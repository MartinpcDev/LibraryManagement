package com.martin.projects.Library.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.projects.Library.util.ValidRole;
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
public class UpdateUser {

  @JsonProperty("full_name")
  @NotBlank(message = "el full_name es obligatorio")
  @Size(min = 5, max = 30, message = "El full_name debe tener entre 5 y 30 caracteres")
  private String fullName;

  @NotBlank(message = "el usernamename es obligatorio")
  @Size(min = 5, max = 30, message = "El username debe tener entre 5 y 30 caracteres")
  private String username;

  @NotBlank(message = "el password es obligatorio")
  @Size(min = 5, max = 30, message = "El password debe tener entre 5 y 30 caracteres")
  private String password;

  @NotBlank(message = "El campo role es obligatorio")
  @ValidRole(message = "El role solo puede ser ADMIN o EMPLOYEE")
  private String role;
}
