package dev.beans;

import dev.customExceptions.CustomerNotFoundException;
import dev.customExceptions.OrderNotFoundException;
import dev.entities.CustomerEntity;
import dev.entities.OrderEntity;

import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Order;

@Stateless(name = "orderEJB")
public class OrderBean {

    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public OrderBean() {
    }

    public OrderEntity getOrder(Long orderId) throws OrderNotFoundException {
        OrderEntity order = em.find(OrderEntity.class, orderId);
        if (order == null){
            throw new OrderNotFoundException();
        }
        return order;
    }

    public OrderEntity updateOrder(OrderEntity newOrder) throws OrderNotFoundException, JMSException, NamingException {
        Long orderId = newOrder.getOrderId();
        OrderEntity oldOrder = em.find(OrderEntity.class, orderId);
        if(oldOrder == null){
            throw new OrderNotFoundException();
        }
        oldOrder.setAddress(newOrder.getAddress());
        oldOrder.setComment(newOrder.getComment());
        oldOrder.setDeliveredDate(newOrder.getDeliveredDate());
        oldOrder.setOrderedItems(newOrder.getOrderedItems());
        oldOrder.setRequiredDateEnd(newOrder.getRequiredDateEnd());
        oldOrder.setStatus(newOrder.getStatus());
        oldOrder.setRequiredDateStart(newOrder.getRequiredDateStart());
        oldOrder.setOrderDate(newOrder.getOrderDate());
        produceOrderMessage(oldOrder);
        return oldOrder;
    }

    public OrderEntity addOrderToCustomer(OrderEntity order, Long customerId) throws CustomerNotFoundException, JMSException, NamingException {
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        if(customer == null){
            throw new CustomerNotFoundException();
        }
        customer.addOrder(order);
        produceOrderMessage(order);
        return order;
    }

    public void deleteOrder(Long orderId) throws OrderNotFoundException {
        OrderEntity order = em.find(OrderEntity.class, orderId);
        if (order == null){
            throw new OrderNotFoundException();
        }
        em.remove(order);
    }

    public void produceOrderMessage(OrderEntity order) throws NamingException, JMSException {
        Context context = new InitialContext();

        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/__defaultConnectionFactory");
        Queue queue = (Queue) context.lookup("orderQueue");

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer publisher = session.createProducer(queue);
        connection.start();
        ObjectMessage message = session.createObjectMessage(order);
        publisher.send(message);
    }
}
