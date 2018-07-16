package com.zdmedtech.wms.domain;

import com.zdmedtech.wms.domain.enumuration.MaterialCategory;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Author: StefanChoo
 * Date: 2018/7/12 19:32
 */
@Entity
public class Material {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String specification;

    @Enumerated(EnumType.STRING)
    private MaterialCategory category;

    private String description;
    private Integer quantity;
    private Integer maxQuantity;
    private Integer minQuantity;
    private String unit;
    private Boolean outofstork;     // 低于最低库存
    private String supplier;

    @OneToOne
    private WarehouseCard warehouseCard;
    private ZonedDateTime createTime = ZonedDateTime.now();
    private ZonedDateTime lastUpdateTime;

    @ManyToOne
    private User lastModifiedUser;

    private Boolean outofdate;       // 过期不再使用

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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public MaterialCategory getCategory() {
        return category;
    }

    public void setCategory(MaterialCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getOutofstork() {
        return outofstork;
    }

    public void setOutofstork(Boolean outofstork) {
        this.outofstork = outofstork;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public WarehouseCard getWarehouseCard() {
        return warehouseCard;
    }

    public void setWarehouseCard(WarehouseCard warehouseCard) {
        this.warehouseCard = warehouseCard;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(ZonedDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public User getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Boolean getOutofdate() {
        return outofdate;
    }

    public void setOutofdate(Boolean outofdate) {
        this.outofdate = outofdate;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", maxQuantity=" + maxQuantity +
                ", minQuantity=" + minQuantity +
                ", unit='" + unit + '\'' +
                ", outofstork=" + outofstork +
                ", supplier='" + supplier + '\'' +
                ", warehouseCard=" + warehouseCard +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", lastModifiedUser=" + lastModifiedUser +
                ", outofdate=" + outofdate +
                '}';
    }
}
