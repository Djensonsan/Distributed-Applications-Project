package dev.beans;

import dev.DTOs.CustomerDTO;
import dev.DTOs.ItemDTO;
import dev.DTOs.OrderDTO;
import dev.DTOs.OrderItemDTO;
import dev.customExceptions.CustomerNotFoundException;
import dev.customExceptions.ItemNotFoundException;
import dev.entities.CustomerEntity;
import dev.entities.OrderEntity;
import dev.entities.OrderItemEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "customerEJB")
public class CustomerBean {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    @EJB
    ItemBean itemBean;

    public CustomerBean() {
    }

    public CustomerEntity getCustomer(Long customerId) throws CustomerNotFoundException {
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    public List <CustomerDTO> getCustomers() throws CustomerNotFoundException, ItemNotFoundException {
        List<CustomerEntity> customerEntities = em.createQuery("Select c from CustomerEntity c", CustomerEntity.class).getResultList();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (CustomerEntity customerEntity: customerEntities) {
            CustomerDTO customerDTO = new CustomerDTO(customerEntity.getCustomerId(),customerEntity.getAddress(),customerEntity.getPerson());
            List<OrderEntity> orderEntities = customerEntity.getOrders();
            List<OrderItemEntity> orderItemEntities;
            for (OrderEntity orderEntity:orderEntities) {
                orderItemEntities = orderEntity.getOrderedItems();
                OrderDTO orderDTO = new OrderDTO(orderEntity.getOrderId(),orderEntity.getAddress(),orderEntity.getOrderDate(),orderEntity.getRequiredDateStart(),orderEntity.getRequiredDateEnd(),orderEntity.getDeliveredDate(),orderEntity.getStatus(),orderEntity.getComment());
                for (OrderItemEntity orderItemEntity:orderItemEntities) {
                    Long itemId = orderItemEntity.getItem().getItemId();
                    ItemDTO itemDTO = itemBean.getItemDTO(itemId);
                    OrderItemDTO orderItemDTO = new OrderItemDTO(orderItemEntity.getOrderItemId(),orderItemEntity.getQuantity(),orderItemEntity.getComment(),itemDTO);
                    orderDTO.addOrderedItem(orderItemDTO);
                }
                customerDTO.addOrder(orderDTO);
            }
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    public CustomerEntity addCustomer(CustomerEntity customer) {
        em.persist(customer);
        em.flush();
        em.refresh(customer);
        return customer;
    }

    public CustomerEntity updateCustomer(CustomerEntity newCustomer) throws CustomerNotFoundException {
        Long customerId = newCustomer.getCustomerId();
        CustomerEntity oldCustomer = em.find(CustomerEntity.class, customerId);
        if (oldCustomer == null) {
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

    public boolean authenticateCustomer(String email, String password){
        Query query = em.createQuery("Select c.customerId FROM CustomerEntity c WHERE c.person.email = :email AND c.person.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        try{
            query.getSingleResult();
        } catch (NoResultException e){
            return false;
        }
        return true;
    }
}
