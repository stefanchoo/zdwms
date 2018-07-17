package com.zdmedtech.wms.service;

import com.zdmedtech.wms.domain.*;
import com.zdmedtech.wms.domain.enumuration.OrderStatus;
import com.zdmedtech.wms.repository.*;
import com.zdmedtech.wms.service.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 15:20
 *
 *  WarehouseService Management Service
 */
@Service
@Transactional
@Slf4j
public class WarehouseService {

    private static final String BJD_NUMBER = "ORDER.BJD_NUMBER";
    private static final String BATCH_NUMBER = "ORDER.BATCH_NUMBER";
    private static final String RKD_NUMBER = "ORDER.RKD_NUMBER";
    private static final String LLD_NUMBER = "ORDER.LLD_NUMBER";
    private static final String CKD_NUMBER = "ORDER.CKD_NUMBER";


    private final RedisService redisService;
    private final CheckOrderRepository checkOrderRepository;
    private final InWarehouseOrderRepository inWarehouseOrderRepository;
    private final AskOrderRepository askOrderRepository;
    private final OutWarehouseOrderRepository outWarehouseOrderRepository;
    private final WarehouseCardRepository warehouseCardRepository;
    private final InOutStockRecordRepository inOutStockRecordRepository;

    @Autowired
    public WarehouseService(RedisService redisService,
                            CheckOrderRepository checkOrderRepository,
                            InWarehouseOrderRepository inWarehouseOrderRepository,
                            AskOrderRepository askOrderRepository,
                            OutWarehouseOrderRepository outWarehouseOrderRepository,
                            WarehouseCardRepository warehouseCardRepository,
                            InOutStockRecordRepository inOutStockRecordRepository) {
        this.redisService = redisService;
        this.checkOrderRepository = checkOrderRepository;
        this.inWarehouseOrderRepository = inWarehouseOrderRepository;
        this.askOrderRepository = askOrderRepository;
        this.outWarehouseOrderRepository = outWarehouseOrderRepository;
        this.warehouseCardRepository = warehouseCardRepository;
        this.inOutStockRecordRepository = inOutStockRecordRepository;
    }

    // 1. 报检申请
    public CheckOrder askCheck(CheckOrder checkOrder) {
        // auto create
        // BJDNumber   BJD18071501
        // batchNumber 18071501 第几批货
        checkOrder.setBJDNumber(casBJDNumberFromRedis());
        String batchNumber = casBatchNumberFromRedis();
        checkOrder.getCheckRecords().forEach(r ->
            r.setBatchNumber(batchNumber));
        return checkOrderRepository.save(checkOrder);
    }

    // 2. 生成检验报告
    public CheckOrder check(CheckOrder checkOrder) {
        checkOrder.setStatus(OrderStatus.FINISH);
        return checkOrderRepository.save(checkOrder);
    }

    // 3. 检测完成，申请入库
    public ResponseMessage askInWarehouse(CheckOrder checkOrder) {
        InWarehouseOrder order = new InWarehouseOrder();
        order.setCheckOrder(checkOrder);
        order.setRKDNumber(casRKDNumberFromRedis());
        inWarehouseOrderRepository.save(order);
        return ResponseMessage.SUCCESS_MSG;
    }

    // 4. 入库完成
    public InWarehouseOrder inWarehouseOrder(InWarehouseOrder order) {
        order.setStatus(OrderStatus.FINISH);
        InWarehouseOrder result = inWarehouseOrderRepository.save(order);
        result.getCheckOrder().getCheckRecords().forEach(r -> {
            InOutStockRecord record = new InOutStockRecord();
            record.setBatchNumber(r.getBatchNumber());
            record.setStockIn(Boolean.TRUE);
            record.setQuantity(r.getQuantity());
            record.setLeftQuantity(r.getQuantity());
            record.setWarehouseKeeper(result.getWarehouseKeeper());
            WarehouseCard card = warehouseCardRepository.findOneByMaterial(r.getMaterial());
            record.setWarehouseCard(card);
            card.getInOutStockRecords().add(record);
            int oldQty = card.getQuantity();
            card.setQuantity(oldQty + r.getQuantity());
            card.getMaterial().setQuantity(card.getQuantity());
            warehouseCardRepository.save(card);
        });
        return result;
    }

    // 5. 领料申请
    public AskOrder askOrder(AskOrder askOrder) {
        askOrder.setLLDNumber(casLLDNumberFromRedis());
        return askOrderRepository.save(askOrder);
    }

