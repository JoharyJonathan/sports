package com.sport.ressources;

import java.util.List;

import org.bson.types.ObjectId;

import com.sport.cache.RedisService;
import com.sport.models.User;
import com.sport.persistence.MongoDBConnection;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Users {
    
    @Inject
    private MongoDBConnection mongoDB;

    @Inject
    private RedisService redisService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GET
    public Response getAllUsers() {
        try {
            String cacheKey = "users:list";
            String cachedUsers = redisService.get(cacheKey);

            if (cachedUsers != null) {
                return Response.ok(cachedUsers).build();
            }

            List<User> users = mongoDB.getDatastore()
                                    .find(User.class)
                                    .iterator()
                                    .toList();

            if (users.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

            String json = mapper.writeValueAsString(users);

            redisService.set(cacheKey, json, 3600);

            return Response.ok(users).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") String id) {
        try {
            String cacheKey = "user:" + id;
            String cachedUser = redisService.get(cacheKey);
    
            if (cachedUser != null) {
                // ✅ On retourne une Response contenant du JSON brut
                return Response.ok(cachedUser, MediaType.APPLICATION_JSON).build();
            }
    
            User user = mongoDB.getDatastore()
                             .find(User.class)
                             .filter("_id", new ObjectId(id))
                             .first();
    
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
    
            String json = mapper.writeValueAsString(user);
    
            redisService.set(cacheKey, json, 3600);
    
            // ✅ Toujours retourner une chaîne JSON
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }    

    @POST
    public Response createUser(User user) {
        try {
            if (user.getUsername() == null || user.getEmail() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                             .entity("Username and email are required")
                             .build();
            }

            mongoDB.getDatastore().save(user);
            redisService.delete("users:list");
            
            return Response.status(Response.Status.CREATED)
                         .entity(user)
                         .build();
        } catch (Exception e) {
            return Response.serverError()
                         .entity("Error creating user: " + e.getMessage())
                         .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, User updatedUser) {
        try {
            if (!ObjectId.isValid(id)) {
                return Response.status(Response.Status.BAD_REQUEST)
                             .entity("Invalid ID format")
                             .build();
            }
    
            User existingUser = mongoDB.getDatastore()
                                    .find(User.class)
                                    .filter("_id", new ObjectId(id))
                                    .first();
    
            if (existingUser == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getCategory() != null) {
                existingUser.setCategory(updatedUser.getCategory());
            }
            if (updatedUser.getStatus() != null) {
                existingUser.setStatus(updatedUser.getStatus());
            }

            mongoDB.getDatastore().save(existingUser);

            String cacheKey = "user:" + id;
            String json = mapper.writeValueAsString(existingUser);
            redisService.set(cacheKey, json, 3600);
            redisService.delete("users:list");

            return Response.ok(existingUser).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error updating user: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            User user = mongoDB.getDatastore()
                            .find(User.class)
                            .filter("_id", new ObjectId(id))
                            .first();

            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            mongoDB.getDatastore().delete(user);

            redisService.delete("user:" + id);
            redisService.delete("users:list");

            return Response.ok().entity("User deleted successfully").build();
        } catch (Exception e) {
            return Response.serverError().entity("Error deleting user: " + e.getMessage()).build();
        }
    }    
}