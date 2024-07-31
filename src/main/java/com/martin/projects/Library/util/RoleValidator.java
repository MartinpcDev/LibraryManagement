package com.martin.projects.Library.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {

  @Override
  public void initialize(ValidRole constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return false;
    }

    return Arrays.stream(UserRole.values())
        .anyMatch(userRole -> userRole.name().equalsIgnoreCase(value));
  }
}
