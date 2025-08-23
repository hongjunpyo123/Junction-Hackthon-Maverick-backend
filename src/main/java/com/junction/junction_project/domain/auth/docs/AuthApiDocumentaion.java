package com.junction.junction_project.domain.auth.docs;

import com.junction.junction_project.domain.auth.dto.LoginRequest;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "테스트를 위한 api", description = "테스트를 위한 api 입니다.")
public interface AuthApiDocumentaion {
  @Operation(summary = "로그인 API", description = "로그인 API입니다. 성공응답, 실패 응답을 테스트 할 수 있습니다. 실제 서비스에서는 사용되지 않습니다.")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "로그인 성공",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class),
              examples = @ExampleObject(
                  value = """
                      {
                               "statusCode": 200,
                               "code": "SUCCESS",
                               "message": "로그인 성공",
                               "data": "admin@gmail.com"
                             }
                """
              )
          )
      ),
      @ApiResponse(
          responseCode = "401",
          description = "로그인 실패 - 비밀번호 잘못됨",
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(
                  value = """
                      {
                               "statusCode": 401,
                               "code": "INVALID_CREDENTIALS",
                               "message": "아이디 또는 비밀번호가 올바르지 않습니다.",
                               "data": null
                             }
                """
              )
          )
      ),
      @ApiResponse(
          responseCode = "404",
          description = "로그인 실패 - 가입된 사용자 없음 ",
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(
                  value = """
                      {
                               "statusCode": 404,
                               "code": "USER_NOT_FOUND",
                               "message": "해당 사용자를 찾을 수 없습니다.",
                               "data": null
                             }
                """
              )
          )
      )
  })
  ResponseDTO<String> login(@RequestBody LoginRequest request);
}
