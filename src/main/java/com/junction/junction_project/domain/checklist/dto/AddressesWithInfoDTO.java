package com.junction.junction_project.domain.checklist.dto;

import com.junction.junction_project.domain.constructionAddresses.entity.Addresses;
import com.junction.junction_project.domain.constructionAddresses.entity.AddressesInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressesWithInfoDTO {

  private String koreanAddress;
  private String permitType; //허가구분
  private String primaryUse; //주용도
  private String landUseZone; //용도지역
  private Integer totalNumOfHouseholds; //총세대수
  private String primaryStructure; //주구조
  private String roofStructure; //지붕구조
  private Integer FARCalculationFloorArea; //용적률산정연면적

  public static AddressesWithInfoDTO of(Addresses addresses, AddressesInfo addressesInfo) {
    return AddressesWithInfoDTO.builder()
        .koreanAddress(addresses.getKoreanAddress())
        .permitType(addressesInfo.getPermitType())
        .primaryUse(addressesInfo.getPrimaryUse())
        .landUseZone(addressesInfo.getLandUseZone())
        .totalNumOfHouseholds(addressesInfo.getTotalNumOfHouseholds())
        .primaryStructure(addressesInfo.getPrimaryStructure())
        .roofStructure(addressesInfo.getRoofStructure())
        .FARCalculationFloorArea(addressesInfo.getFARCalculationFloorArea())
        .build();
  }

}
