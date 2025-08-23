package com.junction.junction_project.domain.constructionAddresses.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressesRequest {

  @NotNull(message = "한글 주소는 필수입니다.")
  private String koreanAddress;

  @NotNull(message = "한글 주소는 필수입니다.")
  private String englishAddress;

}
