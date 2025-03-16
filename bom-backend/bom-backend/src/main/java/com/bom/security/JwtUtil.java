package com.bom.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.*;

import static io.jsonwebtoken.Jwts.*;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "your_secret_key";
    private final byte[] SECRET_KEY_BYTES = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    private final long EXPIRATION_TIME = 86400000; // 1 day

    public String generateToken(String username) {
        return builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY_BYTES)) // Use parserBuilder()
            .build()
            .parseClaimsJws(token)
            .getBody();
}


}
