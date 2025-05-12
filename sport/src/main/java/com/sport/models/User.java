package com.sport.models;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

@Entity("users")
public class User {
    @Id
    private ObjectId id;

    @Property("username")
    private String username;

    @Property("email")
    private String email;

    @Property("password")
    private String password;

    @Property("category")
    private String category;

    public User() {

    }

    public User(String username, String email, String password, String category) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.category = category;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "User{" + 
                "id=" + id + 
                ", username='" + username + '\'' +
                ", email='" + email + '\'' + 
                ", category='" + category + '\'' + 
                '}';                
    }

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
}