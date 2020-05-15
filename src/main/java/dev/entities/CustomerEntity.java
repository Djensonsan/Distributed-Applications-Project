package dev.entities;

import dev.embeddables.AddressEmbeddable;
import dev.embeddables.PersonEmbeddable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customers")
public class CustomerEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    // Address -> List of addresses
    @Embedded
    private AddressEmbeddable address;
    @Embedded
    private PersonEmbeddable person;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Customers_Orders")
    private List <OrderEntity> orders = new ArrayList<>();

    public CustomerEntity() {
    }

    public CustomerEntity(AddressEmbeddable address, PersonEmbeddable person) {
        this.address = address;
        this.person = person;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void addOrder(OrderEntity order) {
        this.orders.add(order);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }

    public PersonEmbeddable getPerson() {
        return person;
    }

    public void setPerson(PersonEmbeddable person) {
        this.person = person;
    }
}
