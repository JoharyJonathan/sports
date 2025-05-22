package com.sport.models;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Reference;

@Entity("ratings")
public class Ratings {

    @Id
    private ObjectId id;

    @Reference
    private User user;

    @Reference
    private Product product;

    @Property("score")
    private float score;

    public Ratings() {
    }

    public Ratings(User user, Product product, float score) {
        this.user = user;
        this.product = product;
        this.score = score;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @Override
    public String toString() {
        return "Ratings{" +
               "id=" + getIdAsString() +
               ", user=" + (user != null ? user.getIdAsString() : "null") +
               ", product=" + (product != null ? product.getIdAsString() : "null") +
               ", score=" + score +
               '}';
    }
}