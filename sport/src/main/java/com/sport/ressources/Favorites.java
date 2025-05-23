package com.sport.ressources;

import com.sport.models.Favorite;
import com.sport.models.Product;
import com.sport.models.User;
import com.sport.persistence.MongoDBConnection;

import dev.morphia.Datastore;
import dev.morphia.query.Query;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/favorites")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Favorites {

    private final Datastore datastore = new MongoDBConnection().getDatastore();

    @POST
    @Path("/add")
    public Response addToFavorites(@QueryParam("userId") String userId, @QueryParam("productId") String productId) {
        User user = datastore.find(User.class).filter("_id", new org.bson.types.ObjectId(userId)).first();
        Product product = datastore.find(Product.class).filter("_id", new org.bson.types.ObjectId(productId)).first();

        if (user == null || product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User or Product not found").build();
        }

        // Vérifier si le favori existe déjà
        Query<Favorite> existing = datastore.find(Favorite.class)
            .filter("user", user)
            .filter("product", product);
        if (existing.first() != null) {
            return Response.status(Response.Status.CONFLICT).entity("Product already in favorites").build();
        }

        Favorite favorite = new Favorite(user, product);
        datastore.save(favorite);
        return Response.ok(favorite).build();
    }

    @DELETE
    @Path("/remove")
    public Response removeFromFavorites(@QueryParam("userId") String userId, @QueryParam("productId") String productId) {
        User user = datastore.find(User.class).filter("_id", new org.bson.types.ObjectId(userId)).first();
        Product product = datastore.find(Product.class).filter("_id", new org.bson.types.ObjectId(productId)).first();

        if (user == null || product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User or Product not found").build();
        }

        Query<Favorite> query = datastore.find(Favorite.class)
            .filter("user", user)
            .filter("product", product);

        Favorite favorite = query.first();

        if (favorite == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Favorite not found").build();
        }

        datastore.delete(favorite);
        return Response.ok("Removed from favorites").build();
    }

    @GET
    @Path("/user/{userId}")
    public Response getFavoritesByUser(@PathParam("userId") String userId) {
        User user = datastore.find(User.class).filter("_id", new org.bson.types.ObjectId(userId)).first();

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        List<Favorite> favorites = datastore.find(Favorite.class)
            .filter("user", user)
            .iterator()
            .toList();

        return Response.ok(favorites).build();
    }

    @GET
    @Path("/count/{productId}")
    public Response getFavoriteCountForProduct(@PathParam("productId") String productId) {
        Product product = datastore.find(Product.class).filter("_id", new org.bson.types.ObjectId(productId)).first();

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }

        long count = datastore.find(Favorite.class)
            .filter("product", product)
            .count();

        return Response.ok("{\"count\": " + count + "}").build();
    }

    @GET
    @Path("/users/{productId}")
    public Response getUsersWhoFavoritedProduct(@PathParam("productId") String productId) {
        Product product = datastore.find(Product.class).filter("_id", new org.bson.types.ObjectId(productId)).first();

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }

        List<Favorite> favorites = datastore.find(Favorite.class)
            .filter("product", product)
            .iterator()
            .toList();

        List<User> users = favorites.stream()
            .map(Favorite::getUser)
            .toList();

        return Response.ok(users).build();
    }

    @DELETE
    @Path("/delete/{favoriteId}")
    public Response deleteFavorite(@PathParam("favoriteId") String favoriteId) {
        Favorite favorite = datastore.find(Favorite.class).filter("_id", new org.bson.types.ObjectId(favoriteId)).first();

        if (favorite == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Favorite not found").build();
        }

        datastore.delete(favorite);

        return Response.ok(favorite).build();
    }

    @DELETE
    @Path("/clear/{userId}")
    public Response clearFavoritesByUser(@PathParam("userId") String userId) {
        User user = datastore.find(User.class).filter("_id", new org.bson.types.ObjectId(userId)).first();

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        Query<Favorite> favoritesQuery = datastore.find(Favorite.class)
            .filter("user", user);

        long deletedCount = datastore.delete(favoritesQuery).getDeletedCount();

        return Response.ok("Deleted " + deletedCount + " favorites for user").build();
    }
}