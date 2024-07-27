package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.request.SaveAuthor;
import com.martin.projects.Library.dto.response.AuthorDto;
import com.martin.projects.Library.service.AuthorService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/author")
public class AuthorController {

  private final AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> findAll(@RequestParam(required = false) String name,
      @RequestParam(required = false) String nationality) {
    List<AuthorDto> authorDtos;

    if (StringUtils.hasText(name)) {
      authorDtos = authorService.findAllByName(name);
    } else if (StringUtils.hasText(nationality)) {
      authorDtos = authorService.findAllByNationality(nationality);
    } else {
      authorDtos = authorService.findAll();
    }

    return ResponseEntity.ok(authorDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(authorService.findById(id));
  }

  @PostMapping
  public ResponseEntity<AuthorDto> create(@RequestBody @Valid SaveAuthor authorDto) {
    AuthorDto authorCreated = authorService.saveAuthor(authorDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(authorCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDto> update(@PathVariable("id") Long id,
      @RequestBody @Valid SaveAuthor authorDto) {
    AuthorDto authorUpdated = authorService.updateAuthor(id, authorDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(authorUpdated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
    authorService.deleteAuthor(id);
    Map<String, String> message = new HashMap<>();
    message.put("message", "Author Eliminado Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
  }
}
