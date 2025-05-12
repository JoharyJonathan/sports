package com.sport.ressources;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.sport.cache.RedisService;
import com.sport.persistence.MongoDBConnection;

import jakarta.ws.rs.ext.Provider;

@Provider
public class AppBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(new MongoDBConnection()).to(MongoDBConnection.class);
        bind(new RedisService()).to(RedisService.class);
    }
}
