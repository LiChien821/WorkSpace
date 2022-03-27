package com.howhow.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.OrderStatusType;

public interface OrderStatusTypeRepository extends JpaRepository<OrderStatusType, Integer> {
	
}
