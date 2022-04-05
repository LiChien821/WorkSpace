package com.howhow.shopping.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.entity.Category;
import com.howhow.shopping.service.CategoryService;

@Controller
public class CategoryShoppingController {
	
	@Autowired
	CategoryService cService;
	
	@GetMapping("/api/getallcategory")
	@ResponseBody
	public List<Category> getCategoryInfo() {
		
		List<Category> all = cService.findAll();
		return all;
	}
	

	@GetMapping("/api/findcategorydetail/{catid}")
	@ResponseBody
	public Category findById(@PathVariable("catid") int id) {
		
		Category bean = cService.findByID(id);
		return bean;
	}

}
