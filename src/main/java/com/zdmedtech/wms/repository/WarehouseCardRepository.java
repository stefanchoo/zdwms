package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.Material;
import com.zdmedtech.wms.domain.WarehouseCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 16:47
 */
@Repository
public interface WarehouseCardRepository extends JpaRepository<WarehouseCard, Long> {
    WarehouseCard findOneByMaterial(Material material);
}
