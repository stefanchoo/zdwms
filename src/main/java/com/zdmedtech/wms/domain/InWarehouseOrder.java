package com.zdmedtech.wms.domain;

import com.zdmedtech.wms.domain.enumuration.OrderStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 11:46
 *
 * 入库单
 *
 */
@Entity
public class InWarehouseOrder {
    @Id
    @GeneratedValue
    private Long id;
    private String RKDNumber;

    @OneToOne
    private CheckOrder checkOrder;

    private String note;
    private ZonedDateTime createTime = ZonedDateTime.now();

    @ManyToOne
    private User warehouseKeeper;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.WAIT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRKDNumber() {
        return RKDNumber;
    }

    public void setRKDNumber(String RKDNumber) {
        this.RKDNumber = RKDNumber;
    }

    public CheckOrder getCheckOrder() {
        return checkOrder;
    }

    public void setCheckOrder(CheckOrder checkOrder) {
        this.checkOrder = checkOrder;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(User warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }
}
