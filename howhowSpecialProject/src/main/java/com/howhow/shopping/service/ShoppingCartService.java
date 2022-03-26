package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.ShoppingCart;
import com.howhow.shopping.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	ShoppingCartRepository repo;
	
	public ShoppingCart findByID(int id) {
		Optional<ShoppingCart> bean = repo.findById(id);
		if(bean.isEmpty()) return null;
		
		return bean.get();
	}
	
	public List<ShoppingCart> findByUserID(int userid) {
		List<ShoppingCart> list = repo.findByUserID(userid);
		return list;
	}
	
	public ShoppingCart insertShoppingCart(ShoppingCart sc) {
		
		ShoppingCart bean = repo.save(sc);
		
		return bean;
	}
	
	public boolean findShoppingCartStatus(int userid, int courseid) {
		if(repo.findShoppingCartStatus(userid, courseid)!=null) return true;
		return false;
	}
	
	
	public boolean deleteByID(int id) {
		if(repo.findById(id).isEmpty()) return false;
	
		repo.deleteById(id);
		return true;
	}
}
