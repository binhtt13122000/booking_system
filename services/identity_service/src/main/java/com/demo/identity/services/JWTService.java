package com.demo.identity.services;

import com.demo.common.Common;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.audience}")
    private String audience;
    @Value("${jwt.expired}")
    private int expired;
    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam(Common.TYPE, Common.TOKEN_TYE)
                .setIssuer(issuer)
                .setAudience(audience)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expired)).compact();
    }
}