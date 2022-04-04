package com.howhow.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.entity.FavoriteCourse;
import com.howhow.entity.OrderMt;
import com.howhow.entity.PurchasedCourse;
import com.howhow.shopping.exception.DuplicatedPurchasedCourseException;
import com.howhow.shopping.exception.OrderNotFoundException;
import com.howhow.shopping.service.FavoriteCourseService;
import com.howhow.shopping.service.OrderMtService;
import com.howhow.shopping.service.ShoppingCartService;
import com.howhow.student.dto.PurchasedCourseDTO;
import com.howhow.student.exception.OrderStatusErrorException;
import com.howhow.student.service.PurchasedCourseService;

@Controller
public class PurchasedCourseController {

	@Autowired
	PurchasedCourseService pService;

	@Autowired
	OrderMtService oService;
	
	@Autowired
	ShoppingCartService sService;
	
	@GetMapping("/findpurchasedcoursebyuserid/{id}")
	@ResponseBody
	public List<PurchasedCourseDTO> findPurchasedCourseByUserID(@PathVariable("id") int userId) {

		List<PurchasedCourseDTO> dtolist = new ArrayList<PurchasedCourseDTO>();
		List<PurchasedCourse> list = pService.findByUserID(userId);
		for (PurchasedCourse purchasedCourse : list) {
			int courseID = purchasedCourse.getCourseBasic().getCourseID();
			String courseName = purchasedCourse.getCourseBasic().getCourseName();
			String categoryname = purchasedCourse.getCourseBasic().getCategory().getName();
			PurchasedCourseDTO pcd = new PurchasedCourseDTO(userId, courseID, courseName, categoryname);
			dtolist.add(pcd);
		}

		return dtolist;
	}

	@GetMapping("/findpurchasedcoursebyid/{id}")
	@ResponseBody
	public PurchasedCourseDTO findbyID(@PathVariable("id") int id) {
		PurchasedCourse bean = pService.findByID(id);
		int userId = bean.getUserAccountMt().getUserId();
		int courseID = bean.getCourseBasic().getCourseID();

		PurchasedCourseDTO dto = new PurchasedCourseDTO(userId, courseID);

		return dto;
	}

	@GetMapping("/insertpurchasedcoursebyorderid/{id}")
	@ResponseBody
	public List<PurchasedCourseDTO> insertPurchasedCourse(@PathVariable("id") int orderid)
			throws OrderNotFoundException, OrderStatusErrorException, DuplicatedPurchasedCourseException {

		List<PurchasedCourseDTO> dtolist = new ArrayList<PurchasedCourseDTO>();
		try {
			List<PurchasedCourse> insertPurchasedCourse = pService.insertPurchasedCourse(orderid);

			OrderMt omt = oService.findByID(orderid);
			omt.setOrderStatusTypeID(20);
			oService.updateOrderMt(omt);

			for (PurchasedCourse purchasedCourse : insertPurchasedCourse) {
				int courseID = purchasedCourse.getCourseBasic().getCourseID();
				int userId = purchasedCourse.getUserAccountMt().getUserId();
				PurchasedCourseDTO pcd = new PurchasedCourseDTO(userId, courseID);
				
				if(sService.findShoppingCartStatus(userId, courseID)) {
					sService.removeShoppingCart(userId, courseID);
				}
				
				dtolist.add(pcd);
			}

			return dtolist;
		} catch (Exception e) {
			throw new DuplicatedPurchasedCourseException();
		}
	}

	@GetMapping("/findpurchasedcoursebycourseid/{id}")
	@ResponseBody
	public List<PurchasedCourseDTO> findByCourseID(@PathVariable("id") int courseid) {
		List<PurchasedCourseDTO> dtolist = new ArrayList<PurchasedCourseDTO>();
		List<PurchasedCourse> list = pService.findPurchasedCourseByCourseID(courseid);
		for (PurchasedCourse purchasedCourse : list) {
			int userId = purchasedCourse.getUserAccountMt().getUserId();
			PurchasedCourseDTO pcd = new PurchasedCourseDTO(userId, courseid);
			dtolist.add(pcd);
		}
		return dtolist;
	}
	
	@GetMapping("/findpurchasedcoursestatusbyuserid/{userid}")
	@ResponseBody
	public List<Integer> findPurchasedCourseListStatusByUserID(@PathVariable("userid") int userid) {
		List<Integer> purchasedstatus = new ArrayList<Integer>();
		
		List<PurchasedCourse> list = pService.findByUserID(userid);
		for (PurchasedCourse purchasedCourse : list) {
			int courseID = purchasedCourse.getCourseBasic().getCourseID();
			purchasedstatus.add(courseID);
		}
		
		return purchasedstatus;
	}
	
	@GetMapping("/findpurchasedcoursestatus/{userid}/{courseid}")
	@ResponseBody
	public boolean findPurchasedCourseStatus(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid) {
		boolean status = pService.findPurchasedStatus(userid, courseid);
		return status;
	}

}
