package com.junction.junction_project.domain.auth.exception;

import com.junction.junction_project.global.exception.CustomException;
import com.junction.junction_project.global.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

  public UserNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
