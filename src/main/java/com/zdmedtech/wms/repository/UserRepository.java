package com.zdmedtech.wms.repository;

import com.zdmedtech.wms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 14:53
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
