package com.zdmedtech.wms.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 10:09
 */
@Entity
public class InOutStockRecord {
    @Id
    @GeneratedValue
    private Long id;

    private Boolean stockIn;      // 入库
    private Boolean stockOut;     // 出库
    private String batchNumber;   // 批号
    private Integer quantity;     // 改变数量
    private Integer leftQuantity; // 该批号剩余数量
    private ZonedDateTime createTime = ZonedDateTime.now();

    @ManyToOne
    private User WarehouseKeeper;   // 记录员 仓管

    @ManyToOne
    private WarehouseCard warehouseCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStockIn() {
        return stockIn;
    }

    public void setStockIn(Boolean stockIn) {
        this.stockIn = stockIn;
    }

    public Boolean getStockOut() {
        return stockOut;
    }

    public void setStockOut(Boolean stockOut) {
        this.stockOut = stockOut;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public User getWarehouseKeeper() {
        return WarehouseKeeper;
    }

    public void setWarehouseKeeper(User warehouseKeeper) {
        WarehouseKeeper = warehouseKeeper;
    }

    public WarehouseCard getWarehouseCard() {
        return warehouseCard;
    }

    public void setWarehouseCard(WarehouseCard warehouseCard) {
        this.warehouseCard = warehouseCard;
    }

    public Integer getLeftQuantity() {
        return leftQuantity;
    }

    public void setLeftQuantity(Integer leftQuantity) {
        this.leftQuantity = leftQuantity;
    }
}
