package ru.geekbrains.corelib.services;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.corelib.interfaces.ITokenService;
import ru.geekbrains.corelib.models.UserInfo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class JWTTokenService implements ITokenService {

    @Value("$(jwt.secret)")
    private String JWT_SECRET;

    @Override
    public String generateToken(UserInfo user) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        String compactTokenString = Jwts.builder()
                .claim("id", user.getUserId())
                .claim("sub", user.getUserEmail())
                .claim("roles", user.getRole())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

        return "Bearer " + compactTokenString;
    }

    @Override
    public UserInfo parseToken(String token) throws ExpiredJwtException {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);

        String email = jwsClaims.getBody()
                .getSubject();

        Long userId = jwsClaims.getBody()
                .get("id", Long.class);

        List<String> roles = jwsClaims.getBody()
                .get("roles", List.class);

        return UserInfo.builder()
                .userId(userId)
                .userEmail(email)
                .role(roles)
                .build();
    }
}