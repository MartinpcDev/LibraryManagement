package com.martin.projects.Library.persistence.repository;

import com.martin.projects.Library.persistence.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findAllByFullNameContainingIgnoreCase(String fullName);

  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);
}
