package com.zdmedtech.wms.domain;

import com.zdmedtech.wms.domain.enumuration.OrderStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 10:48
 *  报检单
 */
@Entity
public class CheckOrder {

    @Id
    @GeneratedValue
    private Long id;
    private String BJDNumber;
    private String PPANumber;

    @ManyToOne
    private User askChecker;
    private ZonedDateTime createTime = ZonedDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CheckRecord> checkRecords = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.WAIT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBJDNumber() {
        return BJDNumber;
    }

    public void setBJDNumber(String BJDNumber) {
        this.BJDNumber = BJDNumber;
    }

    public String getPPANumber() {
        return PPANumber;
    }

    public void setPPANumber(String PPANumber) {
        this.PPANumber = PPANumber;
    }

    public User getAskChecker() {
        return askChecker;
    }

    public void setAskChecker(User askChecker) {
        this.askChecker = askChecker;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public Set<CheckRecord> getCheckRecords() {
        return checkRecords;
    }

    public void setCheckRecords(Set<CheckRecord> checkRecords) {
        this.checkRecords = checkRecords;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
