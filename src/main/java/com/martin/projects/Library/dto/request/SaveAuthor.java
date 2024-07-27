package com.martin.projects.Library.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
  @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "El campo birthdate debe estar en el formato yyyy-MM-dd")
  @JsonProperty(value = "birth_date")
  private String birthdateString;

  private Date birthdate;

  @NotBlank(message = "El campo nationality es obligatorio")
  @Size(min = 2, max = 30, message = "El campo nationality debe tener entre 2 y 30 caracteres")
  private String nationality;

  public void setBirthdateString(String birthdateString) {
    this.birthdateString = birthdateString;
    this.birthdate = convertToDate(birthdateString);
  }

  public String getBirthDateString() {
    return birthdateString;
  }

  @NotNull(message = "El campo birthdate es obligatorio")
  @Past(message = "El campo birthdate debe ser una fecha pasada")
  public Date getBirthdate() {
    return birthdate;
  }

  private Date convertToDate(String dateStr) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      sdf.setLenient(false);
      return sdf.parse(dateStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException(
          "El campo birthdate debe estar en el formato yyyy-MM-dd y ser una fecha válida");
    }
  }
}
