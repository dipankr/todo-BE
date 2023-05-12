package com.dipankr.todobe.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret_key}")
    private final String SECRET_KEY = null;

    /**
     * Extracts username from the jwt token
     *
     * @param jwtToken JWT token
     * @return username
     */
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    /**
     * Checks if the JWT token is valid by comparing the username in the token with the current user's username
     *
     * @param jwtToken    JWT token
     * @param userDetails User details
     * @return a boolean value to indicate if the token is valid
     */
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String usernameFromToken = extractUsername(jwtToken);
        return (usernameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    /**
     * Checks if the token is expired by comparing the expiration of the token to before the current Date
     *
     * @param jwtToken JWT token
     * @return a flag indication if the token is expired
     */
    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    /**
     * Extracts the expiration date from the given token
     *
     * @param jwtToken JWT token
     * @return the expiration date of the token
     */
    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    /**
     * Generates a token valid for 24hrs for given user without extra claims
     *
     * @param userDetails user details
     * @return JWT token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a token valid for 24hrs for given user
     *
     * @param extraClaims extra claims to set in header
     * @param userDetails user details
     * @return JWT token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Extracts claim from token
     *
     * @param jwtToken      JWT token
     * @param claimResolver a Function to resolve claim?
     * @param <T>           generic
     * @return something?
     */
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    /**
     * Extracts all the claims from the jwt token
     *
     * @param jwtToken JWT token
     * @return all the claims
     */
    private Claims extractAllClaims(String jwtToken) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(jwtToken)
            .getBody();
    }

    /**
     * Return the HMAC SHA key using the SECRET_KEY
     *
     * @return HMAC SHA Key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
