package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.CheckOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 16:53
 */
@Repository
public interface CheckOrderRepository extends JpaRepository<CheckOrder, Long> {
}
