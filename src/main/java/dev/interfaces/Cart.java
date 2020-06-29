package dev.interfaces;

import dev.DTOs.ItemDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Cart {

    void addProductToCart(ItemDTO product);

    void checkOut();

    void delete();

    List getProducts();

    String getJSON();
}
