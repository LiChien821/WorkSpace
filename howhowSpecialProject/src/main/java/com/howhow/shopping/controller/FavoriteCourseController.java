package com.howhow.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountDetailService;
import com.howhow.account.service.AccountService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.FavoriteCourse;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.DTO.SimpleCourseDTO;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.shopping.service.FavoriteCourseService;
import com.howhow.util.UtilityTool;

@Controller
public class FavoriteCourseController {

	@Autowired
	FavoriteCourseService fService;
	
	@Autowired
	CourseBasicService cService;
	
	@Autowired
	AccountDetailService aService;
	
	@Autowired
	CourseRankService crService;
	
	@Autowired
	AccountService accService;
	/*
	 * 利用登入的帳號bean去查詢此用戶加入最愛的課程
	 * */
	
	@GetMapping("/findfavoritecourse/{id}")
	@ResponseBody
	public List<SimpleCourseDTO> findAllFavoriteCourseByUserID(@PathVariable("id") int userid) {
		
		List<FavoriteCourse> list = fService.findByUserID(userid);
		List<SimpleCourseDTO> dtoList = new ArrayList<SimpleCourseDTO>();
		for (FavoriteCourse favoriteCourse : list) {
			int favoriteCourseID = favoriteCourse.getFavoriteCourseID();	
			int courseID = favoriteCourse.getCourseBasic().getCourseID();	
			CourseBasic course = cService.findByID(courseID);
			String courseName = course.getCourseName();		
			int price = course.getPrice();		
			double discount = course.getDiscount();
			int discountPrice = (int) (price * discount);		
			double rank = rankUtilsByCourseID(courseID);		
			
			SimpleCourseDTO dto = new SimpleCourseDTO(favoriteCourseID, courseID, courseName, price, discountPrice, rank);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	/*
	 * 用戶將指定最愛課程移除
	 * */
	@GetMapping("/deletefavoritecourse/{id}")
	@ResponseBody
	public boolean removeFavoriteCourseByID(@PathVariable("id") int id) {
		
		boolean status = fService.deleteByID(id);
		
		return status;
	}
	
	/*
	 * 用戶將指定課程加入購物車，並移除最愛列表
	 * */
	@GetMapping("/putfavoritecoursetoshoppingcart/{id}")
	public boolean removeFavoriteCourseAndAddShopByFID(int id) {
		
		return false;
	}
	
	/*
	 * 新增最愛課程
	 * */
	@PostMapping("/insertfavoritecourse")
	@ResponseBody
	public FavoriteCourse insertFavoriteCourse(@RequestBody SimpleCourseDTO favoritecourseDTO) {
		
		UserAccountMt mt = accService.findByID(favoritecourseDTO.getUserID());
		CourseBasic cb = cService.findByID(favoritecourseDTO.getCourseID());
		
		FavoriteCourse fc = new FavoriteCourse(UtilityTool.getSysTime(), mt, cb);
		
		FavoriteCourse insertFavoriteCourse = fService.insertFavoriteCourse(fc);
		
		return insertFavoriteCourse;
	}
	
	private double rankUtilsByCourseID(int id) {
		double count = 0;
		double totalrank = 0;
		
		List<CourseRank> list = crService.findByCourseID(id);
		for (CourseRank courseRank : list) {
			int rank = courseRank.getCourseRank();
			totalrank+=rank;
		}
		
		count=list.size();
		
		return totalrank/count;
	}
}
