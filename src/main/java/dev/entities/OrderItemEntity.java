package dev.entities;

import javax.persistence.*;

@Entity
@Table(name = "ItemOrders")
public class OrderItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private int quantity;
    private String comment;
    @OneToOne(cascade = CascadeType.ALL)
    private ItemEntity item;

    public OrderItemEntity() {
    }

    public OrderItemEntity(int quantity, String comment, ItemEntity item) {
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

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }
}
