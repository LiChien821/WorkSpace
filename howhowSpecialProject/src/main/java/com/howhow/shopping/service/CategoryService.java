package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.Category;
import com.howhow.shopping.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repo;
	
	public Category findByID(int id) {
		Optional<Category> bean = repo.findById(id);
		return bean.get();
	}
	
	public List<Category> findAll() {
		List<Category> all = repo.findAll();
		return all;
	}
	
}
