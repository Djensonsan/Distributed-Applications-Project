package dev.DTOs;

import dev.embeddables.AddressEmbeddable;
import dev.entities.OrderItemEntity;
import dev.enums.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long orderId;
    private AddressEmbeddable address;
    private Date orderDate;
    private Date requiredDateStart;
    private Date requiredDateEnd;
    private Date deliveredDate;
    private StatusEnum status;
    private String comment;
    private List<OrderItemDTO> orderedItems = new ArrayList<>();

    public OrderDTO(Long orderId, AddressEmbeddable address, Date orderDate, Date requiredDateStart, Date requiredDateEnd, Date deliveredDate, StatusEnum status, String comment) {
        this.orderId = orderId;
        this.address = address;
        this.orderDate = orderDate;
        this.requiredDateStart = requiredDateStart;
        this.requiredDateEnd = requiredDateEnd;
        this.deliveredDate = deliveredDate;
        this.status = status;
        this.comment = comment;
    }

    public OrderDTO() {
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

    public List<OrderItemDTO> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItemDTO> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void addOrderedItem(OrderItemDTO orderItemDTO){
        orderedItems.add(orderItemDTO);
    }
}
