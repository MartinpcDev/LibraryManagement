package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SavePrestamo;
import com.martin.projects.Library.dto.request.UpdatePrestamo;
import com.martin.projects.Library.dto.response.PrestamoDto;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.mapper.PrestamoMapper;
import com.martin.projects.Library.persistence.entity.Book;
import com.martin.projects.Library.persistence.entity.Prestamo;
import com.martin.projects.Library.persistence.entity.User;
import com.martin.projects.Library.persistence.repository.BookRepository;
import com.martin.projects.Library.persistence.repository.PrestamoRepository;
import com.martin.projects.Library.persistence.repository.UserRepository;
import com.martin.projects.Library.service.PrestamoService;
import com.martin.projects.Library.util.PrestamoStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PrestamoServiceImpl implements PrestamoService {

  private final PrestamoRepository prestamoRepository;
  private final UserRepository userRepository;
  private final BookRepository bookRepository;

  @Autowired
  public PrestamoServiceImpl(PrestamoRepository prestamoRepository, UserRepository userRepository,
      BookRepository bookRepository) {
    this.prestamoRepository = prestamoRepository;
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<PrestamoDto> findAllPrestamos() {
    return PrestamoMapper.toPrestamoDtoList(prestamoRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<PrestamoDto> findAllByStatus(PrestamoStatus status) {
    return PrestamoMapper.toPrestamoDtoList(prestamoRepository.findAllByStatus(status));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PrestamoDto> findAllByBook(Long bookId) {
    return PrestamoMapper.toPrestamoDtoList(prestamoRepository.findAllByBookId(bookId));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PrestamoDto> findAllByUser(Long userId) {
    return PrestamoMapper.toPrestamoDtoList(prestamoRepository.findAllByUserId(userId));
  }

  @Transactional(readOnly = true)
  @Override
  public PrestamoDto findPrestamoById(Long id) {
    Prestamo prestamo = prestamoRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun Prestamo"));
    return PrestamoMapper.toPrestamoDto(prestamo);
  }

  @Override
  public PrestamoDto createPrestamo(SavePrestamo prestamoDto) {
    User userExists = userRepository.findById(prestamoDto.getUserId())
        .orElseThrow(
            () -> new NotFoundElementException("El user_id no pertenece a ningun usuario"));
    Book bookExists = bookRepository.findById(prestamoDto.getBookId())
        .orElseThrow(
            () -> new NotFoundElementException("El user_id no pertenece a ningun usuario"));
    Prestamo prestamoToCreated = PrestamoMapper.toSavePrestamoEntity(prestamoDto, bookExists,
        userExists);
    return PrestamoMapper.toPrestamoDto(prestamoRepository.save(prestamoToCreated));
  }

  @Override
  public PrestamoDto updatePrestamo(Long id, UpdatePrestamo prestamoDto) {
    Prestamo prestamoExists = prestamoRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun prestamo"));
    User userExists = userRepository.findById(prestamoDto.getUserId())
        .orElseThrow(
            () -> new NotFoundElementException("El user_id no pertenece a ningun usuario"));
    Book bookExists = bookRepository.findById(prestamoDto.getBookId())
        .orElseThrow(
            () -> new NotFoundElementException("El user_id no pertenece a ningun usuario"));
    PrestamoMapper.updatePrestamoEntity(prestamoExists, prestamoDto,
        bookExists, userExists);
    Prestamo prestamoUpdated = prestamoRepository.save(prestamoExists);
    return PrestamoMapper.toPrestamoDto(prestamoUpdated);
  }

  @Override
  public void deletePrestamo(Long id) {
    Prestamo prestamoExists = prestamoRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun prestamo"));
    prestamoRepository.delete(prestamoExists);
  }
}
