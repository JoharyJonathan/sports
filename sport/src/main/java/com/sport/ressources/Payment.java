package com.sport.ressources;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Payment {

    static {
        Dotenv dotenv = Dotenv.load();
        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");
    }

    public static class PaymentRequest {
        public Long amount;
        public String currency;
        public String successUrl;
        public String cancelUrl;
    }

    @POST
    public Response createCheckoutSession(PaymentRequest request) {
        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(request.successUrl)
                    .setCancelUrl(request.cancelUrl)
                    .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency(request.currency)
                                            .setUnitAmount(request.amount)
                                            .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName("Panier sport")
                                                        .build()
                                            )
                                            .build()
                                )
                                .build()
                    )
                    .build();

            Session session = Session.create(params);

            return Response.ok("{\"url\": \"" + session.getUrl() + "\"}").build();
        } catch (StripeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}