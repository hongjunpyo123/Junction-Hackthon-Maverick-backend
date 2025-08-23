package com.junction.junction_project.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

  @Schema(description = "테스트 로그인을 위한 사용자 이메일(admin@gmail.com)", example = "admin@gmail.com")
  private String userEmail;

  @Schema(description = "테스트 로그인을 위한 사용자 패스워드(admin)", example = "admin")
  private String userPassword;

}
