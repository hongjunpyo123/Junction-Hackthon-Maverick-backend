package com.junction.junction_project.domain.constructionAddresses.service;

import com.junction.junction_project.domain.constructionAddresses.dto.AddressesRequest;
import com.junction.junction_project.domain.constructionAddresses.dto.AddressesResponse;
import com.junction.junction_project.domain.constructionAddresses.entity.Addresses;
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

  @Transactional(readOnly = true)
  public List<AddressesResponse> getAddresses() {
    List<Addresses> addresses = addressesRepository.findAll();

    return addresses.stream().map(a -> {
      AddressesResponse response = new AddressesResponse();
      response.setId(a.getId());
      response.setKoreanAddress(a.getKoreanAddress());
      response.setEnglishAddress(a.getEnglishAddress());
      return response;
    }).toList();
  }


  public AddressesResponse addAddress(AddressesRequest request) {
    Addresses addresses = new Addresses();
    addresses.setEnglishAddress(request.getEnglishAddress());
    addresses.setKoreanAddress(request.getKoreanAddress());
    addressesRepository.save(addresses);
    return AddressesResponse.from(request);
  }

}
