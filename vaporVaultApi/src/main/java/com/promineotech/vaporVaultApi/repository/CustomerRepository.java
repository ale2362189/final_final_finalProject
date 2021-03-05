package com.promineotech.vaporVaultApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.vaporVaultApi.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
