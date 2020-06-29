package dev.SOAPwebservices;

import javax.jws.WebService;

@WebService
public class OrderSoapService {

    public OrderSoapService() {
    }

    public String Sayhello (){
        return "Hello";
    }
}
