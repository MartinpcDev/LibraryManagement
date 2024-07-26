package com.martin.projects.Library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveEditorial {

  @NotBlank(message = "El campo name es obligatorio")
  @Size(min = 2, max = 30, message = "El campo name debe tener entre 2 y 30 caracteres")
  private String name;

  @NotBlank(message = "El campo country es obligatorio")
  @Size(min = 2, max = 30, message = "El lastname name debe tener entre 2 y 30 caracteres")
  private String country;

  @NotBlank(message = "El campo address es obligatorio")
  @Size(min = 2, max = 30, message = "El campo address debe tener entre 2 y 30 caracteres")
  private String address;
}
