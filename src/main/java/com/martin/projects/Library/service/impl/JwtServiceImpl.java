package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.persistence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl {

  @Value("${security.jwt.expiration-minutes}")
  private long EXPIRATION_MINUTES;

  @Value("${security.jwt.secret-key}")
  private String SECRET_KEY;


  public String generateToken(User user, Map<String, Object> extraClaims) {
    Date issueAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(issueAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(user.getUsername())
        .setIssuedAt(issueAt)
        .setExpiration(expiration)
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .signWith(generateKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key generateKey() {
    byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(secretAsBytes);
  }

  public String extractUsername(String jwt) {
    return extraAllClaims(jwt).getSubject();
  }

  private Claims extraAllClaims(String jwt) {
    return Jwts.parserBuilder()
        .setSigningKey(generateKey())
        .build()
        .parseClaimsJws(jwt)
        .getBody();
  }
}
