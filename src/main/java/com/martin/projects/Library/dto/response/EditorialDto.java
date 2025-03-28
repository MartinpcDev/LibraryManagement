package com.martin.projects.Library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditorialDto {

  private Long id;
  private String name;
  private String country;
  private String address;
}
