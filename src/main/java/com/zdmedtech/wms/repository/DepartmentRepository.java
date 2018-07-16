package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.Department;
import com.zdmedtech.wms.domain.enumuration.RoleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 14:53
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    Department findOneByCategory(RoleCategory category);
}
