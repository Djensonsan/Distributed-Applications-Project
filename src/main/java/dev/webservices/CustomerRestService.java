package dev.webservices;

import dev.beans.CustomerBean;
import dev.entities.CustomerEntity;
import dev.entities.OrderEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("customer")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class CustomerRestService {

    @Context
    private UriInfo uriInfo;
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    @EJB
    private CustomerBean customerBean;

    //    @GET
//    public Response getAllCustomers(){
//        CustomerEntity customer = em.find(CustomerEntity.class);
//        return Response.ok(customer, MediaType.APPLICATION_JSON).build();
//    }

    @GET
    @Path("/test")
    public Response getTest() {
        String answer = "Get working!";
        return Response.ok(answer, MediaType.APPLICATION_JSON).build();
    }

    // Don't think you want to have backend code here, move find function to seperate bean that's injected here!
    @GET
    @Path("/{customerId}")
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        CustomerEntity customer = customerBean.getCustomer(customerId);
        return Response.ok(customer, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCustomer(CustomerEntity customer) {
        if (customer == null) {
            throw new BadRequestException();
        }
        em.persist(customer);
        return Response.ok().build();
    }


}
