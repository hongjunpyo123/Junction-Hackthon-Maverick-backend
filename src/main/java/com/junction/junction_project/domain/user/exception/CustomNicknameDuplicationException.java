package com.junction.junction_project.domain.user.exception;


import com.junction.junction_project.global.exception.CustomException;
import com.junction.junction_project.global.exception.ErrorCode;

public class CustomNicknameDuplicationException extends CustomException {

  public CustomNicknameDuplicationException(ErrorCode errorCode) {
    super(errorCode);
  }

}
