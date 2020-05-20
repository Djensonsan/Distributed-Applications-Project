package dev.messaging;

import dev.entities.OrderEntity;

import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(mappedName = "orderQueue")
public class OrderNotifierMDB implements MessageListener {
    public OrderNotifierMDB() {
    }

    public void onMessage(Message message){
        ObjectMessage ObjectOrder = (ObjectMessage) message;
        OrderEntity order;
        try {
            if(ObjectOrder.getObject() instanceof OrderEntity) {
                order = (OrderEntity) ObjectOrder.getObject();
                System.out.println("MDB received order: " + order.toString());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
