package dev.beans;

import dev.customExceptions.CustomerNotFoundException;
import dev.entities.CustomerEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;

@Stateless(name = "customerEJB")
public class CustomerBean {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public CustomerBean() {
    }

    public CustomerEntity getCustomer(Long customerId) throws CustomerNotFoundException {
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        if (customer == null){
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    public CustomerEntity addCustomer(CustomerEntity customer){
        em.persist(customer);
        em.flush();
        em.refresh(customer);
        return customer;
    }

    public CustomerEntity updateCustomer(CustomerEntity newCustomer) throws CustomerNotFoundException,BadRequestException {
        Long customerId = newCustomer.getCustomerId();
        CustomerEntity oldCustomer = em.find(CustomerEntity.class, customerId);
        if(oldCustomer == null){
            throw new CustomerNotFoundException();
        }
        oldCustomer.setAddress(newCustomer.getAddress());
        oldCustomer.setPerson(newCustomer.getPerson());
        em.flush();
        return oldCustomer;
    }

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException{
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        if(customer == null){
            throw new CustomerNotFoundException();
        }
        em.remove(customer);
    }
}
