package com.zdmedtech.wms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 10:04
 */
@Entity
public class WarehousePosition {

    @Id
    @GeneratedValue
    private Long id;
    private String rack;                 //  货架  H01
    private String layer;                //  层数  A02
    @Column(unique = true)
    private String position;             //  仓位  H01A0203

    private ZonedDateTime createTime = ZonedDateTime.now();

    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private Set<WarehouseCard> warehouseCards = new HashSet<>();

    @ManyToOne
    private User warehouseKeeper;         // 仓管

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public User getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(User warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }

    public Set<WarehouseCard> getWarehouseCards() {
        return warehouseCards;
    }

    public void setWarehouseCards(Set<WarehouseCard> warehouseCards) {
        this.warehouseCards = warehouseCards;
    }
}
