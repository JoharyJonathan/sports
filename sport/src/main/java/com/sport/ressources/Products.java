package com.sport.ressources;

import java.util.List;

import org.bson.types.ObjectId;

import com.sport.cache.RedisService;
import com.sport.models.Product;
import com.sport.persistence.MongoDBConnection;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Products {

    @Inject
    private MongoDBConnection mongoDB;

    @Inject
    private RedisService redisService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GET
    public Response getAllProducts() {
        try {
            String cacheKey = "products:list";
            String cached = redisService.get(cacheKey);

            if (cached != null) {
                return Response.ok(cached).build();
            }

            List<Product> products = mongoDB.getDatastore()
                                            .find(Product.class)
                                            .iterator()
                                            .toList();

            if (products.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

            String json = mapper.writeValueAsString(products);

            redisService.set(cacheKey, json, 3600);
            return Response.ok(products).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") String id) {
        try {
            String cacheKey = "product:" + id;
            String cached = redisService.get(cacheKey);

            if (cached != null) {
                return Response.ok(cached).build();
            }

            Product product = mongoDB.getDatastore()
                                     .find(Product.class)
                                     .filter("_id", new ObjectId(id))
                                     .first();

            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            String json = mapper.writeValueAsString(product);

            redisService.set(cacheKey, json, 3600);
            return Response.ok(product).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createProduct(Product product) {
        try {
            if (product.getName() == null || product.getPrice() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Product name and price are required")
                               .build();
            }

            mongoDB.getDatastore().save(product);
            redisService.delete("products:list");

            return Response.status(Response.Status.CREATED).entity(product).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") String id, Product updatedProduct) {
        try {
            Product existingProduct = mongoDB.getDatastore()
                                             .find(Product.class)
                                             .filter("_id", new ObjectId(id))
                                             .first();

            if (existingProduct == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setImageUrl(updatedProduct.getImageUrl()); // ✅ Champ image

            mongoDB.getDatastore().save(existingProduct);

            redisService.delete("products:list");
            redisService.delete("product:" + id);

            return Response.ok(existingProduct).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        try {
            Product product = mongoDB.getDatastore()
                                     .find(Product.class)
                                     .filter("_id", new ObjectId(id))
                                     .first();

            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            mongoDB.getDatastore().delete(product);

            redisService.delete("products:list");
            redisService.delete("product:" + id);

            return Response.ok("Product deleted").build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/category/{category}")
    public Response getProductsByCategory(@PathParam("category") String category) {
        try {
            String cacheKey = "products:category:" + category.toLowerCase();
            String cached = redisService.get(cacheKey);

            if (cached != null) {
                return Response.ok(cached).build();
            }

            List<Product> products = mongoDB.getDatastore()
                                            .find(Product.class)
                                            .filter("category", category)
                                            .iterator()
                                            .toList();

            if (products.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

            String json = mapper.writeValueAsString(products);

            redisService.set(cacheKey, json, 3600);
            return Response.ok(products).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}