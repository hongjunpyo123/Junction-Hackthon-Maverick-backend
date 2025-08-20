package com.junction.junction_project.global.exception;


import com.junction.junction_project.global.common.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> providerNotFoundException(ValidationException e) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(ErrorResponse.of(e.getErrorCode()));
  }

}
