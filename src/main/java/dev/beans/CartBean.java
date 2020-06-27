package dev.beans;

import dev.entities.ItemEntity;
import dev.interfaces.Cart;
import dev.interceptors.activeCartInterceptor;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful(name = "CartBean")
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 2)
@Interceptors(activeCartInterceptor.class)
public class CartBean implements Cart {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    private List products;

    @PostConstruct
    private void initializeBean() {
        products = new ArrayList<ItemEntity>();
    }

    @Override
    public void addProductToCart(ItemEntity product) {
        products.add(product);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void checkOut() {
//        for(ItemEntity product : products){
//            em.persist(product);
//        }
        products.clear();
    }
}
