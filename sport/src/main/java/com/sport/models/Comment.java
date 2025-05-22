package com.sport.models;

import java.util.Date;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Reference;

@Entity("comments")
public class Comment {

    @Id
    private ObjectId id;

    @Reference
    private User user;

    @Reference
    private Product product;

    @Property("content")
    private String content;

    @Property("publish_date")
    private Date publishDate;

    public Comment() {
    }

    public Comment(User user, Product product, String content, Date publishDate) {
        this.user = user;
        this.product = product;
        this.content = content;
        this.publishDate = publishDate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + getIdAsString() +
               ", user=" + (user != null ? user.getIdAsString() : "null") +
               ", product=" + (product != null ? product.getIdAsString() : "null") +
               ", content='" + content + '\'' +
               ", publishDate=" + publishDate +
               '}';
    }
}