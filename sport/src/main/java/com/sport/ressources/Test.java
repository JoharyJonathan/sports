package com.sport.ressources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class Test {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String SayTest() {
        return "Test !";
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String testing() {
        return "{\"message\": \"This is Test !\"}";
    }
}
