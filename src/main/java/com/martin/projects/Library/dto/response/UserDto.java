package com.martin.projects.Library.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.projects.Library.util.UserRole;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

  private Long id;

  @JsonProperty(value = "full_name")
  private String fullName;

  private String username;

  private UserRole role;

  @JsonProperty("created_At")
  @JsonFormat(timezone = "yyyy-MM-dd")
  private LocalDateTime createdAt;
}
