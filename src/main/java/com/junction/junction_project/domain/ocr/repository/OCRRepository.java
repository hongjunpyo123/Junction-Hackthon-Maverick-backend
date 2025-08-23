package com.junction.junction_project.domain.ocr.repository;

import com.junction.junction_project.domain.ocr.entity.OCREntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OCRRepository extends JpaRepository<OCREntity, Long> {

}
