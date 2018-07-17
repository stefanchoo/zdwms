package com.zdmedtech.wms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 12:09
 */
@Entity
public class AskItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Material material;

    private Integer askQuantity;
    private Integer takeQuantity;
    private String batchNumber;
    private ZonedDateTime createTime = ZonedDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Integer askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Integer getTakeQuantity() {
        return takeQuantity;
    }

    public void setTakeQuantity(Integer takeQuantity) {
        this.takeQuantity = takeQuantity;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }
}
