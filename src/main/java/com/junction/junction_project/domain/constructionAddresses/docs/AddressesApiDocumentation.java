package com.junction.junction_project.domain.constructionAddresses.docs;

import com.junction.junction_project.domain.constructionAddresses.dto.AddressesRequest;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "현장 주소 조회, 추가 api 입니다 api", description = "")
public interface AddressesApiDocumentation {


  @ApiResponse(
      responseCode = "200",
      description = "조회 성공",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ResponseDTO.class),
          examples = @ExampleObject(
              value = """
                  
                  {
                    "localDateTime": "2025-08-23T15:55:42.020251",
                    "statusCode": 200,
                    "code": "SUCCESS",
                    "message": "등록된 건설 현장 주소 조회 성공",
                    "data": [
                      {
                        "koreanAddress": "경기도 남양주시 진접읍 부평리 204-2번지",
                        "englishAddress": "204-2, Bupyeong-ri, Jinjeop-eup, Namyangju-si, Gyeonggi-do, Republic of Korea"
                      }
                    ]
                  }
                  
                  """
          )
      )
  )
  @Operation(summary = "현장 주소 조회 api 입니다. (검색기능 추가)", description = "")
  ResponseEntity<?> getAddresses(@RequestParam(required = false) String keyword);


  @ApiResponse(
      responseCode = "200",
      description = "등록 성공",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ResponseDTO.class),
          examples = @ExampleObject(
              value = """
                  
                  {
                     "localDateTime": "2025-08-23T15:55:37.632152",
                     "statusCode": 200,
                     "code": "SUCCESS",
                     "message": "건설 현장 주소 등록 성공",
                     "data": {
                       "koreanAddress": "경기도 남양주시 진접읍 부평리 204-2번지",
                       "englishAddress": "204-2, Bupyeong-ri, Jinjeop-eup, Namyangju-si, Gyeonggi-do, Republic of Korea"
                     }
                   }
                  
                  """
          )
      )
  )
  @Operation(summary = "현장 주소 등록 api 입니다.", description = "")
  ResponseEntity<?> addAddress(AddressesRequest request, BindingResult bindingResult);
}
