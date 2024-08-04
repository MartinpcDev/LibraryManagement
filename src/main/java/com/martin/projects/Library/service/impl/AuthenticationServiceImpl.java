package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.AuthenticationRequest;
import com.martin.projects.Library.dto.request.SaveUser;
import com.martin.projects.Library.dto.request.UpdateUser;
import com.martin.projects.Library.dto.response.AuthenticationResponse;
import com.martin.projects.Library.dto.response.UserDto;
import com.martin.projects.Library.exception.DuplicatedNameException;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.mapper.UserMapper;
import com.martin.projects.Library.persistence.entity.User;
import com.martin.projects.Library.persistence.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl {


  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtServiceImpl jwtServiceImpl;

  @Autowired
  public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
      UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtServiceImpl jwtServiceImpl) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtServiceImpl = jwtServiceImpl;
  }

  public AuthenticationResponse login(AuthenticationRequest authRequest) {
    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
            authRequest.getPassword());

    authenticationManager.authenticate(authToken);

    User user = userRepository.findByUsername(authRequest.getUsername())
        .orElseThrow(() -> new NotFoundElementException("El username no pertenece a ningun "
            + "usuario"));

    String jwt = jwtServiceImpl.generateToken(user, generateExtraClaims(user));

    return new AuthenticationResponse(jwt);
  }

  public UserDto register(SaveUser userDto) {
    if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
      throw new DuplicatedNameException("El username ya esta en uso");
    }

    String encodePassword = passwordEncoder.encode(userDto.getPassword());

    User newUser = new User();
    newUser.setFullName(userDto.getFullName());
    newUser.setUsername(userDto.getUsername());
    newUser.setPassword(encodePassword);

    return UserMapper.toUserDto(userRepository.save(newUser));
  }

  public UserDto update(UpdateUser userDto) {
    User userExists = userRepository.findByUsername(userDto.getUsername())
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun usuario"));
    boolean usernameExists = userRepository.existsUserByUsername(userDto.getUsername());
    if (usernameExists && !Objects.equals(userExists.getUsername(), userDto.getUsername())) {
      throw new DuplicatedNameException("El username ya esta en uso");
    }

    String encodePassword = passwordEncoder.encode(userDto.getPassword());
    userDto.setPassword(encodePassword);

    UserMapper.updateEntityUser(userExists, userDto);

    return UserMapper.toUserDto(userRepository.save(userExists));
  }

  private Map<String, Object> generateExtraClaims(User user) {
    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("name", user.getFullName());
    extraClaims.put("role", user.getRole().name());
    extraClaims.put("permissions", user.getAuthorities());
    return extraClaims;
  }
}
