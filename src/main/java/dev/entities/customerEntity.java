package dev.entities;

import dev.embeddables.addressEmbeddable;
import dev.embeddables.personEmbeddable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class customerEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    // Address -> List of addresses
    @Embedded
    private addressEmbeddable address;
    @Embedded
    private personEmbeddable person;
    @OneToMany(cascade = CascadeType.ALL)
    private List <orderEntity> orders = new ArrayList<>();

    public customerEntity() {
    }

    public customerEntity(addressEmbeddable address, personEmbeddable person) {
        this.address = address;
        this.person = person;
    }

    public List<orderEntity> getOrders() {
        return orders;
    }

    public void addOrder(orderEntity order) {
        this.orders.add(order);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public addressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(addressEmbeddable address) {
        this.address = address;
    }

    public personEmbeddable getPerson() {
        return person;
    }

    public void setPerson(personEmbeddable person) {
        this.person = person;
    }
}
