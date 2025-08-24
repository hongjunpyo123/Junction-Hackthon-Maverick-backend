package com.junction.junction_project.infra.gemini;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GeminiClient {

  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  @Value("${gemini.api-key}")
  private String apiKey;

  private static final String PATH = "/v1beta/models/gemini-2.5-flash:generateContent";

  public String call(String prompt) {
    Map<String, Object> promptPart = Map.of("text", prompt);

    Map<String, Object> content = Map.of(
        "parts", List.of(promptPart)
    );

    Map<String, Object> requestBody = Map.of(
        "contents", List.of(content)
    );

    try {
      String response = webClient.post()
          .uri(uriBuilder -> uriBuilder
              .path(PATH)
              .queryParam("key", apiKey)
              .build())
          .contentType(MediaType.APPLICATION_JSON)
          .bodyValue(requestBody)
          .retrieve()
          .bodyToMono(String.class)
          .block(); // ✅ Mono → String 동기 변환

      JsonNode root = objectMapper.readTree(response);
      return root.path("candidates")
          .get(0)
          .path("content")
          .path("parts")
          .get(0)
          .path("text")
          .asText();

    } catch (Exception e) {
      throw new RuntimeException("Gemini API 응답 파싱 실패", e);
    }
  }



  public Mono<String> analyzeImages(String base64Img1, String base64Img2) {
    Map<String, Object> promptPart = Map.of("text",
        """
        첫 번째 이미지와 두 번째 이미지는 항상 같은 음식입니다.
        두 번째 사진이 첫 번째 사진에 비해 얼마나 많이 먹혔는지, 즉 얼마나 먹었는지 분석해주세요.
        반드시 설명은 필요없이 퍼센트(%) 단위로 숫자만 주세요
        예시: 65% 
   
        """
    );

    Map<String, Object> beforeImagePart = Map.of(
        "inline_data", Map.of(
            "mime_type", "image/jpeg",
            "data", base64Img1
        )
    );

    Map<String, Object> afterImagePart = Map.of(
        "inline_data", Map.of(
            "mime_type", "image/jpeg",
            "data", base64Img2
        )
    );

    Map<String, Object> content = Map.of(
        "parts", List.of(promptPart, beforeImagePart, afterImagePart)
    );

    Map<String, Object> requestBody = Map.of(
        "contents", List.of(content)
    );

    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(PATH)
            .queryParam("key", apiKey)
            .build())
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(String.class)
        .map(response -> {
          try {
            JsonNode root = objectMapper.readTree(response);
            String result = root.path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();

            // 퍼센트가 포함되어 있는지 검증
            if (!result.matches(".*\\d{1,3}\\s*%.*")) {
              // 퍼센트 없으면 기본 65% 반환
              return "65%";
            }

            return result;

          } catch (Exception e) {
            throw new RuntimeException("Gemini API 응답 파싱 실패", e);
          }
        });
  }
}