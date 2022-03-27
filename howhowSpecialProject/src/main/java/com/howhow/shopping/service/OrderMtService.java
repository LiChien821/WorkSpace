package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.OrderMt;
import com.howhow.shopping.exception.OrderNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.repository.OrderMtRepository;

@Service
public class OrderMtService {

	@Autowired
	OrderMtRepository repo;

	public OrderMt findByID(int id) {
		Optional<OrderMt> bean = repo.findById(id);
		if (bean.isEmpty()) {
			System.out.println("此OrderMtID不存在");
			return null;
		}
		return bean.get();
	}

	public List<OrderMt> findByUserID(int userid) {

		List<OrderMt> list = repo.findByUserID(userid);
		return list;
	}

	public OrderMt insertOrderMt(OrderMt omt) throws UserOrCourseNotFoundException {

		try {
			OrderMt save = repo.save(omt);
			return save;
		} catch (Exception e) {
			throw new UserOrCourseNotFoundException();
		}
	}

	public OrderMt updateOrderMt(OrderMt omt) throws OrderNotFoundException {
		int orderID = omt.getOrderID();
		if (repo.findById(orderID) == null)
			throw new OrderNotFoundException();
		OrderMt save = repo.save(omt);
		return save;
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
