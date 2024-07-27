package com.martin.projects.Library.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {


  private Long id;
  private String name;
  private String lastname;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;
  private String nationality;
}
