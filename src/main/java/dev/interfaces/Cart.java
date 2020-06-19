package dev.interfaces;

import dev.entities.ItemEntity;

import javax.ejb.Local;

@Local
public interface Cart {

    void addProductToCart(ItemEntity product);

    void checkOut();
}
