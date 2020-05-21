import dev.embeddables.AddressEmbeddable;
import dev.embeddables.PersonEmbeddable;
import dev.entities.CustomerEntity;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntegrationTests {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/DA_Project/");

    @Test
    public void getCustomerNotFound(){
        Response response = target.path("customer/get/9999999").request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void deleteCustomerNotFound(){
        Response response = target.path("customer/delete/9999999").request().delete();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void updateCustomerBadRequest(){
        CustomerEntity customer = new CustomerEntity();
        Response response = target.path("customer/update").request().buildPut(Entity.entity(customer, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
    }

    @Test
    public void updateCustomerNotFound(){
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerId(9999999L);
        Response response = target.path("customer/update").request().buildPut(Entity.entity(customer, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void customerAddGetUpdateDelete(){
        // Add a customer
        AddressEmbeddable address = new AddressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        PersonEmbeddable person = new PersonEmbeddable("Jens", "Leysen", "jens.leysen@student.kuleuven.be","+32470508227","salt","password");
        CustomerEntity newCustomer = new CustomerEntity(address, person);
        Response response = target.path("customer/add").request().buildPost(Entity.entity(newCustomer, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
        assertTrue(response.hasEntity());
        CustomerEntity customer = response.readEntity(CustomerEntity.class);
        Long customerId = customer.getCustomerId();

        // Get customer
        response = target.path("customer/get/"+customerId.toString()).request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
        assertTrue(response.hasEntity());
        customer = response.readEntity(CustomerEntity.class);
        newCustomer.setCustomerId(customerId);
        assertThat(newCustomer, sameBeanAs(customer));

        // Update customer
        AddressEmbeddable newAddress = new AddressEmbeddable("ABBA Straat", "1", "Deurne", "Antwerpen", 2140, "België");
        customer.setAddress(newAddress);
        response = target.path("customer/update").request().buildPut(Entity.entity(customer, MediaType.APPLICATION_JSON)).invoke();
        assertTrue(response.hasEntity());
        customer = response.readEntity(CustomerEntity.class);
        assertThat(customer.getAddress(), sameBeanAs(newAddress));

        // Delete customer
        response = target.path("customer/delete/"+customer.getCustomerId()).request().delete();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());

        // Get deleted customer
        response = target.path("customer/get/"+customerId.toString()).request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }
}
