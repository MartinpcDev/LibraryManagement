package com.martin.projects.Library.service;

import com.martin.projects.Library.dto.request.SavePrestamo;
import com.martin.projects.Library.dto.request.UpdatePrestamo;
import com.martin.projects.Library.dto.response.PrestamoDto;
import com.martin.projects.Library.util.PrestamoStatus;
import java.util.List;

public interface PrestamoService {

  List<PrestamoDto> findAllPrestamos();

  List<PrestamoDto> findAllByStatus(PrestamoStatus status);

  List<PrestamoDto> findAllByBook(Long bookId);

  List<PrestamoDto> findAllByCustomer(Long userId);

  PrestamoDto findPrestamoById(Long id);

  PrestamoDto createPrestamo(SavePrestamo prestamoDto);

  PrestamoDto updatePrestamo(Long id, UpdatePrestamo prestamoDto);

  void deletePrestamo(Long id);
}
