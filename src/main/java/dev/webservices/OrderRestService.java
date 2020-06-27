package dev.webservices;

import dev.beans.OrderBean;
import dev.customExceptions.CustomerNotFoundException;
import dev.customExceptions.OrderNotFoundException;
import dev.entities.OrderEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("order")
@Stateless
public class OrderRestService {
    @Context
    private UriInfo uriInfo;


    @EJB
    private OrderBean orderBean;

    @GET
    @Path("/get/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("orderId") Long orderId) {
        try {
            OrderEntity order = orderBean.getOrder(orderId);
            return Response.ok(order, MediaType.APPLICATION_JSON).build();
        } catch (OrderNotFoundException ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postOrder(@PathParam("customerId") Long customerId, OrderEntity order) {
        if (order == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            OrderEntity persistedOrder = orderBean.addOrderToCustomer(order, customerId);
            return Response.ok(persistedOrder, MediaType.APPLICATION_JSON).build();
        } catch(CustomerNotFoundException ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex){
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/delete/{orderId}")
    public Response deleteOrder(@PathParam("orderId") Long orderId) {
        try {
            orderBean.deleteOrder(orderId);
            return Response.ok().build();
        } catch (OrderNotFoundException ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(OrderEntity order) {
        if(order == null || order.getOrderId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            OrderEntity persistedOrder = orderBean.updateOrder(order);
            return Response.ok(persistedOrder, MediaType.APPLICATION_JSON).build();
        } catch (OrderNotFoundException ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex){
            ex.printStackTrace();
            return Response.serverError().build();
        }

    }
}
