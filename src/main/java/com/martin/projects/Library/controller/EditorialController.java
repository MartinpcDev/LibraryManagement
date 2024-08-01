package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.request.SaveEditorial;
import com.martin.projects.Library.dto.response.EditorialDto;
import com.martin.projects.Library.service.EditorialService;
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
@RequestMapping("/editorial")
@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
public class EditorialController {

  private final EditorialService editorialService;

  @Autowired
  public EditorialController(EditorialService editorialService) {
    this.editorialService = editorialService;
  }

  @GetMapping
  public ResponseEntity<List<EditorialDto>> findAll(@RequestParam(required = false) String name,
      @RequestParam(required = false) String country) {
    List<EditorialDto> editorialDtos;

    if (StringUtils.hasText(name)) {
      editorialDtos = editorialService.findAllByName(name);
    } else if (StringUtils.hasText(country)) {
      editorialDtos = editorialService.findAllByCountry(country);
    } else {
      editorialDtos = editorialService.findAll();
    }

    return ResponseEntity.ok(editorialDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EditorialDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(editorialService.findById(id));
  }

  @PostMapping
  public ResponseEntity<EditorialDto> create(@RequestBody @Valid SaveEditorial editorialDto) {
    EditorialDto newEditorial = editorialService.saveEditorial(editorialDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(newEditorial);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EditorialDto> update(@PathVariable("id") Long id,
      @RequestBody @Valid SaveEditorial editorialDto) {
    EditorialDto updatedEditorial = editorialService.updateEditorial(id, editorialDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedEditorial);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
    editorialService.deleteEditorial(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Editorial Eliminada Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
