package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.domain.WarehouseCard;
import com.zdmedtech.wms.repository.WarehouseCardRepository;
import com.zdmedtech.wms.service.AccountGoodsCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: StefanChoo
 * Date: 2018/7/16 19:10
 */
@RestController
@RequestMapping("/wms/api/warehouse_cards")
@Slf4j
public class WarehouseCardResource {

    private final AccountGoodsCardService accountGoodsCardService;
    private final WarehouseCardRepository warehouseCardRepository;

    @Autowired
    public WarehouseCardResource(AccountGoodsCardService accountGoodsCardService, WarehouseCardRepository warehouseCardRepository) {
        this.accountGoodsCardService = accountGoodsCardService;
        this.warehouseCardRepository = warehouseCardRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WarehouseCard>> getAllWarehouseCards() {
        log.info("Rest request to list all warehouseCards");
        return ResponseEntity.ok(warehouseCardRepository.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<WarehouseCard> updateWarehouseCard(@RequestBody WarehouseCard card) {
        log.info("Rest request to update one warehouseCard {}", card);
        return ResponseEntity.ok(warehouseCardRepository.save(card));
    }
}
