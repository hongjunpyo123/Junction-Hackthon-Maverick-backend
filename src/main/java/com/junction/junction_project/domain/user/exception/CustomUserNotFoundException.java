package com.junction.junction_project.domain.user.exception;


import com.junction.junction_project.global.exception.CustomException;
import com.junction.junction_project.global.exception.ErrorCode;

public class CustomUserNotFoundException extends CustomException {

  public CustomUserNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

}
