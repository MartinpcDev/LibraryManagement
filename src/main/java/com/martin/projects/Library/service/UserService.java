package com.martin.projects.Library.service;

import com.martin.projects.Library.dto.request.SaveUser;
import com.martin.projects.Library.dto.request.UpdateUser;
import com.martin.projects.Library.dto.response.UserDto;
import com.martin.projects.Library.util.UserRole;
import java.util.List;

public interface UserService {

  List<UserDto> findAllUsers();

  List<UserDto> findAllByRole(UserRole role);

  UserDto findUserById(Long id);

  UserDto createUser(SaveUser userDto);

  UserDto updateUser(Long id, UpdateUser userDto);

  void deleteUser(Long id);
}
