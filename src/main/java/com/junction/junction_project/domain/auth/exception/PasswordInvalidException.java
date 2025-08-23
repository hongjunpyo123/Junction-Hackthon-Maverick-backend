package com.junction.junction_project.domain.auth.exception;

import com.junction.junction_project.global.exception.CustomException;
import com.junction.junction_project.global.exception.ErrorCode;

public class PasswordInvalidException extends CustomException {

  public PasswordInvalidException(ErrorCode errorCode) {
    super(errorCode);
  }
}
