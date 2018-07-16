package com.zdmedtech.wms.service;

import com.zdmedtech.wms.domain.Material;
import com.zdmedtech.wms.domain.WarehouseCard;
import com.zdmedtech.wms.repository.MaterialRepository;
import com.zdmedtech.wms.repository.WarehouseCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 15:19
 *
 *  Account : 账
 *  Goods   : 货
 *  Card    : 卡
 *
 */
@Service
@Transactional
@Slf4j
public class AccountGoodsCardService {

    private final MaterialRepository materialRepository;
    private final WarehouseCardRepository warehouseCardRepository;

    @Autowired
    public AccountGoodsCardService(MaterialRepository materialRepository, WarehouseCardRepository warehouseCardRepository) {
        this.materialRepository = materialRepository;
        this.warehouseCardRepository = warehouseCardRepository;
    }

    public Material createOneMaterial(Material material) {
        material.setCreateTime(ZonedDateTime.now());
        material.setLastUpdateTime(ZonedDateTime.now());
        WarehouseCard warehouseCard = new WarehouseCard();
        warehouseCard.setMaterial(material);
        warehouseCard.setCreateTime(ZonedDateTime.now());
        material.setWarehouseCard(warehouseCard);
        WarehouseCard card = warehouseCardRepository.save(warehouseCard);
        return card.getMaterial();
    }
}
