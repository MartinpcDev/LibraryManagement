package com.martin.projects.Library.config.security;

import com.martin.projects.Library.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class HttpSecurityConfig {

  private final AuthenticationProvider authenticationProvider;
  private final JwtAuthenticationFilter authenticationFilter;

  @Autowired
  public HttpSecurityConfig(AuthenticationProvider authenticationProvider,
      JwtAuthenticationFilter authenticationFilter) {
    this.authenticationProvider = authenticationProvider;
    this.authenticationFilter = authenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(Customizer.withDefaults())
        .build();
  }
}
