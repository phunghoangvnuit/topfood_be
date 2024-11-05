package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
