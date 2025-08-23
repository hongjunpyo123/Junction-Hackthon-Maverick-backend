package com.junction.junction_project.domain.ocr.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.junction_project.infra.OCR.OCRClient;
import com.junction.junction_project.infra.OCR.dto.OCRResponseDTO;
import com.junction.junction_project.infra.claude.ClaudeAiClient;
import com.junction.junction_project.infra.claude.prompt.ClaudeAiPrompt;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class OCRService {

  private final ClaudeAiClient claudeAiClient;
  private final OCRClient ocrClient;

  public List<OCRResponseDTO> processImageToData(MultipartFile ocrImage) {
    try {
      String base64Image = multipartFileToBase64(ocrImage);
      String ocrText = ocrClient.requestOCR(base64Image);
      log.info("Extracted Checklist JSON: {}", ocrText);
      String checkListTextJson = claudeAiClient.call(ClaudeAiPrompt.OCR_FORMMATING_PROMPT(ocrText));
      log.info("Extracted Checklist JSON: {}", checkListTextJson);

      List<OCRResponseDTO> checkListItems = parseJsonToList(checkListTextJson);

      return checkListItems;
    } catch (Exception e) {
      log.error("Failed to process image: {}", e.getMessage());
      throw new RuntimeException("Image processing failed : " + e.getMessage());
    }

  }

  private String multipartFileToBase64(MultipartFile file) throws IOException {
    return Base64.getEncoder().encodeToString(file.getBytes());
  }

  private List<OCRResponseDTO> parseJsonToList(String checkListTextJson) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(checkListTextJson, new TypeReference<List<OCRResponseDTO>>() {});
    } catch (Exception e) {
      throw new RuntimeException("JSON 파싱 오류: " + e.getMessage(), e);
    }
  }



}
