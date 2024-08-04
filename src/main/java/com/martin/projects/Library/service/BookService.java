package com.martin.projects.Library.service;

import com.martin.projects.Library.dto.request.SaveBook;
import com.martin.projects.Library.dto.request.UploadImage;
import com.martin.projects.Library.dto.response.BookDto;
import com.martin.projects.Library.util.BookGender;
import java.util.List;

public interface BookService {

  List<BookDto> findAll();

  List<BookDto> findAllByAuthorName(String authorName);

  List<BookDto> findAllByTitle(String title);

  List<BookDto> findAllByEditorialName(String editorialName);

  List<BookDto> findAllByGender(BookGender gender);

  List<BookDto> findAllByReleaseYear(Integer publicationYear);

  BookDto findById(Long id);

  BookDto saveBook(SaveBook bookDto);

  BookDto uploadBookImage(Long id, UploadImage imageDto);

  BookDto updateBookById(Long id, SaveBook bookDto);

  void deleteBookById(Long id);
}
