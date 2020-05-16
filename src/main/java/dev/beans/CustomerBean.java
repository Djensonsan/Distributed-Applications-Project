package dev.beans;

import dev.embeddables.AddressEmbeddable;
import dev.embeddables.PersonEmbeddable;
import dev.entities.CustomerEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "customerEJB")
public class CustomerBean {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public CustomerBean() {
    }

    public CustomerEntity getCustomer(Long customerId){
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        return customer;
    }

    public void addCustomer(){
        AddressEmbeddable address = new AddressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        PersonEmbeddable person = new PersonEmbeddable("Jens", "Leysen", "jens.leysen@student.kuleuven.be","+32470508227","salt","password");
        CustomerEntity customer = new CustomerEntity(address, person);
        em.persist(customer);
    }
}
