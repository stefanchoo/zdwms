package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.OutWarehouseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 16:51
 */
@Repository
public interface OutWarehouseOrderRepository extends JpaRepository<OutWarehouseOrder, Long> {
}
