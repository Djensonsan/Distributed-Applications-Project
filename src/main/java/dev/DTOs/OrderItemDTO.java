package dev.DTOs;

import dev.entities.ItemEntity;

import javax.persistence.*;

public class OrderItemDTO {
    private Long orderItemId;
    private int quantity;
    private String comment;
    private ItemDTO item;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long orderItemId, int quantity, String comment, ItemDTO item) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.comment = comment;
        this.item = item;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }
}
