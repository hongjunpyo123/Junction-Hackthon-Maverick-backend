package com.junction.junction_project.domain.constructionAddresses.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

  @OneToOne(fetch = jakarta.persistence.FetchType.LAZY)
  @JoinColumn(name = "addresses_info_id")
  private AddressesInfo addressesInfo;

}
