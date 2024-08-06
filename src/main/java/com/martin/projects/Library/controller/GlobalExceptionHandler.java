package com.martin.projects.Library.controller;

import com.martin.projects.Library.dto.response.ApiErrorResponse;
import com.martin.projects.Library.exception.DuplicatedNameException;
import com.martin.projects.Library.exception.ImageNotFoundException;
import com.martin.projects.Library.exception.JwtExpiredException;
import com.martin.projects.Library.exception.NotFoundElementException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  ZoneId zoneId = ZoneId.of("America/Lima");
  LocalDateTime timestamp = LocalDateTime.now(zoneId);

  @ExceptionHandler(NotFoundElementException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFoundElementException(
      NotFoundElementException notFoundElementException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.NOT_FOUND.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        notFoundElementException.getMessage(),
        notFoundElementException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(DuplicatedNameException.class)
  public ResponseEntity<ApiErrorResponse> handleEditorialNameExistsException(
      DuplicatedNameException duplicatedNameException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.CONFLICT.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Error en la request: Nombre duplicado",
        duplicatedNameException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(
      IllegalArgumentException illegalArgumentException, HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        illegalArgumentException.getMessage(),
        illegalArgumentException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException httpMessageNotReadableException, HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Error en la lectura del HTTP body, Compruebe que el formato es correcto y/o contenga "
            + "data valida.",
        httpMessageNotReadableException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ApiErrorResponse> handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Media Type no soportados, los Media Type soportados son: "
            + httpMediaTypeNotSupportedException.getSupportedMediaTypes()
            + " y tu enviaste: " + httpMediaTypeNotSupportedException.getContentType(),
        httpMediaTypeNotSupportedException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiErrorResponse> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "metodo HTTP no permitado, Revisa el metodo HTTP de la request.",
        httpRequestMethodNotSupportedException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
    List<String> details = errors.stream().map(error -> {
      if (error instanceof FieldError fieldError) {
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
      }

      return error.getDefaultMessage();
    }).toList();

    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "La request tiene parametros invalidos o incompletos.",
        methodArgumentNotValidException.getMessage(),
        timestamp,
        details
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException methodArgumentTypeMismatchException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    Object valueRejected = methodArgumentTypeMismatchException.getValue();
    String propertyName = methodArgumentTypeMismatchException.getName();

    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Request Invalido: el valor proporcionado " + valueRejected
            + " no tiene el type esperado " + "para el " + propertyName,
        methodArgumentTypeMismatchException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApiErrorResponse> handleAuthenticationException(
      AuthenticationException authenticationException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.UNAUTHORIZED.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Acceso no Autorizado",
        authenticationException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(
      BadCredentialsException badCredentialsException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.UNAUTHORIZED.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Credenciales invalidas",
        badCredentialsException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(AuthorizationDeniedException.class)
  public ResponseEntity<ApiErrorResponse> handleAuthorizationDeniedException(
      AuthorizationDeniedException authorizationDeniedException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.UNAUTHORIZED.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Acceso denegado",
        authorizationDeniedException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(JwtExpiredException.class)
  public ResponseEntity<ApiErrorResponse> handleJwtExpiredException(
      JwtExpiredException jwtExpiredException, HttpServletRequest request) {

    int httpStatus = HttpStatus.UNAUTHORIZED.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "JWT expirado",
        jwtExpiredException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(ImageNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleImageNotFoundException(
      ImageNotFoundException imageNotFoundException,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Imagen no proporcionada",
        imageNotFoundException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ApiErrorResponse> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException maxUploadSizeExceededException, HttpServletRequest request) {

    int httpStatus = HttpStatus.PAYLOAD_TOO_LARGE.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "El tamaño del archivo de imagen sobrepasa el permitido",
        maxUploadSizeExceededException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(MultipartException.class)
  public ResponseEntity<ApiErrorResponse> handleMultipartException(
      MultipartException multipartException, HttpServletRequest request) {

    int httpStatus = HttpStatus.BAD_REQUEST.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Error al subir la imagen",
        multipartException.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception exception,
      HttpServletRequest request) {

    int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        httpStatus,
        request.getMethod(),
        "Ocurrio un error inesperado.",
        exception.getMessage(),
        timestamp,
        null
    );

    return ResponseEntity.status(httpStatus).body(apiErrorResponse);
  }
}
