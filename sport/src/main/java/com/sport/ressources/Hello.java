package com.sport.ressources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello everyone";
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloJson() {
        return "{\"message\": \"Hello JSON!\"}";
    }
}
