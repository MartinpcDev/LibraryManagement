package com.martin.projects.Library.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {

  @Override
  public void initialize(ValidStatus constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return false;
    }

    return Arrays.stream(PrestamoStatus.values())
        .anyMatch(status -> status.name().equalsIgnoreCase(value));
  }
}
