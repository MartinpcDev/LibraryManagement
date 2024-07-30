package com.martin.projects.Library.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.projects.Library.util.ValidStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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
public class UpdatePrestamo {

  @JsonProperty(value = "book_id")
  @NotNull(message = "El campo book_id es obligatorio")
  private Long bookId;

  @JsonProperty(value = "user_id")
  @NotNull(message = "El campo user_id es obligatorio")
  private Long userId;

  @NotBlank(message = "El campo status es obligatorio")
  @ValidStatus(message = "El campo status deber ser EN_PRESTAMO o DISPONIBLE")
  private String status;

  @NotNull(message = "El campo start_date es obligatorio")
  @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "El campo birthdate debe estar en el formato yyyy-MM-dd")
  @JsonProperty(value = "start_date")
  private String startDateString;

  @NotNull(message = "El campo end_date es obligatorio")
  @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "El campo birthdate debe estar en el formato yyyy-MM-dd")
  @JsonProperty(value = "end_date")
  private String endDateString;

  private Date startDate;
  private Date endDate;


  public void setStartDateString(String startDateString) {
    this.startDateString = startDateString;
    this.startDate = convertToDate(startDateString);
  }

  public void setEndDateString(String endDateString) {
    this.endDateString = endDateString;
    this.endDate = convertToDate(endDateString);
  }

  @NotNull(message = "El campo start_date es obligatorio")
  @PastOrPresent(message = "El start_date debe ser en el pasado o presente")
  public Date getStartDate() {
    return startDate;
  }

  @NotNull(message = "El campo end_date es obligatorio")
  @Future(message = "El end_date debe ser en el futuro")
  public Date getEndDate() {
    return endDate;
  }

  private Date convertToDate(String dateStr) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      sdf.setLenient(false);
      return sdf.parse(dateStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException(
          "El campo start_date o end_date debe estar en el formato yyyy-MM-dd y ser una fecha válida");
    }
  }
}
