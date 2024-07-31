package com.martin.projects.Library.persistence.repository;

import com.martin.projects.Library.persistence.entity.User;
import com.martin.projects.Library.util.UserRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByRole(UserRole role);

  boolean existsUserByUsername(String username);

  Optional<User> findByUsername(String username);
}
