package com.promineotech.vaporVaultApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.vaporVaultApi.entity.Order;



public interface OrderRepository extends JpaRepository<Order, Long>{

}
