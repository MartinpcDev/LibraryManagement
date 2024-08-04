package com.martin.projects.Library.mapper;

import com.martin.projects.Library.dto.request.SaveBook;
import com.martin.projects.Library.dto.response.BookDto;
import com.martin.projects.Library.persistence.entity.Author;
import com.martin.projects.Library.persistence.entity.Book;
import com.martin.projects.Library.persistence.entity.Editorial;
import com.martin.projects.Library.util.BookGender;
import java.util.List;

public class BookMapper {

  public static BookDto toBookDto(Book book) {
    if (book == null) {
      return null;
    }

    return new BookDto(
        book.getId(),
        book.getTitle(),
        book.getIsbn(),
        book.getUrl(),
        book.getPublicationYear(),
        book.getGender(),
        book.getStock(),
        AuthorMapper.toAuthorDto(book.getAuthor()),
        EditorialMapper.toEditorialDto(book.getEditorial())
    );
  }

  public static List<BookDto> toBookDtoList(List<Book> books) {
    if (books == null) {
      return null;
    }

    return books.stream()
        .map(BookMapper::toBookDto)
        .toList();
  }

  public static Book toBookEntity(SaveBook bookDto, Author author, Editorial editorial) {
    if (bookDto == null || author == null || editorial == null) {
      return null;
    }

    Book book = new Book();
    book.setTitle(bookDto.getTitle());
    book.setIsbn(bookDto.getIsbn());
    book.setPublicationYear(bookDto.getPublicationYear());
    book.setGender(BookGender.valueOf(bookDto.getGender().toUpperCase()));
    book.setStock(bookDto.getStock());
    book.setAuthor(author);
    book.setEditorial(editorial);

    return book;
  }

  public static void updateBookEntity(Book oldBook, SaveBook bookDto, Author author,
      Editorial editorial) {
    if (oldBook == null || bookDto == null || author == null || editorial == null) {
      return;
    }

    oldBook.setTitle(bookDto.getTitle());
    oldBook.setIsbn(bookDto.getIsbn());
    oldBook.setPublicationYear(bookDto.getPublicationYear());
    oldBook.setGender(BookGender.valueOf(bookDto.getGender().toUpperCase()));
    oldBook.setStock(bookDto.getStock());
    oldBook.setAuthor(author);
    oldBook.setEditorial(editorial);
  }
}
