package com.junction.junction_project.domain.constructionAddresses.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class AddressesRequest {

  @NotNull(message = "한글 주소는 필수입니다.")
  private String koreanAddress;

  @NotNull(message = "한글 주소는 필수입니다.")
  private String englishAddress;

  @Schema(example = "신측")
  private String permitType; //허가구분

  @Schema(example = "공동주택")
  private String primaryUse; //주용도

  @Schema(example = "제1종일반주거지역")
  private String landUseZone; //용도지역

  @Schema(example = "10")
  private Integer totalNumOfHouseholds; //총세대수

  @Schema(example = "철근콘크리트구조")
  private String primaryStructure; //주구조

  @Schema(example = "(철근)콘크리트")
  private String roofStructure; //지붕구조

  @Schema(example = "490")
  private Integer FARCalculationFloorArea; //용적률산정연면적

  @Schema(example = "0")
  private Integer numOfWorkers; //공사인원

}
