package dev.beans;

import dev.entities.CustomerEntity;
import dev.entities.OrderEntity;

import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "orderEJB")
public class OrderBean {

    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public OrderBean() {
    }

    public OrderEntity getOrder(Long orderId){
        OrderEntity order = em.find(OrderEntity.class, orderId);
        return order;
    }

    public void deleteOrder(Long orderId){
        OrderEntity order = em.find(OrderEntity.class, orderId);
        em.remove(order);
    }

    public void updateOrder(OrderEntity newOrder) throws JMSException, NamingException {
        Long orderId = newOrder.getOrderId();
        OrderEntity oldOrder = em.find(OrderEntity.class, orderId);
        oldOrder.setAddress(newOrder.getAddress());
        oldOrder.setComment(newOrder.getComment());
        oldOrder.setDeliveredDate(newOrder.getDeliveredDate());
        oldOrder.setOrderedItems(newOrder.getOrderedItems());
        oldOrder.setRequiredDateEnd(newOrder.getRequiredDateEnd());
        oldOrder.setStatus(newOrder.getStatus());
        oldOrder.setRequiredDateStart(newOrder.getRequiredDateStart());
        oldOrder.setOrderDate(newOrder.getOrderDate());
        produceOrderMessage(oldOrder);
    }

    public void addOrderToCustomer(OrderEntity order, Long customerId) throws JMSException, NamingException {
        CustomerEntity customer = em.find(CustomerEntity.class, customerId);
        customer.addOrder(order);
        produceOrderMessage(order);
        System.out.println("Order bean is saying: "+order.toString());
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
