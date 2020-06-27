package dev.webservices;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("dev.webservices");
        register(MultiPartFeature.class);
    }

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
//    @Override
//    public Set<Class<?>> getClasses() {
//        HashSet h = new HashSet<Class<?>>();
//        h.add(CustomerRestService.class);
//        h.add(OrderRestService.class);
//        h.add(ImageRestService.class);
//        return h;
//    }
}