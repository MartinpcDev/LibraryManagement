package com.martin.projects.Library.config.security.filter;

import com.martin.projects.Library.exception.JwtExpiredException;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.persistence.entity.User;
import com.martin.projects.Library.persistence.repository.UserRepository;
import com.martin.projects.Library.service.impl.JwtServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtServiceImpl jwtServiceImpl;
  private final UserRepository userRepository;

  @Autowired
  public JwtAuthenticationFilter(JwtServiceImpl jwtServiceImpl, UserRepository userRepository) {
    this.jwtServiceImpl = jwtServiceImpl;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.split(" ")[1];
    String username = jwtServiceImpl.extractUsername(jwt);

    try {

      jwtServiceImpl.validateToken(jwt);

      User user = userRepository.findByUsername(username)
          .orElseThrow(() -> new NotFoundElementException("Usuario con el username no esiste"));

      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          username, null, user.getAuthorities()
      );

      SecurityContextHolder.getContext().setAuthentication(authToken);
    } catch (ExpiredJwtException exception) {
      throw new JwtExpiredException("el jwt esta expirado", exception);
    } catch (Exception e) {
      throw new RuntimeException("JWT invalido", e);
    }

    filterChain.doFilter(request, response);
  }
}
