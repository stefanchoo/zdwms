package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.AskOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 16:52
 */
@Repository
public interface AskOrderRepository extends JpaRepository<AskOrder, Long> {
}
