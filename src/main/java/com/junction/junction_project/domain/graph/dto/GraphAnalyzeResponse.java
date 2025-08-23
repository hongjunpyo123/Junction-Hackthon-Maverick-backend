package com.junction.junction_project.domain.graph.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GraphAnalyzeResponse {

  private Long safetyScore;
  private Long riskScore;

}
