package com.junction.junction_project.infra.OCR.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OCRApiResponseDTO {
  private List<Response> responses;

  @Getter
  @Setter
  @Builder
  public static class Response {
    private List<TextAnnotation> textAnnotations;
  }

  @Getter
  @Setter
  @Builder
  public static class TextAnnotation {
    private String description;
  }

  public String getFirstDescription() {
    if (responses != null && !responses.isEmpty() &&
        responses.get(0).getTextAnnotations() != null &&
        !responses.get(0).getTextAnnotations().isEmpty()) {
      return responses.get(0).getTextAnnotations().get(0).getDescription();
    }
    return null;
  }
}
