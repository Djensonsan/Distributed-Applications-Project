package dev.entities;

import dev.embeddables.AddressEmbeddable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Shops")
public class ShopEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;
    private String name;
    private String email;
    private String phoneNumber;
    @Embedded
    private AddressEmbeddable address;
    @OneToMany(cascade = CascadeType.ALL)
    private List <ItemEntity> items = new ArrayList<>();

    public ShopEntity() {
    }

    public ShopEntity(String name, String email, String phoneNumber, AddressEmbeddable address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
