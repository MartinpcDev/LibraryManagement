package com.martin.projects.Library.exception;

public class JwtExpiredException extends RuntimeException {

  public JwtExpiredException(String message) {
    super(message);
  }
}
