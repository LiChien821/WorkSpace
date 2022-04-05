package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.FavoriteCourse;
import com.howhow.entity.ShoppingCart;
import com.howhow.shopping.exception.ShoppingCartNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
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
	
	public ShoppingCart insertShoppingCart(ShoppingCart sc) throws UserOrCourseNotFoundException {
		
		try {
			ShoppingCart bean = repo.save(sc);
			return bean;
		} catch (Exception e) {
			throw new UserOrCourseNotFoundException();
		}
		
	}
	
	public boolean findShoppingCartStatus(int userid, int courseid) {
		if(repo.findShoppingCartStatus(userid, courseid)!=null) return true;
		return false;
	}
	
	
	public boolean deleteByID(int id) throws ShoppingCartNotFoundException {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new ShoppingCartNotFoundException();
		}
	}
	
	public boolean removeShoppingCart(int userid, int courseid) {
		ShoppingCart status = repo.findShoppingCartStatus(userid, courseid);
		if(status!=null) {
			repo.deleteById(status.getShoppingCartID());
			return true;
		}
		return false;
	}
	
}
