package dev.beans;

import dev.embeddables.addressEmbeddable;
import dev.embeddables.personEmbeddable;
import dev.entities.customerEntity;
import dev.entities.orderEntity;
import dev.enums.statusEnum;

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
        customerEntity customer = new customerEntity(address, person, null);
        em.persist(customer);
    }

    public void addOrder(){
        addressEmbeddable address = new addressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        Date orderDate = new Date();
        Date requiredDateStart = new Date();
        Date requiredDateEnd = new Date();
        Date deliveredDate = new Date();
        statusEnum status = statusEnum.AWAITING_CONFIRMATION;
        String comment = "Verse groentjes aub!";
        orderEntity order = new orderEntity(address, orderDate, requiredDateStart, requiredDateEnd, deliveredDate, status,comment);
        em.persist(order);
    }
}
