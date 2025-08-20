package com.junction.junction_project.domain.user.exception;


import com.junction.junction_project.global.exception.CustomException;
import com.junction.junction_project.global.exception.ErrorCode;

public class CustomUserDuplicationException extends CustomException {

  public CustomUserDuplicationException(ErrorCode errorCode) {
    super(errorCode);
  }

}
