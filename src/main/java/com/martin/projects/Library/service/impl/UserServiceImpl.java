package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SaveUser;
import com.martin.projects.Library.dto.response.UserDto;
import com.martin.projects.Library.exception.DuplicatedNameException;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.mapper.UserMapper;
import com.martin.projects.Library.persistence.entity.User;
import com.martin.projects.Library.persistence.repository.UserRepository;
import com.martin.projects.Library.service.UserService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserDto> findAllUsers() {
    return UserMapper.toUserDtoList(userRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserDto> findAllUsersByName(String name) {
    return UserMapper.toUserDtoList(userRepository.findAllByFullNameContainingIgnoreCase(name));
  }

  @Transactional(readOnly = true)
  @Override
  public UserDto findUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun Usuario"));
    return UserMapper.toUserDto(user);
  }

  @Override
  public UserDto createUser(SaveUser userDto) {
    boolean usernameExists = userRepository.existsByUsername(userDto.getUsername());
    boolean emailExists = userRepository.existsByEmail(userDto.getEmail());
    boolean phoneExists = userRepository.existsByPhone(userDto.getPhone());

    if (usernameExists) {
      throw new DuplicatedNameException("el username ya esta en uso");
    } else if (emailExists) {
      throw new DuplicatedNameException("el email ya esta en uso");
    } else if (phoneExists) {
      throw new DuplicatedNameException("el phone ya esta en uso");
    }

    User user = UserMapper.toUserEntity(userDto);
    User userCreated = userRepository.save(user);

    return UserMapper.toUserDto(userCreated);
  }

  @Override
  public UserDto updateUser(Long id, SaveUser userDto) {
    User userExists = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El usuario requerido no existe"));

    boolean usernameExists = userRepository.existsByUsername(userDto.getUsername());
    boolean emailExists = userRepository.existsByEmail(userDto.getEmail());
    boolean phoneExists = userRepository.existsByPhone(userDto.getPhone());

    if (usernameExists && !Objects.equals(userExists.getUsername(), userDto.getUsername())) {
      throw new DuplicatedNameException("el username ya esta en uso");
    } else if (emailExists && !Objects.equals(userExists.getEmail(), userDto.getEmail())) {
      throw new DuplicatedNameException("el email ya esta en uso");
    } else if (phoneExists && !Objects.equals(userExists.getPhone(), userDto.getPhone())) {
      throw new DuplicatedNameException("el phone ya esta en uso");
    }

    UserMapper.updateEntityUser(userExists, userDto);
    User userUpdated = userRepository.save(userExists);
    return UserMapper.toUserDto(userUpdated);
  }

  @Override
  public void deleteUser(Long id) {
    User userExists = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El usuario requerido no existe"));

    userRepository.delete(userExists);
  }
}
