package dev.messaging;

import dev.entities.CustomerEntity;
import dev.entities.OrderEntity;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@MessageDriven(mappedName = "orderQueue")
public class OrderNotifierMDB implements MessageListener {
    @EJB
    EmailSessionBean emailBean;

    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public OrderNotifierMDB() {
    }

    public void onMessage(Message message) {
        ObjectMessage ObjectOrder = (ObjectMessage) message;
        OrderEntity order;
        try {
            if(ObjectOrder.getObject() instanceof OrderEntity) {
                order = (OrderEntity) ObjectOrder.getObject();
                Query customerQuery = em.createNativeQuery("SELECT customers_orders.CUSTOMERID FROM customers_orders WHERE customers_orders.ORDERID="+order.getOrderId());
                Long customerId = (Long) customerQuery.getSingleResult();
                CustomerEntity customer = em.find(CustomerEntity.class, customerId);
                emailBean.sendSimpleMail(customer, order);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
