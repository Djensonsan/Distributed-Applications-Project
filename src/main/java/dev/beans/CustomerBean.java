package dev.beans;

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

    public void addCustomer(CustomerEntity customer){
        em.persist(customer);
    }

    public void deleteCustomer(Long customerId){
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        em.remove(customer);
    }

    public void updateCustomer(CustomerEntity newCustomer){
        Long customerId = newCustomer.getCustomerId();
        CustomerEntity oldCustomer = em.find(CustomerEntity.class, customerId);
        oldCustomer.setAddress(newCustomer.getAddress());
        oldCustomer.setPerson(newCustomer.getPerson());
    }
}
