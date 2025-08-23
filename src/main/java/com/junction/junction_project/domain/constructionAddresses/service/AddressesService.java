package com.junction.junction_project.domain.constructionAddresses.service;

import com.junction.junction_project.domain.constructionAddresses.dto.AddressesRequest;
import com.junction.junction_project.domain.constructionAddresses.dto.AddressesResponse;
import com.junction.junction_project.domain.constructionAddresses.entity.Addresses;
import com.junction.junction_project.domain.constructionAddresses.entity.AddressesInfo;
import com.junction.junction_project.domain.constructionAddresses.repository.AddressesInfoRepository;
import com.junction.junction_project.domain.constructionAddresses.repository.AddressesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AddressesService {

  private final AddressesRepository addressesRepository;
  private final AddressesInfoRepository addressesInfoRepository;

  @Transactional(readOnly = true)
  public List<AddressesResponse> getAddresses() {
    List<Addresses> addresses = addressesRepository.findAll();


    return addresses.stream().map(a -> {
      AddressesResponse response = new AddressesResponse();
      response.setAddressId(a.getId());
      response.setKoreanAddress(a.getKoreanAddress());
      response.setEnglishStreetAddress(a.getEnglishStreetAddress());
      response.setEnglishLotAddress(a.getEnglishLotAddress());
      return response;
    }).toList();
  }


  public AddressesResponse addAddress(AddressesRequest request) {
    Addresses addresses = new Addresses();
    AddressesInfo addressesInfo = new AddressesInfo();

    addresses.setEnglishStreetAddress(request.getEnglishStreetAddress());
    addresses.setEnglishLotAddress(request.getEnglishLotAddress());
    addresses.setKoreanAddress(request.getKoreanAddress());
//    addressesRepository.save(addresses);

    addressesInfo.setPermitType(request.getPermitType());
    addressesInfo.setPrimaryUse(request.getPrimaryUse());
    addressesInfo.setLandUseZone(request.getLandUseZone());
    addressesInfo.setTotalNumOfHouseholds(request.getTotalNumOfHouseholds());
    addressesInfo.setPrimaryStructure(request.getPrimaryStructure());
    addressesInfo.setRoofStructure(request.getRoofStructure());
    addressesInfo.setFARCalculationFloorArea(request.getFARCalculationFloorArea());
    addressesInfo.setNumOfWorkers(request.getNumOfWorkers());
    addressesInfoRepository.save(addressesInfo);


    addresses.setAddressesInfo(addressesInfo);
    addressesRepository.save(addresses);


    return AddressesResponse.from(request);
  }

}
