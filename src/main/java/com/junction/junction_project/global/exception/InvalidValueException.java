package com.junction.junction_project.global.exception;

public class InvalidValueException extends CustomException {

  public InvalidValueException(ErrorCode errorCode) {
    super(errorCode);
  }
}
