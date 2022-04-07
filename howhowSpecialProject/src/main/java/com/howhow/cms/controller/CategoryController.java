package com.howhow.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.entity.Category;
import com.howhow.entity.CourseBasic;
import com.howhow.shopping.service.CategoryService;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.util.UtilityTool;

@Controller
@RequestMapping("/cms")
public class CategoryController {

	@Autowired
	private CategoryService cs;

	
	@GetMapping("/category")
	public String guidToCategory() {
		return "/cms/categorymain.html";
	}
	
	// 顯示所有課程類別
	@ResponseBody
	@GetMapping("/categorydata")

	public List<Category> showCategory() {
		return cs.findAllCategory();
	}

	// 增加課程類別
	@ResponseBody
	@PostMapping("/categorydata")

	public List<Category> addCategory(@RequestBody Category category) {
		
		if (!cs.checkExist(category.getName())) {
			
			category.setSystemTime(UtilityTool.getSysTime());
			
			cs.addCategory(category);
		}
		return showCategory();
	}

	// 刪除課程種類
	@ResponseBody
	@DeleteMapping("/categorydata")
	public List<Category> deleteCategory(@RequestBody Category category){
		if(cs.findByID(category.getId()).getCourseBasicList().isEmpty()) {
			cs.deleteCategory(category);
		}
		return showCategory();
	}
	
	// 修改課程種類
	@ResponseBody
	@PutMapping("/categorydata")
	public List<Category> updateCategory(@RequestBody Category category){
		
		cs.updateCategory(category);
		return showCategory();
	}

}
