package com.junction.junction_project.domain.graph.service;

import com.junction.junction_project.domain.SafetyAssessment.entity.SafetyAssessment;
import com.junction.junction_project.domain.SafetyAssessment.repository.SafetyAssessmentRepository;
import com.junction.junction_project.domain.graph.dto.GraphAnalyzeAiResponse;
import com.junction.junction_project.domain.graph.dto.GraphAnalyzeResponse;
import com.junction.junction_project.infra.claude.ClaudeAiClient;
import com.junction.junction_project.infra.claude.prompt.ClaudeAiPrompt;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GraphService {


  private final ClaudeAiClient claudeAiClient;
  private final SafetyAssessmentRepository safetyAssessmentRepository;

  public GraphAnalyzeAiResponse graphAnalyze() {
    List<SafetyAssessment> safetyAssessments = safetyAssessmentRepository.findAll();
    List<String> safetyAssessmentIssues = safetyAssessments.stream()
        .map(SafetyAssessment::getIssues).toList();

    List<Long> riskScore = safetyAssessments.stream()
        .map(SafetyAssessment::getRiskScore).toList();

    String response = claudeAiClient.call(claudeAiClient.call(ClaudeAiPrompt.GRAPH_ANALYZE(safetyAssessmentIssues, riskScore)));
    log.info("AI response: {}", response);

    GraphAnalyzeAiResponse graphAnalyzeAiResponse = GraphAnalyzeAiResponse.builder()
        .graphAnalyzeResult(response)
        .build();

    return graphAnalyzeAiResponse;




  }




}
