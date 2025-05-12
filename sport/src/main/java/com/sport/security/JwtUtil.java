package com.sport.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "johary";
    private static final long EXPIRATION_TIME = 86400000;

    public static String generateToken(String userId, String email) {
        return JWT.create()
            .withSubject(userId)
            .withClaim("email", email)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(SECRET));
    }
}