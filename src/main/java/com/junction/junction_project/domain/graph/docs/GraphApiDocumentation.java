package com.junction.junction_project.domain.graph.docs;

import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "그래프 관련 분석 호출 api 입니다.", description = "")
public interface GraphApiDocumentation {

  @ApiResponse(
      responseCode = "200",
      description = "일주일간의 안전 점수 데이터를 불러옵니다.",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ResponseDTO.class),
          examples = @ExampleObject(
              value = """
                  
                  {
                    "localDateTime": "2025-08-24T05:34:47.600186",
                    "statusCode": 200,
                    "code": "SUCCESS",
                    "message": "일주일간의 안전 점수 분석 데이터를 불러왔습니다.",
                    "data": [
                      {
                        "safetyScore": 80,
                        "riskScore": 20
                      },
                      {
                        "safetyScore": 80,
                        "riskScore": 20
                      }
                    ]
                  }
                  
                  """
          )
      )
  )
  @Operation(summary = "일주일간의 안전 점수 데이터를 불러옵니다.", description = "")
  public ResponseEntity<?> graphAnalyze();


  @ApiResponse(
      responseCode = "200",
      description = "그래프 추이 분석",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ResponseDTO.class),
          examples = @ExampleObject(
              value = """
                  
                  {
                    "localDateTime": "2025-08-24T05:37:40.26544",
                    "statusCode": 200,
                    "code": "SUCCESS",
                    "message": "그래프 추이 분석에 성공하였습니다.",
                    "data": {
                      "graphAnalyzeResult": "This situation presents significant operational and safety concerns that require immediate attention:\\n\\n**Immediate Actions Required:**\\n- Conduct physical site inspection to verify actual conditions\\n- Review personnel scheduling and deployment records\\n- Check communication systems and reporting protocols\\n- Verify if site operations are active or suspended\\n\\n**Key Issues to Investigate:**\\n1. **Personnel Deployment**: Confirm if workers are assigned but not reporting, or if site is genuinely unmanned\\n2. **Data Integrity**: Assess whether monitoring systems are functioning properly without human oversight\\n3. **Safety Protocols**: Evaluate how safety measures are maintained with zero personnel presence\\n4. **Reporting Chain**: Identify breakdown points in data collection and transmission\\n\\n**Risk Implications:**\\n- Static 20% risk score may indicate sensor malfunction or lack of real-time assessment\\n- Unmanned sites can develop undetected hazards\\n- Emergency response capability is compromised\\n- Regulatory compliance issues may arise\\n\\n**Recommended Response:**\\n- Deploy management team for immediate site assessment\\n- Implement remote monitoring backup systems\\n- Establish emergency contact protocols for unmanned operations\\n- Review and update staffing procedures\\n\\nWould you like me to help develop a specific investigation protocol or emergency response plan for this situation?"
                    }
                  }
                  
                  """
          )
      )
  )
  @Operation(summary = "그래프 추이 분석 api 입니다.", description = "")
  public ResponseEntity<?> getWeeklyAnalysis();

}
