package com.junction.junction_project.domain.checklist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.junction_project.domain.checklist.dto.AddressesWithInfoDTO;
import com.junction.junction_project.domain.checklist.dto.ChecklistGenerateResponseDTO;
import com.junction.junction_project.domain.checklist.dto.ChecklistRequestDTO;
import com.junction.junction_project.domain.constructionAddresses.entity.Addresses;
import com.junction.junction_project.domain.constructionAddresses.entity.AddressesInfo;
import com.junction.junction_project.domain.constructionAddresses.repository.AddressesInfoRepository;
import com.junction.junction_project.domain.constructionAddresses.repository.AddressesRepository;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import com.junction.junction_project.global.exception.ErrorCode;
import com.junction.junction_project.global.exception.NotFoundInfoException;
import com.junction.junction_project.infra.OCR.dto.OCRResponseDTO;
import com.junction.junction_project.infra.claude.ClaudeAiClient;
import com.junction.junction_project.infra.claude.prompt.ClaudeAiPrompt;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class ChecklistService {

  private final ClaudeAiClient claudeAiClient;
  private final AddressesInfoRepository addressesInfoRepository;
  private final AddressesRepository addressesRepository;

    public List<ChecklistGenerateResponseDTO> generateCheckList(@RequestBody ChecklistRequestDTO request) {

      if(!addressesRepository.findById(request.getAddressId()).isPresent()) {

        log.error("id : " + request.getAddressId() + " 해당 건설 현장 정보가 존재하지 않습니다.");
        throw new NotFoundInfoException(ErrorCode.NOT_FOUND);
      }

      Addresses addresses = addressesRepository.findById(request.getAddressId()).orElse(null);
      AddressesInfo addressesInfo = addresses.getAddressesInfo();

      AddressesWithInfoDTO addressesWithInfoDTO = AddressesWithInfoDTO.of(addresses, addressesInfo);
      String response = claudeAiClient.call(ClaudeAiPrompt.GENERATE_CHECKLIST_PROMPT(addressesWithInfoDTO));
      log.info("Generated checklist: {}", response);

      List<ChecklistGenerateResponseDTO> checklistItems = parseJsonToList(response);
      return checklistItems;

    }

  private List<ChecklistGenerateResponseDTO> parseJsonToList(String checkListTextJson) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(checkListTextJson, new TypeReference<List<ChecklistGenerateResponseDTO>>() {});
    } catch (Exception e) {
      throw new RuntimeException("JSON 파싱 오류: " + e.getMessage(), e);
    }
  }


}
