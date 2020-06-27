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
}