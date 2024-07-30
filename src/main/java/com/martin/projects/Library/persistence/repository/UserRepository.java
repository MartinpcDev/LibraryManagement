package com.martin.projects.Library.persistence.repository;

import com.martin.projects.Library.persistence.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByFullNameContainingIgnoreCase(String fullName);

  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);

  boolean existsByUsername(String username);
}
