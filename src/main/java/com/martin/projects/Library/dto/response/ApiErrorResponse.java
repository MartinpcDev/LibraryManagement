package com.martin.projects.Library.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {

  @JsonProperty("status_code")
  private int statusCode;

  @JsonProperty("http_method")
  private String httpMethod;

  private String message;
  private LocalDateTime timestamp;
  private List<String> details;
}
