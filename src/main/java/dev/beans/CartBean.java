package dev.beans;

import dev.DTOs.ItemDTO;
import dev.customExceptions.CustomerNotFoundException;
import dev.customExceptions.ItemNotFoundException;
import dev.entities.OrderEntity;
import dev.entities.OrderItemEntity;
import dev.interceptors.activeCartInterceptor;
import dev.interfaces.Cart;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jms.JMSException;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.naming.NamingException;
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
    private List<ItemDTO> products = new ArrayList<ItemDTO>();
    @EJB
    private OrderBean orderBean;
    @EJB
    private ItemBean itemBean;

    @Override
    public void addProductToCart(ItemDTO product) {
        products.add(product);
        total += product.getPrice();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void checkOut(long customerID) {
        OrderEntity orderEntity = new OrderEntity();
        List<OrderItemEntity> orderItemEntities = new ArrayList<OrderItemEntity>();
        for (ItemDTO itemdto : products) {
            try {
                OrderItemEntity orderItemEntity = new OrderItemEntity();
                orderItemEntity.setItem(itemBean.getItem(itemdto.getItemId()));
                orderItemEntities.add(orderItemEntity);
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
            }
        }
        orderEntity.setOrderedItems(orderItemEntities);
        try {
            orderBean.addOrderToCustomer(orderEntity, customerID);
            products.clear();
            total = 0.0;
            em.persist(orderEntity);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
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
