package com.zdmedtech.wms.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 19:39
 *
 * 库存卡
 *
 */
@Entity
public class WarehouseCard {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private WarehousePosition position;  // 仓位

    @OneToOne(cascade = {CascadeType.ALL})
    private Material material;

    private Integer quantity;
    private ZonedDateTime createTime = ZonedDateTime.now();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<InOutStockRecord> inOutStockRecords = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WarehousePosition getPosition() {
        return position;
    }

    public void setPosition(WarehousePosition position) {
        this.position = position;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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

    public Set<InOutStockRecord> getInOutStockRecords() {
        return inOutStockRecords;
    }

    public void setInOutStockRecords(Set<InOutStockRecord> inOutStockRecords) {
        this.inOutStockRecords = inOutStockRecords;
    }
}