    // 6. 领导审批
    public AskOrder reject(AskOrder askOrder) {
        askOrder.setStatus(OrderStatus.REJECT);
        return askOrderRepository.save(askOrder);
    }

    public AskOrder approval(AskOrder askOrder) {
        askOrder.setStatus(OrderStatus.PROCESS);
        AskOrder result = askOrderRepository.save(askOrder);
        // create outWarehouseOrder
        OutWarehouseOrder order = new OutWarehouseOrder();
        order.setStatus(OrderStatus.PROCESS);
        order.setCKDNumber(casCKDNumberFromRedis());
        order.setAskOrder(result);
        outWarehouseOrderRepository.save(order);
        return result;
    }

    // 7. 出库
    public OutWarehouseOrder outWarehouseOrder(OutWarehouseOrder order) {
        order.setStatus(OrderStatus.FINISH);
        OutWarehouseOrder result = outWarehouseOrderRepository.save(order);
        result.getAskOrder().getAskItems().forEach(r -> {
            WarehouseCard card = warehouseCardRepository.findOneByMaterial(r.getMaterial());
            InOutStockRecord record = new InOutStockRecord();
            record.setWarehouseKeeper(result.getWarehouseKeeper());
            record.setWarehouseCard(card);
            record.setStockOut(Boolean.TRUE);
            record.setQuantity(r.getTakeQuantity());
            record.setBatchNumber(calculateBatchNumber(card, record.getQuantity()));
            card.getInOutStockRecords().add(record);
            int oldQty = card.getQuantity();
            card.setQuantity(oldQty - record.getQuantity());
            card.getMaterial().setQuantity(card.getQuantity());
            warehouseCardRepository.save(card);
        });
        return result;
    }

    private String calculateBatchNumber(WarehouseCard card, int quantity) {
        String batchNumber = "";
        List<InOutStockRecord> records = inOutStockRecordRepository.findAllByWarehouseCardOrderByCreateTime(card)
                .stream()
                .filter(i -> i.getLeftQuantity() != 0)
                .collect(Collectors.toList());
        for(InOutStockRecord record: records) {
            if(record.getLeftQuantity() >= quantity) {
                record.setLeftQuantity(record.getLeftQuantity() - quantity);
                inOutStockRecordRepository.save(record);
                return batchNumber;
            }
            if(batchNumber.isEmpty()) batchNumber += record.getBatchNumber();
            else batchNumber += "|" + record.getBatchNumber();
            quantity = quantity - record.getLeftQuantity();
            record.setLeftQuantity(0);
            inOutStockRecordRepository.save(record);
        }
        return batchNumber;
    }

    private String casNumberFromRedis(String key, String type) {
        String newNumber;
        String prefix = type + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd", Locale.CHINESE));
        if(redisService.hasKey(key)) {
            String number = redisService.readObject(key).toString();
            if(number.startsWith(prefix)) {
                int index = Integer.valueOf(number.substring(number.length() - 2));
                newNumber = (index + 1) / 10 == 0 ? prefix + "0" + (index + 1) : prefix + (index + 1);
            } else {
                newNumber = prefix + "01";
            }
        } else {
            newNumber = prefix + "01";
        }
        return newNumber;
    }

    private String casBJDNumberFromRedis() {
        String newNumber = casNumberFromRedis(BJD_NUMBER, "BJD");
        redisService.saveValue(BJD_NUMBER, newNumber);
        return newNumber;
    }

    private String casBatchNumberFromRedis() {
        String newNumber = casNumberFromRedis(BATCH_NUMBER, "");
        redisService.saveValue(BATCH_NUMBER, newNumber);
        return newNumber;
    }

    private String casLLDNumberFromRedis() {
        String newNumber = casNumberFromRedis(LLD_NUMBER, "LLD");
        redisService.saveValue(LLD_NUMBER, newNumber);
        return newNumber;
    }

    private String casRKDNumberFromRedis() {
        String newNumber = casNumberFromRedis(RKD_NUMBER, "RKD");
        redisService.saveValue(RKD_NUMBER, newNumber);
        return newNumber;
    }

    private String casCKDNumberFromRedis() {
        String newNumber = casNumberFromRedis(RKD_NUMBER, "CKD");
        redisService.saveValue(CKD_NUMBER, newNumber);
        return newNumber;
    }
}
