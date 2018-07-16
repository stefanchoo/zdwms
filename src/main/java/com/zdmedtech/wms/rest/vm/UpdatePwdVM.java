package com.zdmedtech.wms.rest.vm;

import lombok.Data;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 18:06
 */
@Data
public class UpdatePwdVM {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
