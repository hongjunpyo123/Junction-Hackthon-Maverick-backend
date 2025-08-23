package com.junction.junction_project.infra.OCR;

import com.junction.junction_project.infra.OCR.dto.OCRApiResponseDTO;
import com.junction.junction_project.infra.OCR.dto.OCRRequestDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class OCRClient {

  private final WebClient webClient;

  @Value("${google.oauth.token}")
  private String accessToken;

  String url = "https://vision.googleapis.com/v1/images:annotate";

  public String requestOCR(String base64Image) {

    OCRRequestDTO ocrRequestDTO = OCRRequestDTO.builder()
        .requests(List.of(
            OCRRequestDTO.Request.builder()
                .image(OCRRequestDTO.Image.builder()
                    .content(base64Image)
                    .build())
                .features(List.of(
                    OCRRequestDTO.Feature.builder()
                        .type("TEXT_DETECTION")
                        .build()
                ))
                .build()
        ))
        .build();

    OCRApiResponseDTO response = webClient.post()
        .uri(url)
        .header("Authorization", "Bearer " + accessToken)
        .header("Content-Type", "application/json")
        .header("x-goog-user-project", "junction-469900")
        .bodyValue(ocrRequestDTO)
        .retrieve()
        .bodyToMono(OCRApiResponseDTO.class)
        .block();

    return response.getFirstDescription();
  }


}
