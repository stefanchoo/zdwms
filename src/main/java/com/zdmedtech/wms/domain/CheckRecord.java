package com.zdmedtech.wms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 11:38
 */
@Entity
public class CheckRecord {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Material material;
    private Integer quantity;
    private String batchNumber;
    private Boolean qualified = Boolean.FALSE;
    @ManyToOne
    private User checker;
    private String IQCNumber;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Boolean getQualified() {
        return qualified;
    }

    public void setQualified(Boolean qualified) {
        this.qualified = qualified;
    }

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }

    public String getIQCNumber() {
        return IQCNumber;
    }

    public void setIQCNumber(String IQCNumber) {
        this.IQCNumber = IQCNumber;
    }
}
