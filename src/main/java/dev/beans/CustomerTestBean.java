package dev.beans;

import dev.embeddables.AddressEmbeddable;
import dev.embeddables.PersonEmbeddable;
import dev.entities.*;
import dev.enums.StatusEnum;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Stateless(name = "customerTestEJB")
public class CustomerTestBean {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public CustomerTestBean(){
    }

    public void addCustomer(){
        AddressEmbeddable address = new AddressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        PersonEmbeddable person = new PersonEmbeddable("Jens", "Leysen", "jens.leysen@student.kuleuven.be","+32470508227","salt","password");
        CustomerEntity customer = new CustomerEntity(address, person);
        Boolean persisted = em.contains(customer);
        em.persist(customer);
        persisted = em.contains(customer);
        em.flush();
    }

    // Persisted without customer linked to it
    public void addOrder(){
        AddressEmbeddable address = new AddressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        Date orderDate = new Date();
        Date requiredDateStart = new Date();
        Date requiredDateEnd = new Date();
        Date deliveredDate = new Date();
        StatusEnum status = StatusEnum.AWAITING_CONFIRMATION;
        String comment = "Verse groentjes aub!";
        OrderEntity order = new OrderEntity(address, orderDate, requiredDateStart, requiredDateEnd, deliveredDate, status, comment);

        em.persist(order);
        em.flush();
    }

    // Get a customer and add an order to it
    public void addOrdertoCustomer(){
        CustomerEntity customer = em.find(CustomerEntity.class, 1L);

        Boolean persisted = em.contains(customer);

        // Create Item Entity
        ItemEntity item = new ItemEntity("Danone Yoghurt Aardbei 6-pack", "Ingrediënten : volle yoghurt (waarvan 95% MELK), suiker (6,9%), aardbei (5%), framboos (5%), rode vruchten 5% (braambes 2,3%, aardbei 1,8%, framboos 0,9%), verdikkingsmiddel (gemodificeerd maïszetmeel), natuurlijke aroma's, zwarte wortelconcentraat, wortelconcentraat, wortel- en bosbesconcentraat, zuurteregelaars (natriumcitraat, citroenzuur), geconcentreerd citroensap",120,"6-pack",1.99f);
        ItemEntity item2 = new ItemEntity("Banaan","Chiquita bananen",80, "per stuk",0.49f);

        // Create Order Item Entity
        OrderItemEntity orderItem = new OrderItemEntity(2,"Graag 2 weken houdbaar.", item);
        OrderItemEntity orderItem2 = new OrderItemEntity(12, "Bruine bananen aub.", item2);

        // Create Address embeddable
        AddressEmbeddable address = new AddressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        Date orderDate = new Date();
        Date requiredDateStart = new Date();
        Date requiredDateEnd = new Date();
        Date deliveredDate = new Date();
        StatusEnum status = StatusEnum.AWAITING_CONFIRMATION;
        String comment = "Geen vieze patatjes";
        // Create Order
        OrderEntity order = new OrderEntity(address, orderDate, requiredDateStart, requiredDateEnd, deliveredDate, status, comment);

        order.addOrderItem(orderItem);
        order.addOrderItem(orderItem2);

        // Update Customer: Customer is managed.
        customer.addOrder(order);
        em.flush();
    }

    public List<OrderEntity> getCustomerOrders(Long customerId){
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        List<OrderEntity> orders = customer.getOrders();
        return orders;
    }

//    public void addCourier(){
//        PersonEmbeddable person = new PersonEmbeddable("August", "Martens", "august.martens@student.kuleuven.be","+324705648416","salt","password");
//        CourierEntity courier = new CourierEntity(person);
//        em.persist(courier);
//    }
//
//    public void addShop(){
//        AddressEmbeddable address = new AddressEmbeddable("Menegemlei", "10", "Deurne", "Antwerpen", 2140, "België");
//        ShopEntity shop = new ShopEntity("Bakkerij Janssens","janssens.Patisserie@gmail.com","+32470568787", address);
//        em.persist(shop);
//    }
}
