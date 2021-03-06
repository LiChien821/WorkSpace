package com.howhow.shopping.api;

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
import com.howhow.cms.service.CourseStatusTypeService;
import com.howhow.entity.Category;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.CourseStatusType;
import com.howhow.entity.FavoriteCourse;
import com.howhow.entity.ShoppingCart;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.dto.SearchingCourseDTO;
import com.howhow.shopping.dto.SimpleCourseDTO;
import com.howhow.shopping.exception.AlreadyPurchasedException;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.exception.ShoppingCartNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.service.CategoryService;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.shopping.service.FavoriteCourseService;
import com.howhow.shopping.service.ShoppingCartService;
import com.howhow.student.service.PurchasedCourseService;
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
	
	@Autowired
	PurchasedCourseService pService;
	
	@Autowired
	CourseStatusTypeService cstaService;
	
	@Autowired
	CategoryService catService;
	
	@GetMapping("/api/findshoppingcartbyid/{id}")
	@ResponseBody
	public ShoppingCart findById(@PathVariable("id") int id) {
		ShoppingCart bean = sService.findByID(id);
		return bean;
	}

	/*
	 * ????????????bean????????????????????????????????????
	 */
	@GetMapping("/api/findshoppingcartbyuserid/{userid}")
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
	 * ???????????????????????????
	 */
	@GetMapping("/api/removeshoppingcartbyid/{id}")
	@ResponseBody
	public boolean removeProductByID(@PathVariable("id") int id) throws ShoppingCartNotFoundException {

		sService.deleteByID(id);
		return true;
	}

	/*
	 * ????????????????????????????????????
	 */
	@GetMapping("/api/movetofavoritecoursebyid/{id}")
	@ResponseBody
	public boolean removeProductAndAddFCBySID(@PathVariable("id") int id)
			throws UserOrCourseNotFoundException, ShoppingCartNotFoundException {
		ShoppingCart shopping = sService.findByID(id);
		if (shopping == null)
			throw new ShoppingCartNotFoundException();

		int courseID = shopping.getCourseBasic().getCourseID();
		int userId = shopping.getUserAccountMt().getUserId();

		UserAccountMt mt = accService.findByID(userId);
		CourseBasic cb = cService.findByID(courseID);

		FavoriteCourse fc = new FavoriteCourse(UtilityTool.getSysTime(), mt, cb);
		FavoriteCourse insertFavoriteCourse = fService.insertFavoriteCourse(fc);

		sService.deleteByID(id);

		return true;
	}

	@GetMapping("/api/findshoppingcartdetailbyuserid/{userid}")
	@ResponseBody
	public List<SearchingCourseDTO> findFavoriteCourseDetailByUserID(@PathVariable("userid") int userid)
			throws CourseNotFoundException {

		List<SearchingCourseDTO> searchList = new ArrayList<SearchingCourseDTO>();

		List<ShoppingCart> shoplist = sService.findByUserID(userid);
		for (ShoppingCart shoppingCart : shoplist) {

			int courseID = shoppingCart.getCourseBasic().getCourseID();
			SearchingCourseDTO searchutils = searchutils(courseID);
			searchutils.setUserid(userid);
			searchList.add(searchutils);
		}

		return searchList;
	}

	@PostMapping("/api/insertshoppingcart")
	@ResponseBody
	public ShoppingCart insertShoppingCart(@RequestBody SimpleCourseDTO simplecourseDTO)
			throws UserOrCourseNotFoundException, AlreadyPurchasedException {
		
		boolean status = pService.findPurchasedStatus(simplecourseDTO.getUserID(), simplecourseDTO.getCourseID());
		if(status==true) throw new AlreadyPurchasedException();
		
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

	@GetMapping("/api/removeshoppingcart/{userid}/{courseid}")
	@ResponseBody
	public boolean removeShoppingCart(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid) {
		boolean status = sService.removeShoppingCart(userid, courseid);
		return status;
	}

	@GetMapping("/api/findshoppingcartstatusbyuserid/{userid}")
	@ResponseBody
	public List<Integer> findShoppingCartStatusByUserID(@PathVariable("userid") int userid) {
		List<Integer> shopstatus = new ArrayList<Integer>();
		
		List<ShoppingCart> list = sService.findByUserID(userid);
		for (ShoppingCart shoppingCart : list) {
			int courseID = shoppingCart.getCourseBasic().getCourseID();
			shopstatus.add(courseID);
		}
		
		return shopstatus;
	}
	
	@GetMapping("/api/findshoppingcartstatus/{userid}/{courseid}")
	@ResponseBody
	public boolean findShoppingCartStatus(@PathVariable("userid") int userid,@PathVariable("courseid") int courseid) {
		
		boolean status = sService.findShoppingCartStatus(userid, courseid);
		
		return status;
	}
	
	@GetMapping("/api/movetofavoritecourse/{userid}/{courseid}")
	@ResponseBody
	public boolean moveToFavoriteCourse(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid) throws UserOrCourseNotFoundException {
		
		boolean status = fService.findFavoriteCourseStatus(userid, courseid);
		
		if(status==true) return false;
		
		UserAccountMt mt = accService.findByID(userid);
		CourseBasic cb = cService.findByID(courseid);
		FavoriteCourse fc = new FavoriteCourse(UtilityTool.getSysTime(), mt, cb);
		FavoriteCourse insertFavoriteCourse = fService.insertFavoriteCourse(fc);
		
		sService.removeShoppingCart(userid, courseid);
		return true;
	}
	
	private SearchingCourseDTO searchutils(int courseID) throws CourseNotFoundException {

		CourseBasic course = cService.findByID(courseID);
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
		searchDTO.setCoursename(course.getCourseName());
		searchDTO.setCatgoryname(course.getCategory().getName());
		searchDTO.setStudentnum(studentnum);
		searchDTO.setDiscountprice(discountprice);
		searchDTO.setRank(rank);
		searchDTO.setRanknum(ranknum);

		return searchDTO;
	}
	
}
