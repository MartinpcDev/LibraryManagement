package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.response.UserDto;
import com.martin.projects.Library.service.UserService;
import com.martin.projects.Library.util.UserRole;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> findAll(@RequestParam(required = false) String role) {
    List<UserDto> userDtoList;

    if (StringUtils.hasText(role)) {
      userDtoList = userService.findAllByRole(UserRole.valueOf(role.toUpperCase()));
    } else {
      userDtoList = userService.findAllUsers();
    }

    return ResponseEntity.ok(userDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(userService.findUserById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
    userService.deleteUser(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "User eliminado Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
