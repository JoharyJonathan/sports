package com.sport;

import org.glassfish.jersey.server.ResourceConfig;

import com.sport.ressources.AppBinder;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Sport extends ResourceConfig {
    public Sport() {
        packages("com.sport.ressources");
        register(new AppBinder());
    }
}