package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SaveAuthor;
import com.martin.projects.Library.dto.response.AuthorDto;
import com.martin.projects.Library.mapper.AuthorMapper;
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

  @Override
  public List<AuthorDto> findAll() {
    return AuthorMapper.toAuthorDtoList(authorRepository.findAll());
  }

  @Override
  public List<AuthorDto> findAllByName(String name) {
    return AuthorMapper.toAuthorDtoList(authorRepository.findAllByNameContainingIgnoreCase(name));
  }

  @Override
  public List<AuthorDto> findAllByNationality(String nationality) {
    return AuthorMapper.toAuthorDtoList(
        authorRepository.findAllByNationalityContainingIgnoreCase(nationality));
  }

  @Override
  public AuthorDto findById(Long id) {
    return null;
  }

  @Override
  public AuthorDto saveAuthor(SaveAuthor authorDto) {
    return null;
  }

  @Override
  public AuthorDto updateAuthor(Long id, SaveAuthor authorDto) {
    return null;
  }

  @Override
  public void deleteAuthor(Long id) {

  }
}
