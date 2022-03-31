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

	public List<Category> findAllCategory() {
		return repo.findAll();
	}

	public Category addCategory(Category category) {
		return repo.save(category);
	}

	public boolean checkExist(String name) {
		if (null != repo.findByname(name)) {
			return true;
		}
		return false;
	}

	public void deleteCategory(Category category) {
		repo.delete(category);
	}
	
	public Category updateCategory(Category category) {
		return repo.save(category);
	}
}
