package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.domain.AskOrder;
import com.zdmedtech.wms.domain.CheckOrder;
import com.zdmedtech.wms.domain.InWarehouseOrder;
import com.zdmedtech.wms.domain.OutWarehouseOrder;
import com.zdmedtech.wms.service.WarehouseService;
import com.zdmedtech.wms.service.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: StefanChoo
 * Date: 2018/7/17 15:59
 */
@RestController
@RequestMapping("/wms/api/warehouse_orders")
@Slf4j
public class WarehouseOrderResource {

    private WarehouseService warehouseService;
    @Autowired
    public WarehouseOrderResource(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/ask_check")
    public ResponseEntity<CheckOrder> askCheck(@RequestBody CheckOrder checkOrder) {
        log.info("Rest request to ask check {}", checkOrder);
        return ResponseEntity.ok(warehouseService.askCheck(checkOrder));
    }

    @PostMapping("/check")
    public ResponseEntity<CheckOrder> check(@RequestBody CheckOrder checkOrder) {
        log.info("Rest request to check {}", checkOrder);
        return ResponseEntity.ok(warehouseService.check(checkOrder));
    }

    @PostMapping("/ask_in_warehouse")
    public ResponseEntity<ResponseMessage> askInWarehouse(@RequestBody CheckOrder checkOrder) {
        log.info("Rest request to ask in warehouse {}", checkOrder);
        warehouseService.askInWarehouse(checkOrder);
        return ResponseEntity.ok(ResponseMessage.SUCCESS_MSG);
    }

    @PostMapping("/in_warehouse_order")
    public ResponseEntity<InWarehouseOrder> inWarehouseOrder(@RequestBody InWarehouseOrder inWarehouseOrder) {
        log.info("Rest request to complete in warehouse {}", inWarehouseOrder);
        return ResponseEntity.ok(warehouseService.inWarehouseOrder(inWarehouseOrder));
    }

    @PostMapping("/ask_order")
    public ResponseEntity<AskOrder> askOrder(@RequestBody AskOrder askOrder) {
        log.info("Rest request to ask order {}", askOrder);
        return ResponseEntity.ok(warehouseService.askOrder(askOrder));
    }

    @PostMapping("/reject")
    public ResponseEntity<AskOrder> reject(@RequestBody AskOrder askOrder) {
        log.info("Rest request to reject order {}", askOrder);
        return ResponseEntity.ok(warehouseService.reject(askOrder));
    }

    @PostMapping("/approval")
    public ResponseEntity<AskOrder> approval(@RequestBody AskOrder askOrder) {
        log.info("Rest request to approval order {}", askOrder);
        return ResponseEntity.ok(warehouseService.approval(askOrder));
    }

    @PostMapping("/out_warehouse_order")
    public ResponseEntity<OutWarehouseOrder> outWarehouseOrder(@RequestBody OutWarehouseOrder outWarehouseOrder) {
        log.info("Rest request to complete out warehouse {}", outWarehouseOrder);
        return ResponseEntity.ok(warehouseService.outWarehouseOrder(outWarehouseOrder));
    }
}
