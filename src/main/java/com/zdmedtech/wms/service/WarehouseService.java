package com.zdmedtech.wms.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zdmedtech.wms.domain.AskOrder;
import com.zdmedtech.wms.domain.CheckOrder;
import com.zdmedtech.wms.domain.InWarehouseOrder;
import com.zdmedtech.wms.domain.OutWarehouseOrder;
import com.zdmedtech.wms.service.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    // 1. 报检申请
    public CheckOrder askCheck(CheckOrder checkOrder) {
        return null;
    }

    // 2. 生成检验报告
    public CheckOrder check() {
        return null;
    }

    // 3. 检测完成，申请入库
    public ResponseMessage askInWarehouse(CheckOrder checkOrder) {
        return ResponseMessage.SUCCESS_MSG;
    }

    // 4. 入库完成
    public InWarehouseOrder inWarehouseOrder(CheckOrder order) {
        //TODO: 更新物料库存&库存卡
        return null;
    }

    // 5. 领料申请
    public AskOrder askOrder(AskOrder askOrder) {
        return null;
    }

    // 6. 领导审批
    public AskOrder approval(AskOrder askOrder) {
        return null;
    }

    // 7. 出库
    public OutWarehouseOrder outWarehouseOrder(AskOrder order) {
        //TODO: 更新物料库存&库存卡
        return null;
    }
}
