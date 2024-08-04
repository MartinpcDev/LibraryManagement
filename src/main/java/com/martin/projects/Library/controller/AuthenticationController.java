package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.request.AuthenticationRequest;
import com.martin.projects.Library.dto.request.SaveUser;
import com.martin.projects.Library.dto.request.UpdateUser;
import com.martin.projects.Library.dto.response.AuthenticationResponse;
import com.martin.projects.Library.dto.response.UserDto;
import com.martin.projects.Library.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationServiceImpl authenticationServiceImpl;

  @Autowired
  public AuthenticationController(AuthenticationServiceImpl authenticationServiceImpl) {
    this.authenticationServiceImpl = authenticationServiceImpl;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok(authenticationServiceImpl.login(authenticationRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<UserDto> register(@RequestBody @Valid SaveUser userDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authenticationServiceImpl.register(userDto));
  }

  @PutMapping("/update")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDto> update(@RequestBody @Valid UpdateUser userDto) {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(authenticationServiceImpl.update(userDto));
  }
}
