package com.howhow.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.OrderMt;

public interface OrderMtRepository extends JpaRepository<OrderMt, Integer> {
	
	@Query(value="select * from OrderMt where user_id=?1", nativeQuery = true)
	public List<OrderMt> findByUserID(int userid);
}
