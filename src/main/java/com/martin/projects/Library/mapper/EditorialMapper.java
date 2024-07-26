package com.martin.projects.Library.mapper;

import com.martin.projects.Library.dto.request.SaveEditorial;
import com.martin.projects.Library.dto.response.EditorialDto;
import com.martin.projects.Library.persistence.entity.Editorial;
import java.util.List;

public class EditorialMapper {

  public static EditorialDto toEditorialDto(Editorial editorial) {
    if (editorial == null) {
      return null;
    }

    return new EditorialDto(
        editorial.getId(),
        editorial.getName(),
        editorial.getCountry(),
        editorial.getAddress()
    );
  }

  public static List<EditorialDto> toEditorialDtoList(List<Editorial> editorials) {
    if (editorials == null) {
      return null;
    }

    return editorials.stream()
        .map(EditorialMapper::toEditorialDto)
        .toList();
  }

  public static Editorial toEditorialEntity(SaveEditorial editorialDto) {
    if (editorialDto == null) {
      return null;
    }

    Editorial editorial = new Editorial();
    editorial.setName(editorialDto.getName());
    editorial.setCountry(editorialDto.getCountry());
    editorial.setAddress(editorialDto.getAddress());

    return editorial;
  }

  public static void updateEditorialEntity(Editorial oldEditorial, SaveEditorial editorialDto) {
    if (oldEditorial == null || editorialDto == null) {
      return;
    }

    oldEditorial.setName(editorialDto.getName());
    oldEditorial.setCountry(editorialDto.getCountry());
    oldEditorial.setAddress(editorialDto.getAddress());
  }
}
