package com.howhow.shopping.dto;

import java.util.List;

import com.howhow.entity.CourseBasic;

public class OrderDTO {

	String customfield1;
	int totalamount;
	String itemname;
	String description;
	
	public String getCustomfield1() {
		return customfield1;
	}
	public void setCustomfield1(String customfield1) {
		this.customfield1 = customfield1;
	}
	public int getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
