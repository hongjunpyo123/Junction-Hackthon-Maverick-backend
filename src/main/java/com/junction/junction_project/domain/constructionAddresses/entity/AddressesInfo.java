package com.junction.junction_project.domain.constructionAddresses.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AddressesInfo {

  @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  private String permitType; //허가구분
  private String primaryUse; //주용도
  private String landUseZone; //용도지역
  private Integer totalNumOfHouseholds; //총세대수
  private String primaryStructure; //주구조
  private String roofStructure; //지붕구조
  private Integer FARCalculationFloorArea; //용적률산정연면적
  private Integer numOfWorkers; //공사인원

  /**
   * Factory method to create an AddressesInfo instance.
   * @param permitType 허가구분
   * @param primaryUse 주용도
   * @param landUseZone 용도지역
   * @param totalNumOfHouseholds 총세대수
   * @param primaryStructure 주구조
   * @param roofStructure 지붕구조
   * @param FARCalculationFloorArea 용적률산정연면적
   * @param numOfWorkers 공사인원
   * @return
   */
  public static AddressesInfo of(String permitType, String primaryUse, String landUseZone, Integer totalNumOfHouseholds, String primaryStructure, String roofStructure, Integer FARCalculationFloorArea, Integer numOfWorkers) {
    AddressesInfo addressesInfo = new AddressesInfo();
    addressesInfo.setPermitType(permitType);
    addressesInfo.setPrimaryUse(primaryUse);
    addressesInfo.setLandUseZone(landUseZone);
    addressesInfo.setTotalNumOfHouseholds(totalNumOfHouseholds);
    addressesInfo.setPrimaryStructure(primaryStructure);
    addressesInfo.setRoofStructure(roofStructure);
    addressesInfo.setFARCalculationFloorArea(FARCalculationFloorArea);
    addressesInfo.setNumOfWorkers(numOfWorkers);
    return addressesInfo;
  }

}
