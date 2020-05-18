package dev.webservices;

import dev.beans.OrderBean;
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
        OrderEntity order = orderBean.getOrder(orderId);
        return Response.ok(order, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/add/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postOrder(@PathParam("customerId") Long customerId, OrderEntity order) {
        if (order == null) {
            throw new BadRequestException();
        }
        try {
            orderBean.addOrderToCustomer(order, customerId);
        } catch(Exception e){
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{orderId}")
    public Response deleteOrder(@PathParam("orderId") Long orderId) {
        orderBean.deleteOrder(orderId);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(OrderEntity order) {
        try {
            orderBean.updateOrder(order);
        } catch (Exception e){
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
