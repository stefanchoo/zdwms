package com.zdmedtech.wms.domain;

import com.zdmedtech.wms.domain.enumuration.OrderStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 12:01
 *  出库单
 */
@Entity
public class OutWarehouseOrder {
    @Id
    @GeneratedValue
    private Long id;
    private String CKNumber;

    @OneToOne
    private AskOrder askOrder;

    @ManyToOne
    private User warehouseKeeper;  // 仓管

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.WAIT;

    private ZonedDateTime createTime = ZonedDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCKNumber() {
        return CKNumber;
    }

    public void setCKNumber(String CKNumber) {
        this.CKNumber = CKNumber;
    }

    public AskOrder getAskOrder() {
        return askOrder;
    }

    public void setAskOrder(AskOrder askOrder) {
        this.askOrder = askOrder;
    }

    public User getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(User warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }
}
