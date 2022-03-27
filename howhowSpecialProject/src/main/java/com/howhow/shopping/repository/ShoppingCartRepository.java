package com.howhow.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
	
	@Query(value="select * from ShoppingCart where user_id=?1", nativeQuery=true)
	public List<ShoppingCart> findByUserID(int userid);
}
