package com.sport.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

@Entity("carts")
public class Cart {

    @Id
    private ObjectId id;

    @Property("user_id")
    private ObjectId userId;

    private List<CartItem> items = new ArrayList<>();

    @Property("created_at")
    private Date createdAt = new Date();

    private String status = "pending";

    public Cart() {}

    public Cart(ObjectId userId) {
        this.userId = userId;
        this.createdAt = new Date();
        this.status = "pending";
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addItem(ObjectId productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(productId, quantity));
    }

    public void removeItem(ObjectId productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }
}