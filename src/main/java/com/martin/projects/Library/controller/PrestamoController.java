package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.request.SavePrestamo;
import com.martin.projects.Library.dto.request.UpdatePrestamo;
import com.martin.projects.Library.dto.response.PrestamoDto;
import com.martin.projects.Library.service.PrestamoService;
import com.martin.projects.Library.util.PrestamoStatus;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestamo")
@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
public class PrestamoController {

  private final PrestamoService prestamoService;

  @Autowired
  public PrestamoController(PrestamoService prestamoService) {
    this.prestamoService = prestamoService;
  }

  @GetMapping
  public ResponseEntity<List<PrestamoDto>> findAll(
      @RequestParam(value = "status", required = false) String status) {

    List<PrestamoDto> prestamoDtoList;

    if (StringUtils.hasText(status)) {
      prestamoDtoList = prestamoService.findAllByStatus(
          PrestamoStatus.valueOf(status.toUpperCase()));
    } else {
      prestamoDtoList = prestamoService.findAllPrestamos();
    }

    return ResponseEntity.ok(prestamoDtoList);
  }

  @GetMapping("/book/{bookId}")
  public ResponseEntity<List<PrestamoDto>> findAllByBook(@PathVariable("bookId") Long bookId) {
    return ResponseEntity.ok(prestamoService.findAllByBook(bookId));
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<PrestamoDto>> findAllByUser(
      @PathVariable("customerId") Long customerId) {
    return ResponseEntity.ok(prestamoService.findAllByCustomer(customerId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PrestamoDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(prestamoService.findPrestamoById(id));
  }

  @PostMapping
  public ResponseEntity<PrestamoDto> create(@RequestBody @Valid SavePrestamo prestamoDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(prestamoService.createPrestamo(prestamoDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PrestamoDto> update(@PathVariable("id") Long id,
      @RequestBody @Valid UpdatePrestamo prestamoDto) {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(prestamoService.updatePrestamo(id, prestamoDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
    prestamoService.deletePrestamo(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Prestamo Eliminado Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
