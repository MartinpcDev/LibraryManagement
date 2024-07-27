package com.martin.projects.Library.service;

import com.martin.projects.Library.dto.request.SaveAuthor;
import com.martin.projects.Library.dto.response.AuthorDto;
import java.util.List;

public interface AuthorService {

  List<AuthorDto> findAll();

  List<AuthorDto> findAllByName(String name);

  List<AuthorDto> findAllByNationality(String nationality);

  AuthorDto findById(Long id);

  AuthorDto saveAuthor(SaveAuthor authorDto);

  AuthorDto updateAuthor(Long id, SaveAuthor authorDto);

  void deleteAuthor(Long id);
}
