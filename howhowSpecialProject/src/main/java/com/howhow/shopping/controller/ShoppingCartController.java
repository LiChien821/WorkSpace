package com.howhow.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.howhow.entity.ShoppingCart;
import com.howhow.entity.UserAccountMt;

@Controller
public class ShoppingCartController {

	/*
	 * 利用用戶bean物件查詢存在購物車的課程
	 * */
	public List<ShoppingCart> findAllProductByAID(UserAccountMt user) {
		
		return null;
	}
	
	/*
	 * 移除購物車指定商品
	 * */
	public boolean removeProductBySID(int shoppingCartID) {
		
		return false;
	}
	
	/*
	 * 移除購物車課程並加入最愛
	 * */
	public boolean removeProductAndAddFCBySID(int shoppingCartID) {
		
		return false;
	}
	
	/*
	 * 新增購物車商品
	 * */
	public boolean insertShoppingCartProduct() {
		
		return false;
	}
}
