package com.howhow.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.howhow.entity.PurchasedCourse;
import com.howhow.entity.UserAccountMt;

@Controller
public class PurchaseController {

	/*
	 * 尋找用戶購買的課程
	 * */
	public List<PurchasedCourse> findPurchasedCourseByAID(UserAccountMt user) {
		
		return null;
	}
	
	/*
	 * 用戶購買購物車中的課程，購買後移除購物車內容
	 * */
	public boolean purchaseCourseAction(UserAccountMt user) {
		
		return false;
	}
	
}
