package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.OrderMt;
import com.howhow.shopping.repository.OrderMtRepository;

@Service
public class OrderMtService {

	@Autowired
	OrderMtRepository repo;
	
	public OrderMt findByID(int id) {
		Optional<OrderMt> bean = repo.findById(id);
		if(bean.isEmpty()) {
			System.out.println("此OrderMtID不存在");
			return null;
		}
		return bean.get();
	}
	
	public List<OrderMt> findByUserID(int userid) {
		
		List<OrderMt> list = repo.findByUserID(userid);
		return list;
	}
	
	public OrderMt insertOrderMt(OrderMt omt) {
		OrderMt save = repo.save(omt);
		return save;
	}
	
	public OrderMt updateOrderMt(OrderMt omt) {
		int orderID = omt.getOrderID();
		if(repo.findById(orderID)==null) return null;
		OrderMt save = repo.save(omt);
		return save;
	}
	
	
	public boolean deleteByID(int id) {
		
		if(repo.findById(id).isEmpty()) {
			System.out.println("此ordermtid不存在，無法刪除");
			return false;
		}
		
		repo.deleteById(id);
		return true;
	}
	
	
}
