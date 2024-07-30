package com.martin.projects.Library.persistence.repository;

import com.martin.projects.Library.persistence.entity.Prestamo;
import com.martin.projects.Library.util.PrestamoStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

  List<Prestamo> findAllByBookId(Long id);

  List<Prestamo> findAllByUserId(Long id);

  List<Prestamo> findAllByStatus(PrestamoStatus status);
}
