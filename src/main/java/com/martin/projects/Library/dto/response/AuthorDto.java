package com.martin.projects.Library.dto.response;

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
  private Date birthdate;
  private String nationality;
}
