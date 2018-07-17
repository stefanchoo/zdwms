package com.zdmedtech.wms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 14:37
 */
@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

    @Column(unique = true)
    private String workNumber;

    @JsonIgnore
    private String password;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();

    private String signature;
    private ZonedDateTime lastLoginTime = ZonedDateTime.now();
    private ZonedDateTime createTime = ZonedDateTime.now();

    private Boolean activated = Boolean.FALSE;       // 首次登录，修改密码激活

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZonedDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(ZonedDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", password='" + password + '\'' +
                ", departments=" + departments +
                ", signature='" + signature + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime=" + createTime +
                ", activated=" + activated +
                '}';
    }
}

