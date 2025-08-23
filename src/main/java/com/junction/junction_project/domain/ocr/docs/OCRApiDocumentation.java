package com.junction.junction_project.domain.ocr.docs;


import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "이미지 -> 체크리스트 데이터 변환 api", description = "")
public interface OCRApiDocumentation {
  @Operation(summary = "이미지 -> 체크리스트 데이터 변환 api", description = "이미지 -> 체크리스트 데이터 변환 api 입니다 Multipart 형식으로 이미지를 받습니다. 호출시간이 오래걸립니다")
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
                 "localDateTime": "2025-08-23T14:23:14.742898",
                 "statusCode": 200,
                 "code": "SUCCESS",
                 "message": "이미지 변환이 완료되었습니다.",
                 "data": [
                            {
                              "title": "출제 계획(문항정보표)에 부합하게 출제하셨나요?",
                              "description": "미리 수립된 출제 계획과 문항정보표의 내용에 따라 문항이 작성되었는지 확인해야 합니다."
                            },
                            {
                              "title": "교육과정의 범위를 벗어난 문항은 없나요?",
                              "description": "국가 교육과정 및 학교 교육과정에서 정한 학습 범위를 초과하는 내용이 출제되지 않았는지 점검해야 합니다."
                            },
                            {
                              "title": "특정 학생이나 학급에 유리한 내용은 없나요?",
                              "description": "모든 학생에게 공평한 평가가 될 수 있도록 특정 집단에게만 유리한 문항이 포함되지 않았는지 확인해야 합니다."
                            },
                            {
                              "title": "기출 문제나 시중 참고서 문제를 그대로 사용한 문항은 없나요?",
                              "description": "저작권 문제와 평가의 공정성을 위해 기존에 출제된 문제나 참고서의 문제를 무단 사용하지 않았는지 점검해야 합니다."
                            },
                            {
                              "title": "평가 목적과 학생 수준을 고려하여 적절한 난이도로 출제하셨나요?",
                              "description": "학생들의 학습 수준과 평가의 목적에 맞는 적정한 난이도로 문항이 구성되었는지 확인해야 합니다."
                            },
                            {
                              "title": "출제 원칙에 맞게 출제하셨나요?",
                              "description": "학업성적관리지침 및 학교 규정 등에서 정한 출제 원칙을 준수하여 문항을 작성했는지 점검해야 합니다."
                            }
                          ]
      }
"""
              )
          )
      ),
      @ApiResponse(
          responseCode = "401",
          description = "ai 호출 또는 파싱 중 오류 발생 상황입니다.",
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(
                  value = """
                    {
                            "statusCode": 401,
                            "code": "",
                            "message": "토큰 정보가 올바르지 않습니다.",
                            "data": null
                          }
                """
              )
          )
      )
  })
  public ResponseEntity<?> analyzeOCR(@RequestPart("ocrImage") MultipartFile ocrImage);

}
