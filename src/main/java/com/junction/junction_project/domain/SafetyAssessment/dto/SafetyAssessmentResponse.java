package com.junction.junction_project.domain.SafetyAssessment.dto;

import com.junction.junction_project.domain.SafetyAssessment.entity.SubmitStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SafetyAssessmentResponse {

  private Long safetyScore;
  private Long riskScore;

  private SubmitStatus submitStatus;
  private String englishAddress;

}
