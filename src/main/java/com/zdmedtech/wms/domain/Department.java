package com.zdmedtech.wms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zdmedtech.wms.domain.enumuration.RoleCategory;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 14:44
 *
 *  部门角色： 采购，质检，装配，仓管，研发，领导
 */
@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer"})
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private RoleCategory category;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "department_user",
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    private ZonedDateTime createTime = ZonedDateTime.now();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public RoleCategory getCategory() {
        return category;
    }

    public void setCategory(RoleCategory category) {
        this.category = category;
    }

    public void addUser(User user) {
       for(User user1: users) {
           if(user1.getId().equals(user.getId())) return;
       }
       users.add(user);
    }

    public void removeUser(User user) {
        for (User use1: users) {
            if(use1.getId().equals(user.getId())) users.remove(use1);
        }
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", createTime=" + createTime +
                '}';
    }
}
