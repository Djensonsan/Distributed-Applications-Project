package dev.DTOs;

import dev.embeddables.AddressEmbeddable;
import dev.embeddables.PersonEmbeddable;
import dev.entities.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private Long customerId;
    private AddressEmbeddable address;
    private PersonEmbeddable person;
    private List<OrderDTO> orders = new ArrayList<>();

    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, AddressEmbeddable address, PersonEmbeddable person) {
        this.customerId = customerId;
        this.address = address;
        this.person = person;
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

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public void addOrder(OrderDTO orderDTO){
        this.orders.add(orderDTO);
    }
}
