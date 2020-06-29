package dev.beans;

import dev.DTOs.ItemDTO;
import dev.interceptors.activeCartInterceptor;
import dev.interfaces.Cart;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateful(name = "CartBean")
@Interceptors(activeCartInterceptor.class)
public class CartBean implements Cart {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;
    private Double total = 0.0;
    private List products = new ArrayList<ItemDTO>();

    @Override
    public void addProductToCart(ItemDTO product) {
        products.add(product);
        total += product.getPrice();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void checkOut() {
//        for(ItemEntity product : products){
//            em.persist(product);
//        }
        products.clear();
        //total
    }

    @Override
    public void delete() {
        products.clear();
        total = 0.0;
    }

    @Override
    public List getProducts() {
        return products;
    }

    @Override
    public String getJSON() {
        Jsonb jsonb = JsonbBuilder.create();
        String result = "[\n{\n" +
                "  \"products\": " + jsonb.toJson(products) + ",\n" +
                "  \"total\": " + (Math.round(total * 100.0) / 100.0) + "\n" +
                "}\n]";
        return result;
    }

    public void setProducts(List products) {
        this.products = products;
    }

}
