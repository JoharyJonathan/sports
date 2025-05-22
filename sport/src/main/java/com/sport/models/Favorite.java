package com.sport.models;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

@Entity("favorite")
public class Favorite {

    @Id
    private ObjectId id;

    @Reference
    private User user;

    @Reference
    private Product product;

    public Favorite() {
    }

    public Favorite(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + getIdAsString() +
                ", user=" + (user != null ? user.getIdAsString() : "null") +
                ", product=" + (product != null ? product.getIdAsString() : "null") +
                '}';
    }
}
