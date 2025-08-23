package com.junction.junction_project.domain.auth.controller;

import com.junction.junction_project.domain.auth.docs.AuthApiDocumentaion;
import com.junction.junction_project.domain.auth.dto.LoginRequest;
import com.junction.junction_project.domain.auth.exception.PasswordInvalidException;
import com.junction.junction_project.domain.auth.exception.UserNotFoundException;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import com.junction.junction_project.global.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/auth")
public class AuthController implements AuthApiDocumentaion {

  @PostMapping("/login")
  public ResponseDTO<String> login(@RequestBody LoginRequest request)  {

    if(request.getUserEmail().equals("admin@gmail.com")) {
      if(request.getUserPassword().equals("admin")) {
        return ResponseDTO.of(request.getUserEmail(), "로그인 성공");
      } else {
        throw new PasswordInvalidException(ErrorCode.INVALID_CREDENTIALS);
      }
    } else {
      throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
    }



  }
}
