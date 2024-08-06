package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SaveBook;
import com.martin.projects.Library.dto.request.UploadImage;
import com.martin.projects.Library.dto.response.BookDto;
import com.martin.projects.Library.exception.ImageNotFoundException;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.mapper.BookMapper;
import com.martin.projects.Library.persistence.entity.Author;
import com.martin.projects.Library.persistence.entity.Book;
import com.martin.projects.Library.persistence.entity.Editorial;
import com.martin.projects.Library.persistence.repository.AuthorRepository;
import com.martin.projects.Library.persistence.repository.BookRepository;
import com.martin.projects.Library.persistence.repository.EditorialRepository;
import com.martin.projects.Library.service.BookService;
import com.martin.projects.Library.service.CloudinaryService;
import com.martin.projects.Library.util.BookGender;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final AuthorRepository authorRepository;

  private final EditorialRepository editorialRepository;

  private final CloudinaryService cloudinaryService;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
      EditorialRepository editorialRepository, CloudinaryService cloudinaryService) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.editorialRepository = editorialRepository;
    this.cloudinaryService = cloudinaryService;
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookDto> findAll() {
    return BookMapper.toBookDtoList(bookRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookDto> findAllByAuthorName(String authorName) {
    List<Book> bookList = bookRepository.findAllByAuthorName(authorName);
    return BookMapper.toBookDtoList(bookList);
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookDto> findAllByTitle(String title) {
    List<Book> bookList = bookRepository.findAllByTitleContainingIgnoreCase(title);
    return BookMapper.toBookDtoList(bookList);
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookDto> findAllByEditorialName(String editorialName) {
    List<Book> bookList = bookRepository.findAllByEditorialName(editorialName);
    return BookMapper.toBookDtoList(bookList);
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookDto> findAllByGender(BookGender gender) {
    List<Book> bookList = bookRepository.findAllByGender(gender);
    return BookMapper.toBookDtoList(bookList);
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookDto> findAllByReleaseYear(Integer publicationYear) {
    List<Book> bookList = bookRepository.findAllByPublicationYear(publicationYear);
    return BookMapper.toBookDtoList(bookList);
  }

  @Transactional(readOnly = true)
  @Override
  public BookDto findById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun libro"));
    return BookMapper.toBookDto(book);
  }

  @Override
  public BookDto saveBook(SaveBook bookDto) {
    Author authorExists = authorRepository.findById(bookDto.getAuthorId())
        .orElseThrow(
            () -> new NotFoundElementException("El author_id no pertenece a ningun autor"));
    Editorial editorialExists = editorialRepository.findById(bookDto.getEditorialId())
        .orElseThrow(() -> new NotFoundElementException("El editorial_id not pertenece a ninguna "
            + "editorial"));
    Book book = BookMapper.toBookEntity(bookDto, authorExists, editorialExists);
    Book bookCreated = bookRepository.save(book);
    return BookMapper.toBookDto(bookCreated);
  }

  @Override
  public BookDto uploadBookImage(Long id, UploadImage imageDto) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun libro"));

    if (imageDto.getFile().isEmpty()) {
      throw new ImageNotFoundException("Se debe proporcionar una imagen");
    }

    book.setUrl(cloudinaryService.uploadFile(imageDto.getFile()));

    if (book.getUrl() == null) {
      throw new ImageNotFoundException("Se debe proporcionar una imagen");
    }

    bookRepository.save(book);

    return BookMapper.toBookDto(book);
  }

  @Override
  public BookDto updateBookById(Long id, SaveBook bookDto) {
    Optional<Book> existingBookOpt = bookRepository.findById(id);

    if (existingBookOpt.isPresent()) {
      Author authorExists = authorRepository.findById(bookDto.getAuthorId())
          .orElseThrow(
              () -> new NotFoundElementException("El author_id no pertenece a ningun autor"));
      Editorial editorialExists = editorialRepository.findById(bookDto.getEditorialId())
          .orElseThrow(() -> new NotFoundElementException("El editorial_id not pertenece a ninguna "
              + "editorial"));

      BookMapper.updateBookEntity(existingBookOpt.get(), bookDto, authorExists, editorialExists);
      Book updatedBook = bookRepository.save(existingBookOpt.get());
      return BookMapper.toBookDto(updatedBook);
    } else {
      throw new NotFoundElementException("El id no pertenece a ningun Book");
    }
  }

  @Override
  public void deleteBookById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El id no pertenece a ningun libro"));
    bookRepository.delete(book);
  }
}
