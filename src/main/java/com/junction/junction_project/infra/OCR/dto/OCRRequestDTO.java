package com.junction.junction_project.infra.OCR.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class OCRRequestDTO {
  private List<Request> requests;

  @Getter
  @Setter
  @Builder
  public static class Request {
    private Image image;
    private List<Feature> features;
  }

  @Getter
  @Setter
  @Builder
  public static class Image {
    private String content;
  }

  @Getter
  @Setter
  @Builder
  public static class Feature {
    private String type;
  }
}