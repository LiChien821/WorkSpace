package com.howhow.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.OrderStatusType;
import com.howhow.shopping.repository.OrderStatusTypeRepository;

@Service
public class OrderStatusTypeService {

	@Autowired
	OrderStatusTypeRepository repo;
	
	public OrderStatusType findByID(int id) {
		Optional<OrderStatusType> bean = repo.findById(id);
		
		if(bean.isEmpty()) return null;
		
		return bean.get();
	}
}
