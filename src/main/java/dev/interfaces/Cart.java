package dev.interfaces;

import dev.entities.ItemEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Cart {

    void addProductToCart(ItemEntity product);

    void checkOut();

    void delete();

    List getProducts();

    String getJSON();
}
