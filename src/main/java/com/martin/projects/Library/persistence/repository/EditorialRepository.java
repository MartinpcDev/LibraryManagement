package com.martin.projects.Library.persistence.repository;

import com.martin.projects.Library.persistence.entity.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {

  List<Editorial> findAllByNameContainingIgnoreCase(String name);

  List<Editorial> findAllByCountryContainingIgnoreCase(String country);
}
