package com.sport.ressources;

import org.mindrot.jbcrypt.BCrypt;
import dev.morphia.query.filters.Filters;

import com.sport.models.User;
import com.sport.persistence.MongoDBConnection;
import com.sport.security.JwtUtil;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Authe {
    
    @Inject
    private MongoDBConnection mongoDB;

    @POST
    @Path("/signup")
    public Response signup(User user) {
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Email and password required")
                            .build();
            }

            boolean exists = mongoDB.getDatastore()
                .find(User.class)
                .filter(Filters.eq("email", user.getEmail()))
                .first() != null;

            if (exists) {
                return Response.status(Response.Status.CONFLICT)
                            .entity("User already exists")
                            .build();
            }

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            mongoDB.getDatastore().save(user);

            return Response.status(Response.Status.CREATED).entity("User created").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        try {
            User foundUser = mongoDB.getDatastore()
                .find(User.class)
                .filter(Filters.eq("email", user.getEmail()))
                .first();

            if (foundUser == null || !BCrypt.checkpw(user.getPassword(), foundUser.getPassword())) {
                return Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid credentials")
                            .build();
            }

            String token = JwtUtil.generateToken(foundUser.getId().toString(), foundUser.getEmail());

            return Response.ok().entity("{\"token\": \"" + token + "\"}").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
