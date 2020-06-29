package dev.webservices;

import dev.DTOs.OrderDTO;
import dev.beans.OrderBean;
import dev.customExceptions.OrderNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@WebService
public class OrderSoapService {

    @EJB
    OrderBean orderBean;

    public OrderSoapService() {
    }

    public OrderDTO getOrder(@WebParam(name = "order-id") Long orderId){
        try{
            OrderDTO orderDTO = orderBean.getOrderDTO(orderId);
            return orderDTO;
        } catch (OrderNotFoundException e){
            return null;
        }
    }
}
