package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.rest.vm.LoginVM;
import com.zdmedtech.wms.rest.vm.UpdatePwdVM;
import com.zdmedtech.wms.service.RoleService;
import com.zdmedtech.wms.service.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 18:26
 */
@RestController
@RequestMapping("/wms/api/role")
@Slf4j
public class RoleResource {

    private final RoleService roleService;

    @Autowired
    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody  LoginVM vm) {
        log.info("Rest request to login {}", vm);
        return ResponseEntity.ok(roleService.login(vm));
    }

    @PutMapping("/update/password")
    public ResponseEntity<ResponseMessage> updatePwd(@RequestBody UpdatePwdVM vm) {
        log.info("Rest request to update password {}", vm);
        return ResponseEntity.ok(roleService.updatePwd(vm));
    }


}
