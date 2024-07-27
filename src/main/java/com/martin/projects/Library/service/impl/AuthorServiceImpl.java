package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SaveAuthor;
import com.martin.projects.Library.dto.response.AuthorDto;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.mapper.AuthorMapper;
import com.martin.projects.Library.persistence.entity.Author;
import com.martin.projects.Library.persistence.repository.AuthorRepository;
import com.martin.projects.Library.service.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<AuthorDto> findAll() {
    return AuthorMapper.toAuthorDtoList(authorRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<AuthorDto> findAllByName(String name) {
    return AuthorMapper.toAuthorDtoList(authorRepository.findAllByNameContainingIgnoreCase(name));
  }

  @Transactional(readOnly = true)
  @Override
  public List<AuthorDto> findAllByNationality(String nationality) {
    return AuthorMapper.toAuthorDtoList(
        authorRepository.findAllByNationalityContainingIgnoreCase(nationality));
  }

  @Transactional(readOnly = true)
  @Override
  public AuthorDto findById(Long id) {
    Author authorExists = authorRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El author a buscar no existe"));
    return AuthorMapper.toAuthorDto(authorExists);
  }

  @Override
  public AuthorDto saveAuthor(SaveAuthor authorDto) {
    Author authorEntity = AuthorMapper.toAuthorEntity(authorDto);
    Author authorCreated = authorRepository.save(authorEntity);
    return AuthorMapper.toAuthorDto(authorCreated);
  }

  @Override
  public AuthorDto updateAuthor(Long id, SaveAuthor authorDto) {
    Author authorExists = authorRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El author a actualizar no existe"));
    AuthorMapper.updateAuthorEntity(authorExists, authorDto);
    Author authorUpdated = authorRepository.save(authorExists);
    return AuthorMapper.toAuthorDto(authorUpdated);
  }

  @Override
  public void deleteAuthor(Long id) {
    Author authorExists = authorRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("El author a eliminar no existe"));
    authorRepository.delete(authorExists);
  }
}
