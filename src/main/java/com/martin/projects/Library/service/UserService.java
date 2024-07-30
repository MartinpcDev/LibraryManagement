package com.martin.projects.Library.service;

import com.martin.projects.Library.dto.request.SaveUser;
import com.martin.projects.Library.dto.response.UserDto;
import java.util.List;

public interface UserService {

  List<UserDto> findAllUsers();

  List<UserDto> findAllUsersByName(String name);

  UserDto findUserById(Long id);

  UserDto createUser(SaveUser userDto);

  UserDto updateUser(Long id, SaveUser userDto);

  void deleteUser(Long id);
}
