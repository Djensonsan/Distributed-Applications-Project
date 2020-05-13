package dev.entities;

import dev.embeddables.addressEmbeddable;
import dev.enums.statusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
public class orderEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Embedded
    private addressEmbeddable address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requiredDateStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requiredDateEnd;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredDate;
    @Enumerated(EnumType.STRING)
    private statusEnum status;
    private String comment;

    public orderEntity() {
    }

    public orderEntity(addressEmbeddable address, Date orderDate, Date requiredDateStart, Date requiredDateEnd, Date deliveredDate, statusEnum status, String comment) {
        this.address = address;
        this.orderDate = orderDate;
        this.requiredDateStart = requiredDateStart;
        this.requiredDateEnd = requiredDateEnd;
        this.deliveredDate = deliveredDate;
        this.status = status;
        this.comment = comment;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public addressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(addressEmbeddable address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDateStart() {
        return requiredDateStart;
    }

    public void setRequiredDateStart(Date requiredDateStart) {
        this.requiredDateStart = requiredDateStart;
    }

    public Date getRequiredDateEnd() {
        return requiredDateEnd;
    }

    public void setRequiredDateEnd(Date requiredDateEnd) {
        this.requiredDateEnd = requiredDateEnd;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public statusEnum getStatus() {
        return status;
    }

    public void setStatus(statusEnum status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
