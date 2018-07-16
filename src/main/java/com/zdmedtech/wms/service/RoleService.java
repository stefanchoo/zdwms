package com.zdmedtech.wms.service;

import com.zdmedtech.wms.domain.Department;
import com.zdmedtech.wms.domain.User;
import com.zdmedtech.wms.repository.DepartmentRepository;
import com.zdmedtech.wms.repository.UserRepository;
import com.zdmedtech.wms.rest.vm.LoginVM;
import com.zdmedtech.wms.rest.vm.UpdatePwdVM;
import com.zdmedtech.wms.service.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 15:13
 * <p>
 * Role Management Service
 * </p>
 */
@Service
@Transactional
@Slf4j
public class RoleService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public RoleService(PasswordEncoder passwordEncoder, UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    public ResponseMessage login(LoginVM vm) {
        Department department = departmentRepository.findOneByCategory(vm.getRole());
        return department.getUsers()
                .stream()
                .filter(u -> Objects.equals(vm.getWorkNumber(), u.getWorkNumber())
                        && passwordEncoder.matches(vm.getPassword(), u.getPassword()))
                .findAny()
                .map(u -> {
                    u.setLastLoginTime(ZonedDateTime.now());
                    return new ResponseMessage().result(Boolean.TRUE).msg("登录成功")
                            .load(userRepository.save(u));
                })
                .orElse(new ResponseMessage().result(Boolean.FALSE).msg("用户名或密码错误"));
    }

    public ResponseMessage updatePwd(UpdatePwdVM vm) {
        return userRepository.findById(vm.getUserId()).map(u -> {
            if (!passwordEncoder.matches(vm.getOldPassword(), u.getPassword()))
                return new ResponseMessage().result(Boolean.FALSE).msg("用户原密码错误");
            u.setActivated(Boolean.TRUE);    // activate user
            u.setPassword(passwordEncoder.encode(vm.getNewPassword()));
            return new ResponseMessage().result(Boolean.TRUE).msg("密码修改成功")
                    .load(userRepository.save(u));
        }).orElse(new ResponseMessage().result(Boolean.FALSE).msg("找不到该用户"));
    }

    // 管理员激活用户
    public ResponseMessage activateUser(User u) {
        u.setActivated(true);
        return ResponseMessage.SUCCESS_MSG;
    }
}
