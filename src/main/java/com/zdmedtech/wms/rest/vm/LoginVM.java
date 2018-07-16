package com.zdmedtech.wms.rest.vm;

import com.zdmedtech.wms.domain.enumuration.RoleCategory;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 17:38
 */
@Data
public class LoginVM {
    private String workNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleCategory role;
}
