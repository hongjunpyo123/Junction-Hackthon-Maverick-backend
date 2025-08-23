package com.junction.junction_project.domain.ocr.controller;

import com.junction.junction_project.domain.ocr.docs.OCRApiDocumentation;
import com.junction.junction_project.domain.ocr.service.OCRService;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import com.junction.junction_project.infra.OCR.dto.OCRResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ocr")
public class OCRController implements OCRApiDocumentation {

  private final OCRService ocrService;


  @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> analyzeOCR(@RequestPart("ocrImage") MultipartFile ocrImage) {
    List<OCRResponseDTO> OCRResponses = ocrService.processImageToData(ocrImage);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(OCRResponses, "이미지 변환이 완료되었습니다."));
  }

}
