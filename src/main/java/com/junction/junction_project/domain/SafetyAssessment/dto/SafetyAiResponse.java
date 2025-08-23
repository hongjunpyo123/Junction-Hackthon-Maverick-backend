package com.junction.junction_project.domain.SafetyAssessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SafetyAiResponse {
  private Long safetyScore;
  private Long riskScore;
  private String issues;
}
