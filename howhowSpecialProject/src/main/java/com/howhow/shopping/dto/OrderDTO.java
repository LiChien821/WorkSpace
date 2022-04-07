package com.howhow.shopping.dto;

import java.util.List;

import com.howhow.entity.CourseBasic;

public class OrderDTO {

	int customfield1;
	int totalprice;
	String description;
	String shopname;
	
	public int getCustomfield1() {
		return customfield1;
	}
	public void setCustomfield1(int customfield1) {
		this.customfield1 = customfield1;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
	
}