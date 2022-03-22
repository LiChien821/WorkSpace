package com.howhow.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.howhow.entity.FavoriteCourse;
import com.howhow.entity.UserAccountMt;

@Controller
public class FavoriteCourseController {

	/*
	 * 利用登入的帳號bean去查詢此用戶加入最愛的課程
	 * */
	public List<FavoriteCourse> findAllFavoriteCourseByAID(UserAccountMt user) {
		
		return null;
	}
	
	/*
	 * 用戶將指定最愛課程移除
	 * */
	public boolean removeFavoriteCourseByFID(int favoriteCourseID) {
		
		return false;
	}
	
	/*
	 * 用戶將指定課程加入購物車，並移除最愛列表
	 * */
	public boolean removeFavoriteCourseAndAddShopByFID(int favoriteCourseID) {
		
		return false;
	}
	
	/*
	 * 新增最愛課程
	 * */
	public boolean insertFavoriteCourse() {
		
		return false;
	}
}
