package com.junction.junction_project.domain.SafetyAssessment.docs;

import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAssessmentRequest;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "체크리스트 분석 요청 api 입니다", description = "")
public interface SafetyAssessmentApiDocumentation {

  @Operation(summary = "체크리스트 분석 요청 api 입니다", description = "체크리스트 분석 요청 api 입니다")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "분석 성공",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class),
              examples = @ExampleObject(
                  value =
                    """
                    
                        {
                          "localDateTime": "2025-08-24T01:29:10.054309",
                          "statusCode": 200,
                          "code": "SUCCESS",
                          "message": "분석에 성공하였습니다.",
                          "data": {
                            "safetyScore": 80,
                            "riskScore": 20,
                            "submitStatus": "PENDING",
                            "englishAddress": "string"
                          }
                        }
                    """
              )
          )
      )
  })
  ResponseEntity<?> safetyAssessment(SafetyAssessmentRequest safetyAssessmentRequest);
}
