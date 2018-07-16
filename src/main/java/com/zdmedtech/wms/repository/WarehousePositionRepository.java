package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.WarehousePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 16:48
 */
@Repository
public interface WarehousePositionRepository extends JpaRepository<WarehousePosition, Long> {
}
