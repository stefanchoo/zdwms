package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.InWarehouseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 16:50
 */
public interface InWarehouseOrderRepository extends JpaRepository<InWarehouseOrder, Long> {
}
