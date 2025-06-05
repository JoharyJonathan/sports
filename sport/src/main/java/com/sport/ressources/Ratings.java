package com.sport.ressources;

import com.sport.models.Product;
import com.sport.models.Rating;
import com.sport.models.User;
import com.sport.persistence.MongoDBConnection;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;

@Path("/ratings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Ratings {

    private final Datastore datastore = new MongoDBConnection().getDatastore();

    @POST
    @Path("/add")
    public Response addRating(@QueryParam("userId") String userId,
                              @QueryParam("productId") String productId,
                              @QueryParam("score") float score) {
        User user = datastore.find(User.class).filter("_id", new ObjectId(userId)).first();
        Product product = datastore.find(Product.class).filter("_id", new ObjectId(productId)).first();

        if (user == null || product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User or Product not found").build();
        }

        Rating existing = datastore.find(Rating.class)
                .filter("user", user)
                .filter("product", product)
                .first();

        if (existing != null) {
            return Response.status(Response.Status.CONFLICT).entity("Rating already exists. Use update.").build();
        }

        Rating rating = new Rating(user, product, score);
        datastore.save(rating);

        return Response.ok(rating).build();
    }

    @PUT
    @Path("/edit")
    public Response updateRating(@QueryParam("userId") String userId,
                                 @QueryParam("productId") String productId,
                                 @QueryParam("score") float newScore) {
        User user = datastore.find(User.class).filter("_id", new ObjectId(userId)).first();
        Product product = datastore.find(Product.class).filter("_id", new ObjectId(productId)).first();

        if (user == null || product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User or Product not found").build();
        }

        Rating rating = datastore.find(Rating.class)
                .filter("user", user)
                .filter("product", product)
                .first();

        if (rating == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Rating not found").build();
        }

        rating.setScore(newScore);
        datastore.save(rating);

        return Response.ok(rating).build();
    }

    @GET
    @Path("/product/{productId}")
    public Response getRatingsByProduct(@PathParam("productId") String productId) {
        Product product = datastore.find(Product.class).filter("_id", new ObjectId(productId)).first();

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }

        List<Rating> ratings = datastore.find(Rating.class)
                .filter("product", product)
                .iterator().toList();

        return Response.ok(ratings).build();
    }

    @GET
    @Path("/user/{userId}")
    public Response getRatingsByUser(@PathParam("userId") String userId) {
        User user = datastore.find(User.class).filter("_id", new ObjectId(userId)).first();

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        List<Rating> ratings = datastore.find(Rating.class)
                .filter("user", user)
                .iterator().toList();

        return Response.ok(ratings).build();
    }

    @DELETE
    @Path("/delete")
    public Response deleteRating(@QueryParam("userId") String userId,
                                 @QueryParam("productId") String productId) {
        User user = datastore.find(User.class).filter("_id", new ObjectId(userId)).first();
        Product product = datastore.find(Product.class).filter("_id", new ObjectId(productId)).first();

        if (user == null || product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User or Product not found").build();
        }

        Rating rating = datastore.find(Rating.class)
                .filter("user", user)
                .filter("product", product)
                .first();

        if (rating == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Rating not found").build();
        }

        datastore.delete(rating);
        return Response.ok("Rating deleted successfully").build();
    }

    @GET
    @Path("/{ratingId}")
    public Response getRatingById(@PathParam("ratingId") String ratingId) {
        Rating rating = datastore.find(Rating.class).filter("_id", new ObjectId(ratingId)).first();

        if (rating == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Rating not found").build();
        }

        return Response.ok(rating).build();
    }
}