package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SaveEditorial;
import com.martin.projects.Library.dto.response.EditorialDto;
import com.martin.projects.Library.service.EditorialService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EditorialServiceImpl implements EditorialService {

  @Override
  public List<EditorialDto> findAll() {
    return List.of();
  }

  @Override
  public List<EditorialDto> findAllByName(String name) {
    return List.of();
  }

  @Override
  public List<EditorialDto> findAllByCountry(String country) {
    return List.of();
  }

  @Override
  public EditorialDto findById(Long id) {
    return null;
  }

  @Override
  public EditorialDto saveEditorial(SaveEditorial editorialDto) {
    return null;
  }

  @Override
  public EditorialDto updateEditorial(Long id, SaveEditorial editorialDto) {
    return null;
  }

  @Override
  public void deleteEditorial(Long id) {

  }
}
