package com.sport.models;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

@Entity("products")
public class Product {

    @Id
    private ObjectId id;

    @Property("name")
    private String name;

    @Property("category")
    private String category;

    @Property("description")
    private String description;

    @Property("stock_quantity")
    private Integer stock_quantity;

    @Property("price")
    private Float price;

    @Property("image_url")
    private String imageUrl;

    public Product() {
    }

    public Product(String name, String category, String description, Integer stock_quantity, Float price, String imageUrl) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.stock_quantity = stock_quantity;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStockQuantity() {
        return stock_quantity;
    }

    public void setStockQuantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + getIdAsString() +
               ", name='" + name + '\'' +
               ", category='" + category + '\'' +
               ", description=" + description + '\'' +
               ", stock_quantity=" + stock_quantity +
               ", price=" + price +
               ", imageUrl='" + imageUrl + '\'' +
               '}';
    }
}