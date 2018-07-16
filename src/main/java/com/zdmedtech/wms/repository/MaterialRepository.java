package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 16:49
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
