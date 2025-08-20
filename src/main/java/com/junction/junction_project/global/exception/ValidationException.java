package com.junction.junction_project.global.exception;

public class ValidationException extends CustomException {

  public ValidationException(ErrorCode errorCode) {
    super(errorCode);
  }
}
