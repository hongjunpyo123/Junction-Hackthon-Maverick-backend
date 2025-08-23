package com.junction.junction_project.domain.constructionAddresses.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Addresses {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String koreanAddress;
  private String englishAddress;

}
