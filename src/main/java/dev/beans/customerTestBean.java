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
        em.persist(customer);
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
        em.persist(order);
    }

    // Get a customer and add an order to it
    // Bug: 2nd customer and order are added
    public void addOrdertoCustomer(){
        Query query = em.createNativeQuery("select * from da_database.customerentity where customerentity.CUSTOMERID = 1", customerEntity.class);
        customerEntity customer = (customerEntity) query.getSingleResult();

        addressEmbeddable address = new addressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        Date orderDate = new Date();
        Date requiredDateStart = new Date();
        Date requiredDateEnd = new Date();
        Date deliveredDate = new Date();
        statusEnum status = statusEnum.AWAITING_CONFIRMATION;
        String comment = "Geen vieze patatjes";
        orderEntity order = new orderEntity(address, orderDate, requiredDateStart, requiredDateEnd, deliveredDate, status, comment);

        customer.addOrder(order);
        em.persist(order);
        em.persist(customer);
    }
}
