package com.junction.junction_project.domain.checklist.controller;

import com.junction.junction_project.domain.checklist.docs.CheckListApiDocumentation;
import com.junction.junction_project.domain.checklist.dto.ChecklistGenerateResponseDTO;
import com.junction.junction_project.domain.checklist.dto.ChecklistRequestDTO;
import com.junction.junction_project.domain.checklist.service.ChecklistService;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/check-list")
public class CheckListController implements CheckListApiDocumentation {

  private final ChecklistService checklistService;

  @Tag(name = "데이터 기반 체크리스트 생성 api 입니다", description = "addressId 값이 필요하며 /api/construction-addresses api에서 조회 가능합니다.")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "체크리스트 생성 성공",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResponseDTO.class),
              examples = @ExampleObject(
                  value =
                      """
                      
                          {
                            "localDateTime": "2025-08-23T21:16:22.107234",
                            "statusCode": 200,
                            "code": "SUCCESS",
                            "message": "체크리스트 생성 완료",
                            "data": [
                              {
                                "title": "Hard Hat Compliance Check",
                                "description": "Verify all workers and visitors are wearing properly fitted hard hats that meet Korean Industrial Safety and Health Act standards before entering the construction site"
                              },
                              {
                                "title": "Safety Shoe Inspection",
                                "description": "Ensure all personnel wear steel-toed safety shoes with slip-resistant soles appropriate for concrete construction work"
                              },
                              {
                                "title": "Safety Harness Availability",
                                "description": "Confirm adequate supply of safety harnesses and lanyards are available for workers performing elevated tasks"
                              },
                              {
                                "title": "High-Visibility Clothing Verification",
                                "description": "Check that all workers wear high-visibility vests or clothing to ensure visibility around heavy equipment and work areas"
                              },
                              {
                                "title": "Guardrail Installation Check",
                                "description": "Inspect that guardrails are properly installed at all floor edges, stairwells, and openings where fall hazards exist"
                              },
                              {
                                "title": "Safety Net Deployment",
                                "description": "Verify safety nets are properly installed below work areas where workers are exposed to fall hazards exceeding 2 meters"
                              },
                              {
                                "title": "Fall Arrest System Inspection",
                                "description": "Ensure fall arrest systems including anchor points, lifelines, and personal fall arrest equipment are properly installed and inspected"
                              },
                              {
                                "title": "GFCI Installation Verification",
                                "description": "Confirm Ground Fault Circuit Interrupters are installed on all temporary electrical circuits and construction power outlets"
                              },
                              {
                                "title": "Electrical Equipment Daily Inspection",
                                "description": "Conduct daily visual inspection of electrical cords, tools, and equipment for damage, proper grounding, and safe operating condition"
                              },
                              {
                                "title": "Temporary Power Distribution Safety",
                                "description": "Verify temporary electrical distribution panels are properly protected, labeled, and accessible only to qualified personnel"
                              },
                              {
                                "title": "Reinforced Concrete Formwork Inspection",
                                "description": "Check that concrete formwork and shoring systems are properly designed, installed, and inspected by qualified engineers"
                              },
                              {
                                "title": "Temporary Support System Verification",
                                "description": "Ensure temporary structural supports are adequately designed and installed during concrete construction phases"
                              },
                              {
                                "title": "Structural Integrity Monitoring",
                                "description": "Implement regular monitoring of structural elements during construction to detect any signs of distress or instability"
                              },
                              {
                                "title": "Fire Extinguisher Placement",
                                "description": "Verify appropriate fire extinguishers are strategically placed throughout the construction site and regularly inspected"
                              },
                              {
                                "title": "Evacuation Route Planning",
                                "description": "Establish and clearly mark emergency evacuation routes for the 10-household residential building construction site"
                              }
                            ]
                          }
                      
                      """
              )
          )
      )
    }
  )
  @PostMapping("/generate")
  public ResponseEntity<?> generateCheckList(@RequestBody ChecklistRequestDTO request)  {

    List<ChecklistGenerateResponseDTO> response = checklistService.generateCheckList(request);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(response, "체크리스트 생성 완료"));
  }



}
