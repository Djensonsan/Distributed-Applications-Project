package dev.webservices;

import dev.beans.CustomerBean;
import dev.entities.CustomerEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("customer")
@Stateless
public class CustomerRestService {

    @Context
    private UriInfo uriInfo;

    @EJB
    private CustomerBean customerBean;

    @GET
    @Path("/get/{customerId}")
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        CustomerEntity customer = customerBean.getCustomer(customerId);
        if (customer == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCustomer(CustomerEntity customer) {
        if (customer == null) {
            throw new BadRequestException();
        }
        customerBean.addCustomer(customer);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        customerBean.deleteCustomer(customerId);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerEntity customer) {
        customerBean.updateCustomer(customer);
        return Response.ok().build();
    }
}
