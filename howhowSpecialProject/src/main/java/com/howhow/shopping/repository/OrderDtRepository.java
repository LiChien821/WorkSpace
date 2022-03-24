package com.howhow.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.OrderDt;

public interface OrderDtRepository extends JpaRepository<OrderDt, Integer> {
	
	@Query(value="select * from OrderDt where order_id=?1", nativeQuery=true)
	public List<OrderDt> findByOrderID(int orderid);
}
