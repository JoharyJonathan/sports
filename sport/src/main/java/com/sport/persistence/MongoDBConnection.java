package com.sport.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MongoDBConnection {
    private static final String DB_NAME = "sportdb";
    private final Datastore datastore;

    public MongoDBConnection() {
        try {
            MongoClient client = MongoClients.create("mongodb://localhost:27017");
            this.datastore = Morphia.createDatastore(client, DB_NAME);
            this.datastore.getMapper().mapPackage("com.sport.models");
            this.datastore.ensureIndexes();
            System.out.println("Connecté à MongoDB avec succès");
        } catch (Exception e) {
            throw new RuntimeException("Erreur de connexion MongoDB", e);
        }
    }

    public Datastore getDatastore() {
        return datastore;
    }
}