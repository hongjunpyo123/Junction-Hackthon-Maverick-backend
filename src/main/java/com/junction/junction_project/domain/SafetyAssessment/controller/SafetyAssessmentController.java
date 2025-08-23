package com.junction.junction_project.domain.SafetyAssessment.controller;

import com.junction.junction_project.domain.SafetyAssessment.docs.SafetyAssessmentApiDocumentation;
import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAssessmentRequest;
import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAssessmentResponse;
import com.junction.junction_project.domain.SafetyAssessment.service.SafetyAssessmentService;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/safety-assessment")
public class SafetyAssessmentController implements SafetyAssessmentApiDocumentation {

  private final SafetyAssessmentService safetyAssessmentService;

  @PostMapping("/analyze")
  public ResponseEntity<?> safetyAssessment(@RequestBody SafetyAssessmentRequest safetyAssessmentRequest) {
    SafetyAssessmentResponse safetyAssessmentResponse = safetyAssessmentService.safetyAssessment(safetyAssessmentRequest);

    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(safetyAssessmentResponse, "분석에 성공하였습니다."));

  }



}
