package com.martin.projects.Library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveAuthor {

  @NotBlank(message = "El campo name es obligatorio")
  @Size(min = 2, max = 30, message = "El campo name debe tener entre 2 y 30 caracteres")
  private String name;

  @NotBlank(message = "El campo lastname es obligatorio")
  @Size(min = 2, max = 30, message = "El campo lastname debe tener entre 2 y 30 caracteres")
  private String lastname;

  @NotNull(message = "El campo birthdate es obligatorio")
  @Past(message = "El campo birthdate debe ser una fecha pasada")
  private Date birthdate;

  @NotBlank(message = "El campo nationality es obligatorio")
  @Size(min = 2, max = 30, message = "El campo nationality debe tener entre 2 y 30 caracteres")
  private String nationality;
}
