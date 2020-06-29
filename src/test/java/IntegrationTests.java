import dev.SOAPwebservices.QuoteSoapService;
import dev.embeddables.AddressEmbeddable;
import dev.embeddables.PersonEmbeddable;
import dev.entities.CustomerEntity;
import dev.entities.ItemEntity;
import dev.entities.OrderEntity;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.*;

public class IntegrationTests {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/DA_Project/");

    // Customer REST Service
    @Test
    public void RESTGetCustomerNotFound(){
        Response response = target.path("customers/9999999").request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTDeleteCustomerNotFound(){
        Response response = target.path("customers/9999999").request().delete();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTUpdateCustomerBadRequest(){
        CustomerEntity customer = new CustomerEntity();
        Response response = target.path("customers").request().buildPut(Entity.entity(customer, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTUpdateCustomerNotFound(){
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerId(9999999L);
        Response response = target.path("customers").request().buildPut(Entity.entity(customer, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTCustomerAddGetUpdateDelete(){
        // Add a customer
        AddressEmbeddable address = new AddressEmbeddable("August Van Putlei", "117", "Borsbeek", "Antwerpen", 2150, "België");
        PersonEmbeddable person = new PersonEmbeddable("Jens", "Leysen", "jens.leysen@student.kuleuven.be","+32470508227","salt","password");
        CustomerEntity newCustomer = new CustomerEntity(address, person);
        Response response = target.path("customers").request().buildPost(Entity.entity(newCustomer, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
        assertTrue(response.hasEntity());
        CustomerEntity customer = response.readEntity(CustomerEntity.class);
        Long customerId = customer.getCustomerId();

        // Get customer
        response = target.path("customers/"+customerId.toString()).request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
        assertTrue(response.hasEntity());
        customer = response.readEntity(CustomerEntity.class);
        newCustomer.setCustomerId(customerId);
        assertThat(newCustomer, sameBeanAs(customer));

        // Update customer
        AddressEmbeddable newAddress = new AddressEmbeddable("ABBA Straat", "1", "Deurne", "Antwerpen", 2140, "België");
        customer.setAddress(newAddress);
        response = target.path("customers").request().buildPut(Entity.entity(customer, MediaType.APPLICATION_JSON)).invoke();
        assertTrue(response.hasEntity());
        customer = response.readEntity(CustomerEntity.class);
        assertThat(customer.getAddress(), sameBeanAs(newAddress));

        // Delete customer
        response = target.path("customers/"+customer.getCustomerId()).request().delete();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());

        // Get deleted customer
        response = target.path("customers/"+customerId.toString()).request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }


    // Order REST Service
    @Test
    public void RESTUpdateOrderNotFound(){
        OrderEntity order = new OrderEntity();
        order.setOrderId(9999999L);
        Response response = target.path("order").request().buildPut(Entity.entity(order, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTGetOrderNotFound(){
        Response response = target.path("order/9999999").request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTGetOrderNotFound2(){
        Response response = target.path("order/RANDOMSTRING").request(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTDeleteOrderNotFound(){
        Response response = target.path("order/9999999").request().delete();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTDeleteOrderNotFound2(){
        Response response = target.path("order/RANDOMSTRING").request().delete();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTUpdateOrderBadRequest(){
        OrderEntity order = new OrderEntity();
        Response response = target.path("order").request().buildPut(Entity.entity(order, MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
    }

    // Item REST Service
    @Test
    public void RESTAddGetDeleteItem(){
        ItemEntity itemEntity = new ItemEntity();
        Response response = target.path("items").request().buildPost(Entity.entity(itemEntity,MediaType.APPLICATION_JSON)).invoke();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
        itemEntity = response.readEntity(ItemEntity.class);
        response = target.path("items/"+itemEntity.getItemId()).request().buildGet().invoke();
        ItemEntity recievedItemEntity = response.readEntity(ItemEntity.class);
        assertThat(itemEntity, sameBeanAs(recievedItemEntity));
        response = target.path("items/"+itemEntity.getItemId()).request().buildDelete().invoke();
        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTDeleteItemNotFound(){
        Response response = target.path("items/9999999").request().buildDelete().invoke();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo().toEnum());
    }

    @Test
    public void RESTGetItemNotFound(){
       Response response = target.path("items/9999999").request().buildGet().invoke();
        assertEquals(response.getStatusInfo().toEnum(), Response.Status.NOT_FOUND);
    }

    @Test
    public void RESTGetItemNotFound2(){
        Response response = target.path("items/RANDOMSTRING").request().buildGet().invoke();
        assertEquals(response.getStatusInfo().toEnum(), Response.Status.NOT_FOUND);
    }

    // SOAP Service
    @Test
    public void SOAPTest(){
        QuoteSoapService quoteSoapService = new QuoteSoapService();
        String spreuk = quoteSoapService.getSpreuk();
        assertNotNull(spreuk);
    }
}
