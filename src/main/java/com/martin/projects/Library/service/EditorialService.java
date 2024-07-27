package com.martin.projects.Library.service;

import com.martin.projects.Library.dto.request.SaveEditorial;
import com.martin.projects.Library.dto.response.EditorialDto;
import java.util.List;

public interface EditorialService {

  List<EditorialDto> findAll();

  List<EditorialDto> findAllByName(String name);

  List<EditorialDto> findAllByCountry(String country);

  EditorialDto findById(Long id);

  EditorialDto saveEditorial(SaveEditorial editorialDto);

  EditorialDto updateEditorial(Long id, SaveEditorial editorialDto);

  void deleteEditorial(Long id);
}
