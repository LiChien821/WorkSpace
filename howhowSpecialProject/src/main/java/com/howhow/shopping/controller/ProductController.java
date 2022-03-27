package com.howhow.shopping.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountDetailService;
import com.howhow.entity.Category;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.UserAccountDt;
import com.howhow.shopping.DTO.CourseBasicDTO;
import com.howhow.shopping.service.CategoryService;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.util.UtilityTool;

@Controller
public class ProductController {
	
	@Autowired
	CourseBasicService cService;
	
	@Autowired
	CourseRankService crService;
	
	@Autowired
	AccountDetailService acdService;
	
	@Autowired
	CategoryService catService;
	/*
	 * Find BY ID
	 * */
	@GetMapping("/findcoursebyid/{id}")
	@ResponseBody
	public CourseBasicDTO findCourseByID(@PathVariable("id") int courseid) {
		CourseBasic course = cService.findByID(courseid);
		if(course==null) return null;
		CourseBasicDTO dto = dtoutils(course);

		Integer id = course.getCategory().getId();
		int creatorid = course.getCreator().getUserId();
		String courseCover = course.getCourseCover();
		String description = course.getDescription();
		UserAccountDt userbean = acdService.findByID(creatorid);
		String creatorName = userbean.getGivenName();
		
		
		dto.setCategoryid(id);
		dto.setDescription(description);
		dto.setCover(courseCover);
		dto.setCreatorName(creatorName);
		
		return dto;
	}
	
	
	/*
	 * 查詢所有課程
	 * */
	@GetMapping("/findallcourses")
	@ResponseBody
	public List<CourseBasicDTO> findAllCourse() {
		
		List<CourseBasicDTO> dtoList = new ArrayList<CourseBasicDTO>();
		List<CourseBasic> list = cService.findAll();
		
		for (CourseBasic courseBasic : list) {
			CourseBasicDTO cb = dtoutils(courseBasic);
			dtoList.add(cb);
		}
		
		return dtoList;
	}
	
	
	/*
	 * 依照課程類別查詢課程
	 * */
	@GetMapping("/findcoursebycategoryid/{id}")
	@ResponseBody
	public List<CourseBasicDTO> findCourseListByCategory(@PathVariable("id") int catID) {
		
		List<CourseBasicDTO> dtoList = new ArrayList<CourseBasicDTO>();
		List<CourseBasic> list = cService.findByCategoryID(catID);
		
		for (CourseBasic courseBasic : list) {
			CourseBasicDTO cb = dtoutils(courseBasic);
			dtoList.add(cb);
		}
		
		return dtoList;
	}
	
	/*
	 * 依照課程名稱關鍵字搜尋
	 * */
	@GetMapping("/findcoursebynamelike/{name}")
	@ResponseBody
	public List<CourseBasicDTO> findCourseListByNameLike(@PathVariable("name")	String name) {
		
		List<CourseBasicDTO> dtoList = new ArrayList<CourseBasicDTO>();
		List<CourseBasic> list = cService.findByCourseNameLike(name);
		
		for (CourseBasic courseBasic : list) {
			CourseBasicDTO cb = dtoutils(courseBasic);
			dtoList.add(cb);
		}
		return dtoList;
	}
	
	@PostMapping("/insertcourse")
	@ResponseBody
	public CourseBasic insertCourseBasic(@RequestBody CourseBasicDTO coursebasicDTO) {
		
		
		int creatorid = coursebasicDTO.getCreatorid();
		int categoryid = coursebasicDTO.getCategoryid();
		UserAccountDt dt = acdService.findByID(creatorid);
		Category cat = catService.findByID(categoryid);
		
		CourseBasic cb = new CourseBasic(coursebasicDTO.getCourseName(), coursebasicDTO.getPrice(), coursebasicDTO.getDiscount(), cat, coursebasicDTO.getCoursestatus(), coursebasicDTO.getCover(), coursebasicDTO.getDescription(), UtilityTool.getSysTime(), dt);
		CourseBasic insertCourseBasic = cService.insertCourseBasic(cb);
		
		return insertCourseBasic;
	}
	
	@PostMapping("/updatecourse")
	@ResponseBody
	public CourseBasic updateCourseBasic(@RequestBody CourseBasicDTO coursebasicDTO) {
		
		int courseID = coursebasicDTO.getCourseID();
		CourseBasic course = cService.findByID(courseID);
		if(course==null) {
			System.out.println("COURSEID不存在，無法修改");
			return null;
		}
		if(coursebasicDTO.getCourseName()!=null) course.setCourseName(coursebasicDTO.getCourseName());
		if(coursebasicDTO.getCoursestatus()!=0) course.setCourseStatus(coursebasicDTO.getCoursestatus());
		if(coursebasicDTO.getDescription()!=null) course.setDescription(coursebasicDTO.getDescription());
		if(coursebasicDTO.getCover()!=null) course.setCourseCover(coursebasicDTO.getCover());
		if(coursebasicDTO.getPrice()!=0) course.setPrice(coursebasicDTO.getPrice());
		if(coursebasicDTO.getDiscount()!=0) course.setDiscount(coursebasicDTO.getDiscount());
		
		CourseBasic insertCourseBasic = cService.updateCourseBasic(course);
		
		return insertCourseBasic;
	}
	
	@GetMapping("/deletecourse/{id}")
	@ResponseBody
	public boolean deleteCourseBasic(@PathVariable("id") int id) {
		
		if(cService.findByID(id)==null) return false;
		
		cService.deleteById(id);
		return true;
		
	}
	
	public double rankUtilsByCourseID(int id) {
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
	
	private CourseBasicDTO dtoutils(CourseBasic courseBasic) {
		int courseID = courseBasic.getCourseID();
		String courseName = courseBasic.getCourseName();
		long price = courseBasic.getPrice();
		double discount = courseBasic.getDiscount();
		int discountprice = (int)(price*discount);
		double rank = rankUtilsByCourseID(courseID);
		
		CourseBasicDTO cb = new CourseBasicDTO(courseID, courseName, price, discountprice, rank);
		return cb;
	}
}
