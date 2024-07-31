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
public class AuthenticationRequest {

  @NotBlank(message = "el usernamename es obligatorio")
  @Size(min = 5, max = 30, message = "El username debe tener entre 5 y 30 caracteres")
  private String username;
  
  @NotBlank(message = "el usernamename es obligatorio")
  @Size(min = 5, max = 30, message = "El username debe tener entre 5 y 30 caracteres")
  private String password;
}
