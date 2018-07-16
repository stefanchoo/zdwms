package com.zdmedtech.wms.rest;

import com.zdmedtech.wms.domain.Department;
import com.zdmedtech.wms.domain.User;
import com.zdmedtech.wms.repository.DepartmentRepository;
import com.zdmedtech.wms.repository.UserRepository;
import com.zdmedtech.wms.rest.error.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 15:55
 */
@RestController
@RequestMapping("/wms/api/departments")
@Slf4j
public class DepartmentResource {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addNewDepartment(@RequestBody Department department) {
        if(Objects.nonNull(department.getId())) {
            throw new BadRequestAlertException("A new department cannot already have an ID", "departmentManagement", "idexists");
        }
        department.setCreateTime(ZonedDateTime.now());
        return ResponseEntity.ok(departmentRepository.saveAndFlush(department));
    }

    @PostMapping("/add/{departmentId}/users")
    ResponseEntity<Department> addUsers(@PathVariable Long departmentId, @RequestBody Set<User> users) {
        Department department = departmentRepository.getOne(departmentId);
        for (User user: users) {
            department.addUser(user);
        }
        return ResponseEntity.ok(departmentRepository.saveAndFlush(department));
    }

    @PostMapping("/remove/{departmentId}/users")
    ResponseEntity<Department> removeUsers(@PathVariable Long departmentId, @RequestBody Set<User> users) {
        Department department = departmentRepository.getOne(departmentId);
        for (User user: users) {
            department.removeUser(user);
        }
        return ResponseEntity.ok(departmentRepository.saveAndFlush(department));
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        if(Objects.isNull(department.getId())) {
            return addNewDepartment(department);
        }
        return ResponseEntity.ok(departmentRepository.saveAndFlush(department));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Department> getOneDepartment(@PathVariable Long id) {
        return departmentRepository.findById(id).map(ResponseEntity::ok).orElse(null);
    }
}
