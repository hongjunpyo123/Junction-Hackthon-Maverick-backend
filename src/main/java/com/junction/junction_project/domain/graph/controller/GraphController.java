package com.junction.junction_project.domain.graph.controller;

import com.junction.junction_project.domain.SafetyAssessment.entity.SafetyAssessment;
import com.junction.junction_project.domain.SafetyAssessment.repository.SafetyAssessmentRepository;
import com.junction.junction_project.domain.graph.dto.GraphAnalyzeAiResponse;
import com.junction.junction_project.domain.graph.dto.GraphAnalyzeResponse;
import com.junction.junction_project.domain.graph.service.GraphService;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/graph")
public class GraphController {


  private final SafetyAssessmentRepository safetyAssessmentRepository;
  private final GraphService graphService;

  @GetMapping("/analyze")
  public ResponseEntity<?> graphAnalyze() {
    GraphAnalyzeAiResponse response = graphService.graphAnalyze();
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(response, "그래프 추이 분석에 성공하였습니다."));
  }

  @GetMapping("/weekly-analysis")
  public ResponseEntity<?> getWeeklyAnalysis() {
    List<SafetyAssessment> safetyAssessments = safetyAssessmentRepository.findTop7ByOrderByIdDesc();
    List<GraphAnalyzeResponse> responses = safetyAssessments.stream()
        .map(assessment -> {
          return GraphAnalyzeResponse.builder()
              .safetyScore(assessment.getSafetyScore())
              .riskScore(assessment.getRiskScore())
              .build();
        }).toList();

    return ResponseEntity.status(HttpStatus.OK)
        .body(ResponseDTO.of(responses, "일주일간의 안전 점수 분석 데이터를 불러왔습니다."));
  }



}
