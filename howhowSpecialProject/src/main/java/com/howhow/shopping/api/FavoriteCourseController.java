package com.howhow.shopping.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.howhow.account.controller.AccountController;
import com.howhow.account.service.AccountDetailService;
import com.howhow.account.service.AccountService;
import com.howhow.cms.service.CourseStatusTypeService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.CourseStatusType;
import com.howhow.entity.FavoriteCourse;
import com.howhow.entity.ShoppingCart;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.dto.SearchingCourseDTO;
import com.howhow.shopping.dto.SimpleCourseDTO;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.exception.FavoriteCourseNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.shopping.service.FavoriteCourseService;
import com.howhow.shopping.service.ShoppingCartService;
import com.howhow.student.service.PurchasedCourseService;
import com.howhow.util.UtilityTool;

@Controller
public class FavoriteCourseController {
	
	@Autowired
	AccountController aController;
	
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
	
	@Autowired
	ShoppingCartService sService;
	
	@Autowired
	CourseStatusTypeService cstaService;
	
	@Autowired
	PurchasedCourseService pService;
	/*
	 * ?????????????????????bean???????????????????????????????????????
	 * */
	
	@GetMapping("/api/findfavoritecourse/{id}")
	@ResponseBody
	public List<SimpleCourseDTO> findAllFavoriteCourseByUserID(@PathVariable("id") int userid) {
		
		List<FavoriteCourse> list = fService.findByUserID(userid);
		List<SimpleCourseDTO> dtoList = new ArrayList<SimpleCourseDTO>();
		for (FavoriteCourse favoriteCourse : list) {
			int favoriteCourseID = favoriteCourse.getFavoriteCourseID();	
			int courseID = favoriteCourse.getCourseBasic().getCourseID();	
			CourseBasic course = cService.findByID(courseID);
			String courseName = course.getCourseName();		
			long price = course.getPrice();		
			double discount = course.getDiscount();
			int discountPrice = (int) (price * discount);		
			double rank = rankUtilsByCourseID(courseID);		
			
			SimpleCourseDTO dto = new SimpleCourseDTO(favoriteCourseID, courseID, courseName, price, discountPrice, rank);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	/*
	 * ?????????????????????????????????
	 * */
	@GetMapping("/api/deletefavoritecourse/{id}")
	@ResponseBody
	public boolean removeFavoriteCourseByID(@PathVariable("id") int id) throws FavoriteCourseNotFoundException {
		
		boolean status = fService.deleteByID(id);
		return status;
	}
	
	/*
	 * ????????????????????????????????????????????????????????????
	 * */
	@GetMapping("/api/movetoshoppingcart/{id}")
	@ResponseBody
	public boolean removeFavoriteCourseAndAddShopByFID(@PathVariable("id") int id) throws UserOrCourseNotFoundException, FavoriteCourseNotFoundException {
		
		FavoriteCourse favorite = fService.findByID(id);
		if(favorite == null) throw new FavoriteCourseNotFoundException();
		
		int courseID = favorite.getCourseBasic().getCourseID();
		int userId = favorite.getUserAccountMt().getUserId();
		
		UserAccountMt mt = accService.findByID(userId);
		CourseBasic cb = cService.findByID(courseID);
		
		ShoppingCart sc = new ShoppingCart(UtilityTool.getSysTime(), mt, cb);
		sService.insertShoppingCart(sc);
		
		fService.deleteByID(id);
		
		return true;
	}
	
	/*
	 * ??????????????????
	 * */
	@PostMapping("/api/insertfavoritecourse")
	@ResponseBody
	public FavoriteCourse insertFavoriteCourse(@RequestBody SimpleCourseDTO favoritecourseDTO) throws UserOrCourseNotFoundException {
		
		UserAccountMt mt = accService.findByID(favoritecourseDTO.getUserID());
		CourseBasic cb = cService.findByID(favoritecourseDTO.getCourseID());
		
		FavoriteCourse fc = new FavoriteCourse(UtilityTool.getSysTime(), mt, cb);
		
		FavoriteCourse insertFavoriteCourse = fService.insertFavoriteCourse(fc);
		
		return insertFavoriteCourse;
	}
	
	@GetMapping("/api/findfavoritecoursestatus/{userid}/{courseid}")
	@ResponseBody
	public boolean findFavoriteCourseStatus(@PathVariable("userid") int userid,@PathVariable("courseid") int courseid) {
		
		boolean status = fService.findFavoriteCourseStatus(userid, courseid);
		
		return status;
	}
	
	@GetMapping("/api/removefavoritecourse/{userid}/{courseid}")
	@ResponseBody
	public boolean removeFavoriteCourse(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid) {
		boolean status = fService.removeFavoriteCourse(userid, courseid);
		return status;
	}
	
	@GetMapping("/api/findfavoritecoursestatusbyuserid/{userid}")
	@ResponseBody
	public List<Integer> findFavoriteCourseByUserID(@PathVariable("userid") int userid) {
		List<Integer> favstatus = new ArrayList<Integer>();
		
		List<FavoriteCourse> list = fService.findByUserID(userid);
		for (FavoriteCourse favoriteCourse : list) {
			int courseID = favoriteCourse.getCourseBasic().getCourseID();
			favstatus.add(courseID);
		}
		
		return favstatus;
	}
	
	@GetMapping("/api/findfavoritecoursedetailbyuserid/{userid}")
	@ResponseBody
	public List<SearchingCourseDTO> findFavoriteCourseDetailByUserID(@PathVariable("userid") int userid) throws CourseNotFoundException {
		
		List<SearchingCourseDTO> searchList = new ArrayList<SearchingCourseDTO>();
		
		List<FavoriteCourse> list = fService.findByUserID(userid);
		for (FavoriteCourse favoriteCourse : list) {
			int courseID = favoriteCourse.getCourseBasic().getCourseID();
			
			SearchingCourseDTO searchutils = searchutils(courseID);
			searchutils.setUserid(userid);
			searchList.add(searchutils);
		}
		return searchList;
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
	
	private SearchingCourseDTO searchutils(int courseID) throws CourseNotFoundException {
		
		CourseBasic course = cService.findByID(courseID);
		String courseName = course.getCourseName();
		Integer statusID = course.getStatusType().getStatusID();
		CourseStatusType cat = cstaService.findById(statusID);
		String statusName = cat.getStatusName();
		Integer studentnum = pService.findStudentCount(courseID);
		
		SearchingCourseDTO searchDTO = new SearchingCourseDTO();
		
		long price = course.getPrice();
		double discount = course.getDiscount();
		int discountprice = (int) (price * discount);
		
		int ranknum = 0;
		double totalrank = 0;
		
		List<CourseRank> listrank = crService.findByCourseID(courseID);
		for (CourseRank courseRank : listrank) {
			int ranka = courseRank.getCourseRank();
			totalrank += ranka;
		}

		ranknum = listrank.size();
		double rank = totalrank / ranknum;
		
		searchDTO.setCourseid(courseID);
		searchDTO.setCoursename(courseName);
		searchDTO.setCatgoryname(statusName);
		searchDTO.setStudentnum(studentnum);
		searchDTO.setDiscountprice(discountprice);
		searchDTO.setRank(rank);
		searchDTO.setRanknum(ranknum);
		String url = "/product?id=" + Integer.toString(courseID);
		searchDTO.setUrl(url);
		searchDTO.setCover(course.getCourseCover());
		
		return searchDTO;
	}
	
	
	
}
