package com.howhow.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.OrderDt;
import com.howhow.entity.OrderMt;
import com.howhow.entity.ShoppingCart;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.exception.OrderNotFoundException;
import com.howhow.shopping.exception.ShoppingCartNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.OrderDtService;
import com.howhow.shopping.service.OrderMtService;
import com.howhow.shopping.service.ShoppingCartService;
import com.howhow.util.UtilityTool;

@Controller
public class OrderController {

	@Autowired
	OrderMtService omtService;
	
	@Autowired
	OrderDtService odtService;
	
	@Autowired
	ShoppingCartService sService;
	
	@Autowired
	CourseBasicService cService;
	
	@Autowired
	AccountService accService;
	
	
	@GetMapping("/createorder/{id}")
	@ResponseBody
	public boolean createOrder(@PathVariable("id") int id) throws ShoppingCartNotFoundException, UserOrCourseNotFoundException {
		
		List<ShoppingCart> shoppinglist = sService.findByUserID(id);
		UserAccountMt user = accService.findByID(id);
		
		if(shoppinglist.size()==0) return false;
		OrderMt omt = new OrderMt(user);
		
		int totalprice=0;
		List<OrderDt> dtlist = new ArrayList<OrderDt>();
		for (ShoppingCart shoppingCart : shoppinglist) {
			int courseID = shoppingCart.getCourseBasic().getCourseID();
			CourseBasic course = cService.findByID(courseID);
			int unitprice = (int)(course.getPrice()*course.getDiscount());
			totalprice+=unitprice;
			OrderDt odt = new OrderDt(unitprice, course, UtilityTool.getSysTime(),omt);
			dtlist.add(odt);
		}
		
		omt.setOrderDtList(dtlist);
		omt.setTotalPrice(totalprice);
		omt.setSystemTime(UtilityTool.getSysTime());
		omt.setOrderDate(new java.util.Date());
		
		for (ShoppingCart shoppingCart : shoppinglist) {
			int shoppingCartID = shoppingCart.getShoppingCartID();
			sService.deleteByID(shoppingCartID);
		}
		
		omtService.insertOrderMt(omt);
		return true;
	}
	
	@GetMapping("/findorder/{id}")
	@ResponseBody
	public OrderMt findByID(@PathVariable("id") int id) {
		OrderMt omt = omtService.findByID(id);
		
		return omt;
	}
	
	
	@GetMapping("/findorderbyuserid/{userid}")
	@ResponseBody
	public List<OrderMt> findByUserID(@PathVariable("userid") int userid) {
		
		List<OrderMt> list = omtService.findByUserID(userid);
		
		return list;
	}
	
	@GetMapping("/deleteorder/{id}")
	@ResponseBody
	public boolean deleteByID(@PathVariable("id") int id) throws OrderNotFoundException {
		
		omtService.deleteByID(id);
		return true;
	}
}
