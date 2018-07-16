package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.domain.WarehouseCard;
import com.zdmedtech.wms.repository.WarehouseCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: StefanChoo
 * Date: 2018/7/16 19:10
 */
@RestController
@RequestMapping("/wms/api/warehouse_cards")
@Slf4j
public class WarehouseCardResource {

    private final WarehouseCardRepository warehouseCardRepository;

    @Autowired
    public WarehouseCardResource(WarehouseCardRepository warehouseCardRepository) {
        this.warehouseCardRepository = warehouseCardRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WarehouseCard>> getAllWarehouseCards() {
        log.info("Rest request to list all warehouseCards");
        return ResponseEntity.ok(warehouseCardRepository.findAll());
    }
}
