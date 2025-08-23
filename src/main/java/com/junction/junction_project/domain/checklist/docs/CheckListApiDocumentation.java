package com.junction.junction_project.domain.checklist.docs;

import com.junction.junction_project.domain.checklist.dto.ChecklistGenerateResponseDTO;
import com.junction.junction_project.domain.checklist.dto.ChecklistRequestDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CheckListApiDocumentation {

  ResponseEntity<?> generateCheckList(@RequestBody ChecklistRequestDTO request);

}
