package dev.beans;

import dev.embeddables.addressEmbeddable;
import dev.embeddables.personEmbeddable;
import dev.entities.customerEntity;
import dev.entities.orderEntity;
import dev.enums.statusEnum;
import testClasses.TestEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;


@Stateless(name = "customerTestEJB")
public class customerTestBean {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public customerTestBean(){
    }

    public void addCustomer(){
        addressEmbeddable address = new addressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        personEmbeddable person = new personEmbeddable("Jens", "Leysen", "jens.leysen@student.kuleuven.be","+32470508227","salt","password");
        customerEntity customer = new customerEntity(address, person);
        Boolean persisted = em.contains(customer);
        em.persist(customer);
        persisted = em.contains(customer);
        em.flush();
    }

    // Persisted without customer linked to it
    public void addOrder(){
        addressEmbeddable address = new addressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        Date orderDate = new Date();
        Date requiredDateStart = new Date();
        Date requiredDateEnd = new Date();
        Date deliveredDate = new Date();
        statusEnum status = statusEnum.AWAITING_CONFIRMATION;
        String comment = "Verse groentjes aub!";
        orderEntity order = new orderEntity(address, orderDate, requiredDateStart, requiredDateEnd, deliveredDate, status, comment);
        Boolean persisted = em.contains(order);
        em.persist(order);
        persisted = em.contains(order);
        em.flush();
    }

    // Get a customer and add an order to it
    public void addOrdertoCustomer(){
        customerEntity customer = em.find(customerEntity.class, 1L);

        Boolean persisted = em.contains(customer);

        addressEmbeddable address = new addressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        Date orderDate = new Date();
        Date requiredDateStart = new Date();
        Date requiredDateEnd = new Date();
        Date deliveredDate = new Date();
        statusEnum status = statusEnum.AWAITING_CONFIRMATION;
        String comment = "Geen vieze patatjes";
        orderEntity order = new orderEntity(address, orderDate, requiredDateStart, requiredDateEnd, deliveredDate, status, comment);

        customer.addOrder(order);
        em.flush();
    }
}
