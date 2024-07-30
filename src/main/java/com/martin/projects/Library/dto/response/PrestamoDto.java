package com.martin.projects.Library.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.projects.Library.util.PrestamoStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoDto {

  private Long id;

  private PrestamoStatus status;

  @JsonProperty(value = "start_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonProperty(value = "end_date")
  private Date endDate;

  private BookDto book;
  private UserDto user;
}
