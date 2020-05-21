package dev.webservices;

import dev.beans.CustomerBean;
import dev.customExceptions.CustomerNotFoundException;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        try {
            CustomerEntity customer = customerBean.getCustomer(customerId);
            return Response.ok(customer, MediaType.APPLICATION_JSON).build();
        } catch (CustomerNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCustomer(CustomerEntity customer) {
        if (customer == null) {
            throw new BadRequestException();
        }
        CustomerEntity persistedCustomer = customerBean.addCustomer(customer);
        return Response.ok(persistedCustomer, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerEntity customer) {
        if (customer == null) {
            throw new BadRequestException();
        }
        try {
            CustomerEntity persistedCustomer = customerBean.updateCustomer(customer);
            return Response.ok(persistedCustomer, MediaType.APPLICATION_JSON).build();
        } catch (CustomerNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/delete/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        try {
            customerBean.deleteCustomer(customerId);
            return Response.ok().build();
        } catch (CustomerNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
