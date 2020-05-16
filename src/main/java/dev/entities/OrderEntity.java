package dev.entities;

import dev.embeddables.AddressEmbeddable;
import dev.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class OrderEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Embedded
    private AddressEmbeddable address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requiredDateStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requiredDateEnd;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredDate;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String comment;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Orders_Itemorders",
            joinColumns = @JoinColumn(name = "ORDERID"),
            inverseJoinColumns = @JoinColumn(name = "ORDERITEMID"))
    private List <OrderItemEntity> orderedItems = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(Long orderId, AddressEmbeddable address, Date orderDate, Date requiredDateStart, Date requiredDateEnd, Date deliveredDate, StatusEnum status, String comment, List<OrderItemEntity> orderedItems) {
        this.orderId = orderId;
        this.address = address;
        this.orderDate = orderDate;
        this.requiredDateStart = requiredDateStart;
        this.requiredDateEnd = requiredDateEnd;
        this.deliveredDate = deliveredDate;
        this.status = status;
        this.comment = comment;
        this.orderedItems = orderedItems;
    }

    public OrderEntity(AddressEmbeddable address, Date orderDate, Date requiredDateStart, Date requiredDateEnd, Date deliveredDate, StatusEnum status, String comment) {
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

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<OrderItemEntity> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItemEntity> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void addOrderItem(OrderItemEntity orderItem) {
        this.orderedItems.add(orderItem);
    }
}
