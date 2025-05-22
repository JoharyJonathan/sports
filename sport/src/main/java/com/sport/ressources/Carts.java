package com.sport.ressources;

import java.util.Optional;

import org.bson.types.ObjectId;

import com.sport.models.Cart;
import com.sport.models.CartItem;
import com.sport.persistence.MongoDBConnection;

import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Carts {

    @Inject
    MongoDBConnection mongoDBConnection;

    @GET
    @Path("/{userId}")
    public Response getCart(@PathParam("userId") String userId) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(Filters.eq("user_id", new ObjectId(userId)))
                .first();

        return Response.ok(cart).build();
    }

    @GET
    @Path("/cart/{userId}")
    public Response getUserCart(@PathParam("userId") String userId) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(
                    Filters.eq("user_id", new ObjectId(userId)),
                    Filters.eq("status", "pending")
                )
                .first();

        if (cart == null) {
            cart = new Cart(new ObjectId(userId));
            datastore.save(cart);
        }

        return Response.ok(cart).build();
    }

    @POST
    @Path("/{userId}")
    public Response addItemToCart(@PathParam("userId") String userId, CartItem newItem) {
        ObjectId userObjectId = new ObjectId(userId);
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(Filters.eq("user_id", userObjectId))
                .first();

        if (cart == null) {
            cart = new Cart(userObjectId);
        }

        cart.addItem(newItem.getProductId(), newItem.getQuantity());
        datastore.save(cart);

        return Response.ok(cart).build();
    }

    @PUT
    @Path("/{userId}")
    public Response updateItemQuantity(@PathParam("userId") String userId, CartItem updatedItem) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(Filters.eq("user_id", new ObjectId(userId)))
                .first();

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cart not found").build();
        }

        Optional<CartItem> itemOpt = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(updatedItem.getProductId()))
                .findFirst();

        if (itemOpt.isPresent()) {
            itemOpt.get().setQuantity(updatedItem.getQuantity());
            datastore.save(cart);
            return Response.ok(cart).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found in cart").build();
        }
    }

    @DELETE
    @Path("/{userId}/{productId}")
    public Response removeItem(@PathParam("userId") String userId, @PathParam("productId") String productId) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(Filters.eq("user_id", new ObjectId(userId)))
                .first();

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cart not found").build();
        }

        cart.removeItem(new ObjectId(productId));
        datastore.save(cart);
        return Response.ok(cart).build();
    }

    @DELETE
    @Path("/{userId}")
    public Response clearCart(@PathParam("userId") String userId) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(Filters.eq("user_id", new ObjectId(userId)))
                .first();

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cart not found").build();
        }

        cart.getItems().clear();
        datastore.save(cart);

        return Response.ok("Cart has been cleared").build();
    }
}