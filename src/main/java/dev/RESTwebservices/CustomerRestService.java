package dev.RESTwebservices;

import dev.DTOs.CustomerDTO;
import dev.beans.CustomerBean;
import dev.customExceptions.CustomerNotFoundException;
import dev.customExceptions.ItemNotFoundException;
import dev.entities.CustomerEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("customers")
@Stateless
public class CustomerRestService {

    @Context
    private UriInfo uriInfo;

    @EJB
    private CustomerBean customerBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCustomer(CustomerEntity customer) {
        if (customer == null) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Headers",
                            "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Methods",
                            "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .entity("")
                    .build();
        }
        CustomerEntity persistedCustomer = customerBean.addCustomer(customer);
        return Response.ok(persistedCustomer, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        try {
            CustomerEntity customer = customerBean.getCustomer(customerId);
            return Response.ok(customer, MediaType.APPLICATION_JSON).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/getAll")
    public Response getCustomers() {
        try {
            List<CustomerDTO> customers = customerBean.getCustomers();
            return Response.ok(customers).header("Access-Control-Allow-Origin", "*").build();
        } catch (CustomerNotFoundException | ItemNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerEntity customer) {
        if (customer == null || customer.getCustomerId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            CustomerEntity persistedCustomer = customerBean.updateCustomer(customer);
            return Response.ok(persistedCustomer, MediaType.APPLICATION_JSON).build();
        } catch (CustomerNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        try {
            customerBean.deleteCustomer(customerId);
            return Response.ok().build();
        } catch (CustomerNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
