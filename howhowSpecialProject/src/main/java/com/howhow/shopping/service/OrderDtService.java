package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.OrderDt;
import com.howhow.shopping.exception.OrderNotFoundException;
import com.howhow.shopping.repository.OrderDtRepository;

@Service
public class OrderDtService {

	@Autowired
	OrderDtRepository repo;
	
	public OrderDt findByID(int id) {
		
		Optional<OrderDt> bean = repo.findById(id);
		if(bean.isEmpty()) {
			System.out.println("此orderdtid不存在");
			return null;
		}
		return bean.get();
	}
	
	public List<OrderDt> findByOrderID(int orderid) throws OrderNotFoundException {
		
		try {
			List<OrderDt> list = repo.findByOrderID(orderid);
			return list;
		} catch (Exception e) {
			throw new OrderNotFoundException();
		}
	}
	
	public boolean deleteByID(int id) throws OrderNotFoundException {
		
		try {
		repo.deleteById(id);
		return true;
		} catch (Exception e) {
			throw new OrderNotFoundException();
		}
	}
	
}
