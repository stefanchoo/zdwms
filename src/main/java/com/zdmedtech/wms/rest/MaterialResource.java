package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.domain.Material;
import com.zdmedtech.wms.repository.MaterialRepository;
import com.zdmedtech.wms.rest.error.BadRequestAlertException;
import com.zdmedtech.wms.service.AccountGoodsCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Author: StefanChoo
 * Date: 2018/7/16 18:21
 */
@RestController
@RequestMapping("/wms/api/materials")
@Slf4j
public class MaterialResource {
    private final MaterialRepository materialRepository;
    private final AccountGoodsCardService accountGoodsCardService;

    @Autowired
    public MaterialResource(AccountGoodsCardService accountGoodsCardService, MaterialRepository materialRepository) {
        this.accountGoodsCardService = accountGoodsCardService;
        this.materialRepository = materialRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Material>> getAllMaterials() {
        log.info("Rest request to list all material");
        return ResponseEntity.ok(materialRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Material> addNewMaterial(@RequestBody Material material) {
        log.info("Rest request to add a new material {}", material);
        if(Objects.nonNull(material.getId())) {
            throw new BadRequestAlertException("A new material cannot already have an ID", "materialManagement", "idexists");
        }
        return ResponseEntity.ok(accountGoodsCardService.createOneMaterial(material));
    }
}
