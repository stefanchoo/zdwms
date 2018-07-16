package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.domain.User;
import com.zdmedtech.wms.repository.UserRepository;
import com.zdmedtech.wms.rest.error.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 14:55
 */
@RestController
@RequestMapping("/wms/api/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        if(Objects.nonNull(user.getId())) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
        }
        user.setCreateTime(ZonedDateTime.now());
        user.setLastLoginTime(ZonedDateTime.now());
        user.setPassword(passwordEncoder.encode("123456"));
        return ResponseEntity.ok(userRepository.saveAndFlush(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if(Objects.isNull(user.getId())) {
            return addNewUser(user);
        }
        User result = userRepository.getOne(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setWorkNumber(user.getWorkNumber());
        result.setSignature(user.getSignature());
        return ResponseEntity.ok(userRepository.saveAndFlush(result));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok).orElse(null);
    }
}
