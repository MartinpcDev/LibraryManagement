package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.request.SaveBook;
import com.martin.projects.Library.dto.response.BookDto;
import com.martin.projects.Library.service.BookService;
import com.martin.projects.Library.util.BookGender;
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
@RequestMapping("/book")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> findAll(
      @RequestParam(required = false, value = "author_name") String authorName,
      @RequestParam(required = false) String title,
      @RequestParam(required = false, value = "editorial_name") String editorialName,
      @RequestParam(required = false) String gender,
      @RequestParam(required = false, value = "release_year") Integer publicactionYear) {

    List<BookDto> bookDtoList;
    if (StringUtils.hasText(authorName)) {
      bookDtoList = bookService.findAllByAuthorName(authorName);
    } else if (StringUtils.hasText(title)) {
      bookDtoList = bookService.findAllByTitle(title);
    } else if (StringUtils.hasText(editorialName)) {
      bookDtoList = bookService.findAllByEditorialName(editorialName);
    } else if (StringUtils.hasText(gender)) {
      bookDtoList = bookService.findAllByGender(BookGender.valueOf(gender.toUpperCase()));
    } else if (publicactionYear != null) {
      bookDtoList = bookService.findAllByReleaseYear(publicactionYear);
    } else {
      bookDtoList = bookService.findAll();
    }

    return ResponseEntity.ok(bookDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> findbyId(@PathVariable("id") Long id) {
    BookDto bookDto = bookService.findById(id);
    return ResponseEntity.ok(bookDto);
  }

  @PostMapping
  public ResponseEntity<BookDto> create(@RequestBody @Valid SaveBook bookDto) {
    BookDto createdBook = bookService.saveBook(bookDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> updateById(@PathVariable("id") Long id,
      @RequestBody @Valid SaveBook bookDto) {
    BookDto updatedBook = bookService.updateBookById(id, bookDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedBook);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> deleteById(@PathVariable("id") Long id) {
    bookService.deleteBookById(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Book Eliminado Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
