import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/DA_Project/");

    @Test
    public void shouldNotFindCustomer(){
        Response response = target.path("customer").request("99999999").get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
    }
}
