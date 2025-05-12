package com.sport.models;

import org.bson.types.ObjectId;

import com.sport.ressources.ObjectIdAdapter;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Property;
import jakarta.json.bind.annotation.JsonbTypeAdapter;

@Entity(useDiscriminator = false)
public class CartItem {

    @JsonbTypeAdapter(ObjectIdAdapter.class)
    @Property("product_id")
    private ObjectId productId;

    @Property("quantity")
    private int quantity;

    public CartItem() {}

    public CartItem(ObjectId productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}