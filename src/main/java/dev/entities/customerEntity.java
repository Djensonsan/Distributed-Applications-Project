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
    @OneToMany
    private List <orderEntity> orders;

    public customerEntity() {
    }

    public customerEntity(addressEmbeddable address, personEmbeddable person, orderEntity order) {
        this.address = address;
        this.person = person;
        this.orders = new ArrayList<>();
        orders.add(order);
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
