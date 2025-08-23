package com.junction.junction_project.domain.checklist.controller;

import com.junction.junction_project.domain.checklist.dto.ChecklistGenerateResponseDTO;
import com.junction.junction_project.domain.checklist.dto.ChecklistRequestDTO;
import com.junction.junction_project.domain.checklist.service.ChecklistService;
import com.junction.junction_project.global.common.dto.response.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/check-list")
public class CheckListController {

  private final ChecklistService checklistService;

  @PostMapping("/generate")
  public ResponseEntity<?> generateCheckList(@RequestBody ChecklistRequestDTO request) {

    List<ChecklistGenerateResponseDTO> response = checklistService.generateCheckList(request);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.of(response, "체크리스트 생성 완료"));
  }



}
