package com.sport.filters;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, 
                      ContainerResponseContext responseContext) throws IOException {
        
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        
        headers.add("Access-Control-Allow-Origin", "http://localhost:5173");
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Headers", 
            "origin, content-type, accept, authorization, X-Requested-With");
        headers.add("Access-Control-Allow-Methods", 
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        
        // Important pour les requêtes OPTIONS (preflight)
        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            headers.add("Access-Control-Max-Age", "1209600"); // 2 semaines
        }
    }
}