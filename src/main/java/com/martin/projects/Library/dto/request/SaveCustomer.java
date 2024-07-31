package com.martin.projects.Library.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveCustomer {

  @JsonProperty("full_name")
  @NotBlank(message = "el full_name es obligatorio")
  @Size(min = 5, max = 30, message = "El full_name debe tener entre 5 y 30 caracteres")
  private String fullName;
  @NotBlank(message = "el phone es obligatorio")
  @Pattern(regexp = "^\\d{9}$", message = "El phone debe tener un formato valido")
  private String phone;
  @NotBlank(message = "el email es obligatorio")
  @Email(regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|hotmail\\.com)$", message = "El campo email"
      + " debe tener el formato correcot @gmail.com o @hotmail.com")
  private String email;
}
