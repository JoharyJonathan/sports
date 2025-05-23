package com.sport.ressources;

import com.sport.models.Comment;
import com.sport.models.Product;
import com.sport.models.User;
import com.sport.persistence.MongoDBConnection;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Comments {

    private final Datastore datastore = new MongoDBConnection().getDatastore();

    // 1. Ajouter un commentaire à un produit
    @POST
    @Path("/add")
    public Response addComment(@QueryParam("userId") String userId,
                               @QueryParam("productId") String productId,
                               String content) {
        User user = datastore.find(User.class).filter("_id", new ObjectId(userId)).first();
        Product product = datastore.find(Product.class).filter("_id", new ObjectId(productId)).first();

        if (user == null || product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User or Product not found").build();
        }

        Comment comment = new Comment(user, product, content, new Date());
        datastore.save(comment);

        return Response.ok(comment).build();
    }

    // 2. Lister les commentaires des utilisateurs pour un produit
    @GET
    @Path("/product/{productId}")
    public Response getCommentsByProduct(@PathParam("productId") String productId) {
        Product product = datastore.find(Product.class).filter("_id", new ObjectId(productId)).first();

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }

        List<Comment> comments = datastore.find(Comment.class)
                .filter("product", product)
                .iterator().toList();

        return Response.ok(comments).build();
    }

    // 3. Lister les commentaires envoyés par un utilisateur
    @GET
    @Path("/user/{userId}")
    public Response getCommentsByUser(@PathParam("userId") String userId) {
        User user = datastore.find(User.class).filter("_id", new ObjectId(userId)).first();

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        List<Comment> comments = datastore.find(Comment.class)
                .filter("user", user)
                .iterator().toList();

        return Response.ok(comments).build();
    }

    // 4. Modifier un commentaire
    @PUT
    @Path("/edit/{commentId}")
    public Response updateComment(@PathParam("commentId") String commentId, String updatedContent) {
        Comment comment = datastore.find(Comment.class).filter("_id", new ObjectId(commentId)).first();

        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Comment not found").build();
        }

        comment.setContent(updatedContent);
        comment.setPublishDate(new Date()); // Mettre à jour la date
        datastore.save(comment);

        return Response.ok(comment).build();
    }

    // 5. Supprimer un commentaire
    @DELETE
    @Path("/delete/{commentId}")
    public Response deleteComment(@PathParam("commentId") String commentId) {
        Comment comment = datastore.find(Comment.class).filter("_id", new ObjectId(commentId)).first();

        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Comment not found").build();
        }

        datastore.delete(comment);
        return Response.ok("Comment deleted successfully").build();
    }

    // 6. Voir le détail d’un commentaire
    @GET
    @Path("/{commentId}")
    public Response getCommentById(@PathParam("commentId") String commentId) {
        Comment comment = datastore.find(Comment.class).filter("_id", new ObjectId(commentId)).first();

        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Comment not found").build();
        }

        return Response.ok(comment).build();
    }
}