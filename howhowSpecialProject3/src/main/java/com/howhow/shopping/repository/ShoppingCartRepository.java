package com.howhow.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

}
