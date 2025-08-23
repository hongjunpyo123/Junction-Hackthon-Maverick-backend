package com.junction.junction_project.domain.SafetyAssessment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAiResponse;
import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAssessmentRequest;
import com.junction.junction_project.domain.SafetyAssessment.dto.SafetyAssessmentResponse;
import com.junction.junction_project.domain.SafetyAssessment.entity.SafetyAssessment;
import com.junction.junction_project.domain.SafetyAssessment.entity.SubmitStatus;
import com.junction.junction_project.domain.SafetyAssessment.repository.SafetyAssessmentRepository;
import com.junction.junction_project.domain.checklist.dto.ChecklistGenerateResponseDTO;
import com.junction.junction_project.domain.constructionAddresses.entity.Addresses;
import com.junction.junction_project.domain.constructionAddresses.repository.AddressesRepository;
import com.junction.junction_project.global.exception.ErrorCode;
import com.junction.junction_project.global.exception.NotFoundInfoException;
import com.junction.junction_project.infra.claude.ClaudeAiClient;
import com.junction.junction_project.infra.claude.prompt.ClaudeAiPrompt;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SafetyAssessmentService {

  private final AddressesRepository addressesRepository;
  private final ClaudeAiClient claudeAiClient;
  private final SafetyAssessmentRepository safetyAssessmentRepository;



  public SafetyAssessmentResponse safetyAssessment(SafetyAssessmentRequest safetyAssessmentRequest) {
    if(!addressesRepository.findById(safetyAssessmentRequest.getAddressId()).isPresent()) {
      log.error("Address not found: "+ safetyAssessmentRequest.getAddressId());
      throw new NotFoundInfoException(ErrorCode.NOT_FOUND);
    }
    log.info("Address not found: "+ safetyAssessmentRequest.getAddressId());

    Addresses safetyAssessment2 = addressesRepository.findById(safetyAssessmentRequest.getAddressId()).orElse(null);

    try {
      ObjectMapper mapper = new ObjectMapper();
      String aiResponse = claudeAiClient.call(ClaudeAiPrompt.SAFETY_ASSESSMENT(safetyAssessmentRequest));
      log.info("Safety Assessment Response: {}", aiResponse);
      SafetyAiResponse safetyAiResponse = parseJsonToList(aiResponse);

      SafetyAssessment safetyAssessment = new SafetyAssessment();
      safetyAssessment.setSafetyScore(safetyAiResponse.getSafetyScore());
      safetyAssessment.setRiskScore(safetyAiResponse.getRiskScore());
      safetyAssessment.setIssues(safetyAiResponse.getIssues());
      safetyAssessment.setEnglishAddress(safetyAssessment2.getEnglishLotAddress());

      safetyAssessmentRepository.save(safetyAssessment);


      SafetyAssessmentResponse safetyAssessmentResponse = new SafetyAssessmentResponse();
      safetyAssessmentResponse.setSafetyScore(safetyAiResponse.getSafetyScore());
      safetyAssessmentResponse.setRiskScore(safetyAiResponse.getRiskScore());
      safetyAssessmentResponse.setEnglishAddress(safetyAssessment2.getEnglishLotAddress());
      safetyAssessmentResponse.setSubmitStatus(SubmitStatus.PENDING);

      return safetyAssessmentResponse;



    } catch (Exception e) {
      e.printStackTrace();
      log.error("Failed to process image: {}", e.getMessage());
      throw new NotFoundInfoException(ErrorCode.NOT_FOUND);
    }


  }

  private SafetyAiResponse parseJsonToList(String checkListTextJson) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(checkListTextJson, SafetyAiResponse.class);
    } catch (Exception e) {
      throw new RuntimeException("JSON 파싱 오류: " + e.getMessage(), e);
    }
  }

}
