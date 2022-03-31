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

import com.howhow.account.service.AccountService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.FavoriteCourse;
import com.howhow.entity.ShoppingCart;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.dto.SimpleCourseDTO;
import com.howhow.shopping.exception.ShoppingCartNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.shopping.service.FavoriteCourseService;
import com.howhow.shopping.service.ShoppingCartService;
import com.howhow.util.UtilityTool;

@Controller
public class ShoppingCartController {

	@Autowired
	ShoppingCartService sService;

	@Autowired
	CourseBasicService cService;

	@Autowired
	CourseRankService crService;

	@Autowired
	AccountService accService;

	@Autowired
	FavoriteCourseService fService;

	@GetMapping("/findshoppingcartbyid/{id}")
	@ResponseBody
	public ShoppingCart findById(@PathVariable("id") int id) {
		ShoppingCart bean = sService.findByID(id);
		return bean;
	}

	/*
	 * 利用用戶bean物件查詢存在購物車的課程
	 */
	@GetMapping("/findshoppingcartbyuserid/{userid}")
	@ResponseBody
	public List<SimpleCourseDTO> findAllProductByID(@PathVariable("userid") int userid) {

		List<SimpleCourseDTO> dtoList = new ArrayList<SimpleCourseDTO>();
		List<ShoppingCart> list = sService.findByUserID(userid);
		for (ShoppingCart shoppingCart : list) {
			int shoppingCartID = shoppingCart.getShoppingCartID();
			int courseID = shoppingCart.getCourseBasic().getCourseID();
			CourseBasic course = cService.findByID(courseID);
			String courseName = course.getCourseName();
			long price = course.getPrice();
			double discount = course.getDiscount();
			int discountPrice = (int) (price * discount);
			double rank = rankUtilsByCourseID(courseID);

			SimpleCourseDTO dto = new SimpleCourseDTO(shoppingCartID, courseID, courseName, price, discountPrice, rank);
			dtoList.add(dto);
		}

		return dtoList;
	}

	/*
	 * 移除購物車指定商品
	 */
	@GetMapping("/removeshoppingcartbyid/{id}")
	@ResponseBody
	public boolean removeProductByID(@PathVariable("id") int id) throws ShoppingCartNotFoundException {

		sService.deleteByID(id);
		return true;
	}

	/*
	 * 移除購物車課程並加入最愛
	 */
	@GetMapping("/movetofavoritecourse/{id}")
	@ResponseBody
	public boolean removeProductAndAddFCBySID(@PathVariable("id") int id) throws UserOrCourseNotFoundException, ShoppingCartNotFoundException {
		ShoppingCart shopping = sService.findByID(id);
		if (shopping == null) throw new ShoppingCartNotFoundException();
		
		int courseID = shopping.getCourseBasic().getCourseID();
		int userId = shopping.getUserAccountMt().getUserId();

		UserAccountMt mt = accService.findByID(userId);
		CourseBasic cb = cService.findByID(courseID);

		FavoriteCourse fc = new FavoriteCourse(UtilityTool.getSysTime(), mt, cb);
		FavoriteCourse insertFavoriteCourse = fService.insertFavoriteCourse(fc);

		sService.deleteByID(id);

		return true;
	}

	@PostMapping("/insertshoppingcart")
	@ResponseBody
	public ShoppingCart insertShoppingCart(@RequestBody SimpleCourseDTO simplecourseDTO) throws UserOrCourseNotFoundException {

		UserAccountMt mt = accService.findByID(simplecourseDTO.getUserID());
		CourseBasic cb = cService.findByID(simplecourseDTO.getCourseID());
		if (mt == null || cb == null)
			return null;
		ShoppingCart fc = new ShoppingCart(UtilityTool.getSysTime(), mt, cb);
		ShoppingCart insertShoppingCart = sService.insertShoppingCart(fc);
		return insertShoppingCart;
	}

	private double rankUtilsByCourseID(int id) {
		double count = 0;
		double totalrank = 0;

		List<CourseRank> list = crService.findByCourseID(id);
		for (CourseRank courseRank : list) {
			int rank = courseRank.getCourseRank();
			totalrank += rank;
		}

		count = list.size();

		return totalrank / count;
	}
	
	@GetMapping("/removeshoppingcart/{userid}/{courseid}")
	@ResponseBody
	public boolean removeShoppingCart(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid) {
		boolean status = sService.removeShoppingCart(userid, courseid);
		return status;
	}
	
}
