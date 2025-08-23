package com.junction.junction_project.domain.constructionAddresses.repository;

import com.junction.junction_project.domain.constructionAddresses.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepository extends JpaRepository<Addresses, Long> {


}
