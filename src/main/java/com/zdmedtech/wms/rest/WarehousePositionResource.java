package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.domain.WarehousePosition;
import com.zdmedtech.wms.repository.WarehousePositionRepository;
import com.zdmedtech.wms.rest.error.BadRequestAlertException;
import com.zdmedtech.wms.service.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Author: StefanChoo
 * Date: 2018/7/17 14:54
 */
@RestController
@RequestMapping("/wms/api/warehouse_positions")
@Slf4j
public class WarehousePositionResource {

    private WarehousePositionRepository warehousePositionRepository;

    @Autowired
    public WarehousePositionResource(WarehousePositionRepository warehousePositionRepository) {
        this.warehousePositionRepository = warehousePositionRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WarehousePosition>> getAllWarehousePositions() {
        log.info("Rest request to list all warehousePositions");
        return ResponseEntity.ok(warehousePositionRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<WarehousePosition> addNewWarehousePosition(@RequestBody WarehousePosition position) {
        log.info("Rest request to add a new warehousePosition {}", position);
        if(Objects.nonNull(position.getId())) {
            throw new BadRequestAlertException("A new warehousePosition cannot already have an ID", "warehousePositionManagement", "idexists");
        }
        position.setCreateTime(ZonedDateTime.now());
        return ResponseEntity.ok(warehousePositionRepository.saveAndFlush(position));
    }

    @PutMapping("/update")
    public ResponseEntity<WarehousePosition> updateOneWarehousePosition(@RequestBody WarehousePosition position) {
        log.info("Rest request to update one warehousePosition {}", position);
        if(Objects.isNull(position.getId())) {
            return addNewWarehousePosition(position);
        }
        return ResponseEntity.ok(warehousePositionRepository.saveAndFlush(position));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<WarehousePosition> getOneWarehousePosition(@PathVariable Long id) {
        log.info("Rest request to get one position {}", id);
        return warehousePositionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/one/{id}")
    public ResponseEntity<ResponseMessage> deleteOneWarehousePosition(@PathVariable Long id) {
        log.info("Rest request to delete one position {}", id);
        warehousePositionRepository.deleteById(id);
        return ResponseEntity.ok(ResponseMessage.SUCCESS_MSG);
    }
}
