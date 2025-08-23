package com.junction.junction_project.domain.SafetyAssessment.repository;

import com.junction.junction_project.domain.SafetyAssessment.entity.SafetyAssessment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafetyAssessmentRepository extends JpaRepository<SafetyAssessment, Long> {
  List<SafetyAssessment> findTop7ByOrderByIdDesc();
}
