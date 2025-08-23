package com.junction.junction_project.domain.constructionAddresses.repository;

import com.junction.junction_project.domain.constructionAddresses.entity.Addresses;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressesRepository extends JpaRepository<Addresses, Long> {

  @Query("SELECT a FROM Addresses a WHERE " +
      "(:keyword IS NULL OR :keyword = '') OR " +
      "(UPPER(a.englishLotAddress) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
      "UPPER(a.englishStreetAddress) LIKE UPPER(CONCAT('%', :keyword, '%')))")
  List<Addresses> findByKeyword(@Param("keyword") String keyword);

}
