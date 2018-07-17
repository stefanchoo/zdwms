package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.InOutStockRecord;
import com.zdmedtech.wms.domain.WarehouseCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: StefanChoo
 * Date: 2018/7/17 18:55
 */
@Repository
public interface InOutStockRecordRepository extends JpaRepository<InOutStockRecord, Long> {
    List<InOutStockRecord> findAllByWarehouseCardOrderByCreateTime(WarehouseCard card);
}
