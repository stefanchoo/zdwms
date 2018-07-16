package com.zdmedtech.wms.domain;

import com.zdmedtech.wms.domain.enumuration.OrderStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 12:03
 */
@Entity
public class AskOrder {
    @Id
    @GeneratedValue
    private Long id;
    private String LLNumber;
    private String SRNumber;

    private ZonedDateTime createTime = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private User asker;       // 申领人

    @ManyToOne
    private User approver;    // 审批人

    @OneToMany(fetch = FetchType.EAGER)
    private Set<AskItem> askItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLLNumber() {
        return LLNumber;
    }

    public void setLLNumber(String LLNumber) {
        this.LLNumber = LLNumber;
    }

    public String getSRNumber() {
        return SRNumber;
    }

    public void setSRNumber(String SRNumber) {
        this.SRNumber = SRNumber;
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

    public User getAsker() {
        return asker;
    }

    public void setAsker(User asker) {
        this.asker = asker;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Set<AskItem> getAskItems() {
        return askItems;
    }

    public void setAskItems(Set<AskItem> askItems) {
        this.askItems = askItems;
    }
}
