package com.martin.projects.Library.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

  private Long id;
  @JsonProperty("full_name")
  private String fullName;
  private String phone;
  private String email;
  @JsonProperty("created_At")
  @JsonFormat(timezone = "yyyy-MM-dd")
  private LocalDateTime createdAt;
}
