package com.junction.junction_project.global.exception;


import com.junction.junction_project.domain.auth.exception.PasswordInvalidException;
import com.junction.junction_project.domain.auth.exception.UserNotFoundException;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ResponseDTO> providerNotFoundException(ValidationException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(ResponseDTO.of(e.getErrorCode()));
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ResponseDTO> userNotFoundException(UserNotFoundException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(ResponseDTO.of(e.getErrorCode()));
  }

  @ExceptionHandler(PasswordInvalidException.class)
  public ResponseEntity<ResponseDTO> passwordInvalidException(PasswordInvalidException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(ResponseDTO.of(e.getErrorCode()));
  }

  @ExceptionHandler(InvalidValueException.class)
  public ResponseEntity<ResponseDTO> invalidValueException(InvalidValueException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(ResponseDTO.of(e.getErrorCode()));
  }

  @ExceptionHandler(NotFoundInfoException.class)
  public ResponseEntity<ResponseDTO> NotFoundInfoException(NotFoundInfoException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(ResponseDTO.of(e.getErrorCode()));
  }

}
