package dev.messaging;

import dev.entities.OrderEntity;

import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(mappedName = "orderQueue")
public class OrderNotifierMDB implements MessageListener {
    public OrderNotifierMDB() {
    }

    public void onMessage(Message message){
        OrderEntity order = (OrderEntity) message;
    }
}
