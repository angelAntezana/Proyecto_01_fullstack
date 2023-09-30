package com.angel.backend_01.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY="sLavaQE+VE+DptoJO3tUcITz+qrOsz8eVEsr4/yptXbfdf68Wbg1TIqtrCv5LjCPNxmz4sA8xGUfynfDbjfck83uMmEaXIXYS/EnVgweQNd3qTuRDUuq6HcBUbw3yDRnZ8nOpvqzw9rynA9x/7e+pW/jGV4hY9ZOogmIBq3e+aPA4b+spHy19D3c5ktSM2HZlXjR4zhdFUbfoas0hbuVf/dZbLxIgKH34q/Fz+DLzKjLZzPG2EzW31stAEs/vVaxe8WGwh7hI4ciXCo9W6ZaZVabeDWPh2BSxElsnfsuuaehP1EA3CIfpX2CMS4AFa53yFCymHKl8kOcyZ8eCbiX0f8Q+AtVA3en/nIK1l+OKAE=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T>T extractClaim(String token,Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
        Map<String,Object>extraClaims,
        UserDetails userDetails
    ){
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *24))
            .signWith(getSignInKey(),SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return(username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
