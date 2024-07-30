package com.martin.projects.Library.mapper;

import com.martin.projects.Library.dto.request.SaveUser;
import com.martin.projects.Library.dto.response.UserDto;
import com.martin.projects.Library.persistence.entity.User;
import java.util.List;

public class UserMapper {

  public static UserDto toUserDto(User user) {
    if (user == null) {
      return null;
    }

    return new UserDto(
        user.getId(),
        user.getFullName(),
        user.getUsername(),
        user.getPhone(),
        user.getEmail(),
        user.getCreatedAt()
    );
  }

  public static List<UserDto> toUserDtoList(List<User> users) {
    if (users == null) {
      return null;
    }

    return users.stream()
        .map(UserMapper::toUserDto)
        .toList();
  }

  public static User toUserEntity(SaveUser userDto) {
    if (userDto == null) {
      return null;
    }

    User user = new User();
    user.setFullName(userDto.getFullName());
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    user.setPhone(userDto.getPhone());
    user.setEmail(userDto.getEmail());

    return user;
  }

  public static void updateEntityUser(User oldUser, SaveUser userDto) {
    if (oldUser == null || userDto == null) {
      return;
    }

    oldUser.setFullName(userDto.getFullName());
    oldUser.setUsername(userDto.getUsername());
    oldUser.setPassword(userDto.getPassword());
    oldUser.setPhone(userDto.getPhone());
    oldUser.setEmail(userDto.getEmail());
  }
}
