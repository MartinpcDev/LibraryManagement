package com.martin.projects.Library.persistence.repository;

import com.martin.projects.Library.persistence.entity.Book;
import com.martin.projects.Library.util.BookGender;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByAuthorName(String authorName);

  List<Book> findAllByTitleContainingIgnoreCase(String title);

  List<Book> findAllByEditorialName(String editorialName);

  List<Book> findByGender(BookGender gender);

  List<Book> findAllByPublicationYear(Integer publicationYear);

  Book findByIsbn(String isbn);

  boolean existsByIsbn(String isbn);
}
