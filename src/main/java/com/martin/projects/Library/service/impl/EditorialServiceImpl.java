package com.martin.projects.Library.service.impl;

import com.martin.projects.Library.dto.request.SaveEditorial;
import com.martin.projects.Library.dto.response.EditorialDto;
import com.martin.projects.Library.exception.DuplicatedNameEditorialException;
import com.martin.projects.Library.exception.NotFoundElementException;
import com.martin.projects.Library.mapper.EditorialMapper;
import com.martin.projects.Library.persistence.entity.Editorial;
import com.martin.projects.Library.persistence.repository.EditorialRepository;
import com.martin.projects.Library.service.EditorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EditorialServiceImpl implements EditorialService {

  private final EditorialRepository editorialRepository;

  @Autowired
  public EditorialServiceImpl(EditorialRepository editorialRepository) {
    this.editorialRepository = editorialRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<EditorialDto> findAll() {
    return EditorialMapper.toEditorialDtoList(editorialRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<EditorialDto> findAllByName(String name) {
    return EditorialMapper.toEditorialDtoList(
        editorialRepository.findAllByNameContainingIgnoreCase(name));
  }

  @Transactional(readOnly = true)
  @Override
  public List<EditorialDto> findAllByCountry(String country) {
    return EditorialMapper.toEditorialDtoList(
        editorialRepository.findAllByCountryContainingIgnoreCase(country));
  }

  @Transactional(readOnly = true)
  @Override
  public EditorialDto findById(Long id) {
    Editorial editorialExists = editorialRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("La editorial a buscar no existe"));

    return EditorialMapper.toEditorialDto(editorialExists);
  }

  @Override
  public EditorialDto saveEditorial(SaveEditorial editorialDto) {
    boolean editorialNameExists = editorialRepository.existsByName(editorialDto.getName());

    if (editorialNameExists) {
      throw new DuplicatedNameEditorialException("El nombre de la Editorial ya existe");
    }

    Editorial editorialEntity = EditorialMapper.toEditorialEntity(editorialDto);
    Editorial editorialCreated = editorialRepository.save(editorialEntity);

    return EditorialMapper.toEditorialDto(editorialCreated);
  }

  @Override
  public EditorialDto updateEditorial(Long id, SaveEditorial editorialDto) {
    Editorial editorialExists = editorialRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("La editorial a actualizar no existe"));

    if (!editorialExists.getName().equals(editorialDto.getName())
        && editorialRepository.existsByName(editorialDto.getName())) {
      throw new DuplicatedNameEditorialException("El name de la Editorial ya existe");
    }

    EditorialMapper.updateEditorialEntity(editorialExists, editorialDto);
    Editorial editorialUpdated = editorialRepository.save(editorialExists);

    return EditorialMapper.toEditorialDto(editorialUpdated);
  }

  @Override
  public void deleteEditorial(Long id) {
    Editorial editorialExists = editorialRepository.findById(id)
        .orElseThrow(() -> new NotFoundElementException("La editorial a eliminar no existe"));

    editorialRepository.delete(editorialExists);
  }
}
