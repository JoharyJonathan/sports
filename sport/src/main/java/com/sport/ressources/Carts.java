package com.sport.ressources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import com.sport.dto.CartDTO;
import com.sport.mapper.CartMapper;
import com.sport.mapper.CartsMapper;
import com.sport.models.Cart;
import com.sport.models.CartItem;
import com.sport.persistence.MongoDBConnection;

import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
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
    public Response getAllCarts() {
        Datastore datastore = mongoDBConnection.getDatastore();
        List<Cart> carts = datastore.find(Cart.class).iterator().toList();

        List<CartDTO> dtos = carts.stream().map(CartsMapper::toDTO).collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{userId}")
    public Response getCart(@PathParam("userId") String userId) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(Filters.eq("user_id", new ObjectId(userId)))
                .first();

        CartDTO dto = CartMapper.toDTO(cart);

        return Response.ok(dto).build();
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

        CartDTO dto = CartMapper.toDTO(cart);

        return Response.ok(dto).build();
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

    @PATCH
    @Path("/update-status/{userId}")
    public Response updateStatus(@PathParam("userId") String userId) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class)
                .filter(
                    Filters.eq("user_id", new ObjectId(userId)),
                    Filters.eq("status", "pending")
                )
                .first();

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cart not found").build();
        }

        cart.setStatus("paid");

        datastore.save(cart);

        return Response.ok(cart).build();
    }

    @DELETE
    @Path("/delete/{CartId}/{userId}")
    public Response deleteCart(@PathParam("CartId") String ObjectId, @PathParam("user_id") String userId) {
        Datastore datastore = mongoDBConnection.getDatastore();
        Cart cart = datastore.find(Cart.class).
                filter(
                    Filters.eq("user_id", new ObjectId(userId))
                )
                .first();

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cart not found").build();
        }

        datastore.delete(cart);

        return Response.ok(cart).build();
    }

    @DELETE
    @Path("/delete-cart/{CartId}")
    public Response adminDeleteCart(@PathParam("CartId") String cartId) {
        Datastore datastore = mongoDBConnection.getDatastore();
    
        ObjectId objectId;
        try {
            objectId = new ObjectId(cartId);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Cart ID").build();
        }
    
        Cart cart = datastore.find(Cart.class).filter("_id", objectId).first();
    
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cart not found").build();
        }
    
        datastore.delete(cart);
    
        return Response.ok().entity("Cart deleted successfully").build();
    }    
}