package com.example.library.security.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 700000;
    public static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
}
