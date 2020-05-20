package dev.clients;

import dev.entities.CustomerEntity;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class testClient {

    public testClient() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://");
        Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();
        Response response = invocation.invoke();

        CustomerEntity customer = response.readEntity(CustomerEntity.class);
    }
}
