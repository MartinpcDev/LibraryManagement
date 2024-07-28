package com.martin.projects.Library.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

  @Override
  public void initialize(ValidGender constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return true;
    }
    return Arrays.stream(BookGender.values())
        .anyMatch(gender -> gender.name().equalsIgnoreCase(value));
  }
}
