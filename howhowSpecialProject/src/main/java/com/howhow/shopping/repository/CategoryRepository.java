package com.howhow.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findByname(String name);
}
