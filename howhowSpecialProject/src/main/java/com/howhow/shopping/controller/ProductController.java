package com.howhow.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.howhow.entity.CourseBasic;

@Controller
public class ProductController {
	
	
	/*
	 * 查詢所有課程
	 * */
	public List<CourseBasic> findAllCourse() {
		
		return null;
	}
	
	
	/*
	 * 依照課程類別查詢課程
	 * */
	public List<CourseBasic> findCourseListByCategory(int catID) {
		
		return null;
	}
	
	/*
	 * 依照課程名稱關鍵字搜尋
	 * */
	public List<CourseBasic> findCourseListByNameLike(String name) {
		
		return null;
	}
	
	/*
	 * 依照課程ID搜尋課程 估計給後台使用
	 * */
	public CourseBasic findCourseByCID(int courseid) {
		
		return null;
	}
}
