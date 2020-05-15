package dev.entities;

import dev.embeddables.PersonEmbeddable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Couriers")
public class CourierEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courierId;
    @Embedded
    private PersonEmbeddable person;
    @OneToMany(cascade = CascadeType.ALL)
    private List <OrderItemEntity> orders = new ArrayList<>();

    public CourierEntity() {
    }

    public CourierEntity(PersonEmbeddable person) {
        this.person = person;
    }

    public PersonEmbeddable getPerson() {
        return person;
    }

    public void setPerson(PersonEmbeddable person) {
        this.person = person;
    }

    public List<OrderItemEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemEntity> orders) {
        this.orders = orders;
    }
}
