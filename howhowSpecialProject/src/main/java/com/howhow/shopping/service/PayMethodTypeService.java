package com.howhow.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.PayMethodType;
import com.howhow.shopping.repository.PayMethodTypeRepository;

@Service
public class PayMethodTypeService {

	@Autowired
	PayMethodTypeRepository repo;
	
	public PayMethodType findByID(int id) {
		Optional<PayMethodType> bean = repo.findById(id);
		if(bean.isEmpty()) return null;
		
		return bean.get();
	}
}
