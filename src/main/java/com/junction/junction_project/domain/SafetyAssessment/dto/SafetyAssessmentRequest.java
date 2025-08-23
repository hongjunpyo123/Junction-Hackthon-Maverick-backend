package com.junction.junction_project.domain.SafetyAssessment.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SafetyAssessmentRequest {
  private Long addressId;
  private Long numOfWorkers;
  private List<AddressInfoDTO> addressInfoList;



  @Getter
  @Setter
  @NoArgsConstructor
  public static class AddressInfoDTO {
    private String title;
    private String description;
    private Boolean answer;
  }
}
