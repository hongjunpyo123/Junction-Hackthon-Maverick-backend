package com.junction.junction_project.domain.graph.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAiResponse;
import com.junction.junction_project.domain.SafetyAssessment.entity.SafetyAssessment;
import com.junction.junction_project.domain.SafetyAssessment.repository.SafetyAssessmentRepository;
import com.junction.junction_project.domain.graph.dto.GraphAnalyzeAiParseDTO;
import com.junction.junction_project.domain.graph.dto.GraphAnalyzeAiResponse;
import com.junction.junction_project.domain.graph.dto.GraphAnalyzeResponse;
import com.junction.junction_project.infra.claude.ClaudeAiClient;
import com.junction.junction_project.infra.claude.prompt.ClaudeAiPrompt;
import com.junction.junction_project.infra.gemini.GeminiClient;
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
  private final GeminiClient geminiClient;
  private final SafetyAssessmentRepository safetyAssessmentRepository;

  public GraphAnalyzeAiParseDTO graphAnalyze() {
    List<SafetyAssessment> safetyAssessments = safetyAssessmentRepository.findAll();
    List<String> safetyAssessmentIssues = safetyAssessments.stream()
        .map(SafetyAssessment::getIssues).toList();

    List<Long> riskScore = safetyAssessments.stream()
        .map(SafetyAssessment::getRiskScore).toList();

    System.out.println("graphAnalyze input prompt = " + ClaudeAiPrompt.GRAPH_ANALYZE(safetyAssessmentIssues, riskScore));
    String response = claudeAiClient.call(ClaudeAiPrompt.GRAPH_ANALYZE(safetyAssessmentIssues, riskScore));

    log.info("AI response: {}", response);

    GraphAnalyzeAiParseDTO graphAnalyzeAiParseDTO = parseJsonToList(response);



    return graphAnalyzeAiParseDTO;
  }


  private GraphAnalyzeAiParseDTO parseJsonToList(String checkListTextJson) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(checkListTextJson, GraphAnalyzeAiParseDTO.class);
    } catch (Exception e) {
      throw new RuntimeException("JSON 파싱 오류: " + e.getMessage(), e);
    }
  }




}
