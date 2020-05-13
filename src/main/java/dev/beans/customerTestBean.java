package dev.beans;

import dev.embeddables.addressEmbeddable;
import dev.embeddables.personEmbeddable;
import dev.entities.customerEntity;

import javax.ejb.Stateless;
import javax.persistence.*;

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
}
