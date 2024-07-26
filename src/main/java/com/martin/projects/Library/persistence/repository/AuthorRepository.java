package com.martin.projects.Library.persistence.repository;

import com.martin.projects.Library.persistence.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  List<Author> findAllByNameContainingIgnoreCase(String name);

  List<Author> findAllByNationalityContainingIgnoreCase(String nationality);
}
