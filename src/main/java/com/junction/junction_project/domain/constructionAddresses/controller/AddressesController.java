package com.junction.junction_project.domain.constructionAddresses.controller;

import com.junction.junction_project.domain.constructionAddresses.docs.AddressesApiDocumentation;
import com.junction.junction_project.domain.constructionAddresses.dto.AddressesRequest;
import com.junction.junction_project.domain.constructionAddresses.dto.AddressesResponse;
import com.junction.junction_project.domain.constructionAddresses.service.AddressesService;
import com.junction.junction_project.global.common.dto.response.ListResponseDTO;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import com.junction.junction_project.global.exception.ErrorCode;
import com.junction.junction_project.global.exception.InvalidValueException;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/construction-addresses")
public class AddressesController implements AddressesApiDocumentation {

  private final AddressesService addressesService;

  @GetMapping
  public ResponseEntity<?> getAddresses() {
    List<AddressesResponse> addressesRequests =  addressesService.getAddresses();
    ListResponseDTO listResponseDTO = ListResponseDTO.of(addressesRequests);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(listResponseDTO, "등록된 건설 현장 주소 조회 성공"));
  }

  @PostMapping
  public ResponseEntity<?> addAddress(@RequestBody @Valid AddressesRequest request, BindingResult bindingResult) {

    if(bindingResult.hasErrors()) {
      log.error("AuthSocialRequest validation failed :: {}", bindingResult.getAllErrors());
      throw new InvalidValueException(ErrorCode.INVALID_INPUT_VALUE);
    }

    AddressesResponse addressesResponse = addressesService.addAddress(request);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(addressesResponse, "건설 현장 주소 등록 성공"));
  }

}
