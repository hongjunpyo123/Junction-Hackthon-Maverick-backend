package com.junction.junction_project.global.exception;

public class NotFoundInfoException extends CustomException {

  public NotFoundInfoException(ErrorCode errorCode) {
    super(errorCode);
  }
}
