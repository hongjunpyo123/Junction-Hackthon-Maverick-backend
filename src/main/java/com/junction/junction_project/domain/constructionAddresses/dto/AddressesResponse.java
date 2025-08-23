package com.junction.junction_project.domain.constructionAddresses.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class AddressesResponse {
  private Long addressId;
  private String koreanAddress;
  private String englishLotAddress;
  private String englishStreetAddress;


  public static AddressesResponse from(AddressesRequest request) {
    AddressesResponse response = new AddressesResponse();
    response.setKoreanAddress(request.getKoreanAddress());
    response.setEnglishLotAddress(request.getEnglishLotAddress());
    response.setEnglishStreetAddress(request.getEnglishStreetAddress());
    return response;
  }
}
