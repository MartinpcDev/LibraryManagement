package com.martin.projects.Library.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.projects.Library.util.BookGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

  private Long id;
  private String title;
  private String isbn;
  private String url;

  @JsonProperty(value = "release_year")
  private Integer publicationYear;

  private BookGender gender;
  private Integer stock;
  private AuthorDto author;
  private EditorialDto editorial;
}
