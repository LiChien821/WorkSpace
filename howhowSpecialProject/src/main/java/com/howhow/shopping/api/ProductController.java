package com.howhow.shopping.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountDetailService;
import com.howhow.cms.service.CourseStatusTypeService;
import com.howhow.entity.Category;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.Section;
import com.howhow.entity.UserAccountDt;
import com.howhow.shopping.dto.CourseBasicDTO;
import com.howhow.shopping.dto.CourseRankDTO;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.exception.UserNotFoundException;
import com.howhow.shopping.service.CategoryService;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.shopping.service.FavoriteCourseService;
import com.howhow.student.service.PurchasedCourseService;
import com.howhow.util.UtilityTool;

@Controller
public class ProductController {
	
	public final int PAGESIZE=2;

	@Autowired
	CourseBasicService cService;

	@Autowired
	CourseRankService crService;

	@Autowired
	AccountDetailService acdService;

	@Autowired
	CategoryService catService;

	@Autowired
	FavoriteCourseService fService;

	@Autowired
	CourseStatusTypeService cstService;

	@Autowired
	PurchasedCourseService pService;
	
	/*
	 * Find BY ID
	 */
	@GetMapping("/api/findcoursebyid/{id}")
	@ResponseBody
	public CourseBasicDTO findCourseByID(@PathVariable("id") int courseid) throws CourseNotFoundException {
		CourseBasic course = cService.findByID(courseid);
		if (course == null)
			return null;
		CourseBasicDTO dto = dtoutils(course);

		Integer id = course.getCategory().getId();
		int creatorid = course.getCreator().getUserId();
		String courseCover = course.getCourseCover();
		String description = course.getDescription();
		UserAccountDt userbean = acdService.findByID(creatorid);
		String creatorName = userbean.getGivenName();
		String category = catService.findByID(id).getName();
		/*
		 * if(loggedAccount!=null) { int userId =
		 * loggedAccount.getLoggedAccount().getUserId(); boolean fstatus =
		 * fService.findFavoriteCourseStatus(userId, courseid);
		 * dto.setFavoritecoursestatus(fstatus); }
		 */

		dto.setCategoryid(id);
		dto.setDescription(description);
		dto.setCover(courseCover);
		dto.setCreatorname(creatorName);
		dto.setCategory(category);
		
		CourseRankController cc = new CourseRankController();
		
		List<CourseRank> rank = crService.findByCourseID(courseid);
		
		List<Section> sectionList = course.getSectionList();
		dto.setSectionList(sectionList);

		return dto;
	}

	/*
	 * 查詢所有課程
	 */
	@GetMapping("/api/findallcourses/{pageNo}")
	@ResponseBody
	public Page<CourseBasicDTO> findAllCourse(@PathVariable("pageNo") int pageNo)
			throws CourseNotFoundException {
		Pageable pageable = PageRequest.of(pageNo - 1, PAGESIZE);

		List<CourseBasicDTO> dtoList = new ArrayList<CourseBasicDTO>();
		List<CourseBasic> list = cService.findAll();

		for (CourseBasic courseBasic : list) {
			CourseBasicDTO cb = dtoutils(courseBasic);
			dtoList.add(cb);

		}

		Page<CourseBasicDTO> page = toPage(dtoList, pageable);

		return page;
	}

	public Page<CourseBasicDTO> toPage(List<CourseBasicDTO> list, Pageable pageable) {
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), list.size());
		if (start > list.size())
			return new PageImpl<>(new ArrayList<>(), pageable, list.size());
		return new PageImpl<>(list.subList(start, end), pageable, list.size());
	}

	/*
	 * 依照課程類別查詢課程
	 */
	@GetMapping("/api/findcoursebycategoryid/{id}/{pageNo}")
	@ResponseBody
	public Page<CourseBasicDTO> findCourseListByCategory(@PathVariable("id") int catID, @PathVariable("pageNo") int pageNo) throws CourseNotFoundException {
		
		Pageable pageable = PageRequest.of(pageNo - 1, PAGESIZE);
		
		List<CourseBasicDTO> dtoList = new ArrayList<CourseBasicDTO>();
		List<CourseBasic> list = cService.findByCategoryID(catID);

		for (CourseBasic courseBasic : list) {
			CourseBasicDTO cb = dtoutils(courseBasic);
			dtoList.add(cb);
		}
		
		Page<CourseBasicDTO> page = toPage(dtoList, pageable);
		return page;
	}

	/*
	 * 依照課程名稱關鍵字搜尋
	 */
	@GetMapping("/api/findcoursebynamelike/{name}/{pageNo}")
	@ResponseBody
	public Page<CourseBasicDTO> findCourseListByNameLike(@PathVariable("name") String name, @PathVariable("pageNo") int pageNo)
			throws CourseNotFoundException {
		Pageable pageable = PageRequest.of(pageNo - 1, PAGESIZE);

		List<CourseBasicDTO> dtoList = new ArrayList<CourseBasicDTO>();
		List<CourseBasic> list = cService.findByCourseNameLike(name);

		for (CourseBasic courseBasic : list) {
			CourseBasicDTO cb = dtoutils(courseBasic);
			dtoList.add(cb);
		}

		Page<CourseBasicDTO> page = toPage(dtoList, pageable);
		return page;
	}

	@PostMapping("/api/insertcourse")
	@ResponseBody
	public CourseBasic insertCourseBasic(@RequestBody CourseBasicDTO coursebasicDTO) throws UserNotFoundException {

		int creatorid = coursebasicDTO.getCreatorid();
		int categoryid = coursebasicDTO.getCategoryid();
		UserAccountDt dt = acdService.findByID(creatorid);
		Category cat = catService.findByID(categoryid);

		CourseBasic cb = new CourseBasic(coursebasicDTO.getCoursename(), coursebasicDTO.getPrice(),
				coursebasicDTO.getDiscount(), cat, coursebasicDTO.getCover(), coursebasicDTO.getDescription(),
				UtilityTool.getSysTime(), dt);
		cb.setStatusType(cstService.findById(1));
		CourseBasic insertCourseBasic = cService.insertCourseBasic(cb);

		return insertCourseBasic;
	}

	@PostMapping("/api/updatecourse")
	@ResponseBody
	public CourseBasic updateCourseBasic(@RequestBody CourseBasicDTO coursebasicDTO) throws CourseNotFoundException {

		int courseID = coursebasicDTO.getCourseid();
		CourseBasic course = cService.findByID(courseID);
		if (course == null) {
			System.out.println("COURSEID不存在，無法修改");
			return null;
		}
		if (coursebasicDTO.getCoursename() != null)
			course.setCourseName(coursebasicDTO.getCoursename());
		if (coursebasicDTO.getDescription() != null)
			course.setDescription(coursebasicDTO.getDescription());
		if (coursebasicDTO.getCover() != null)
			course.setCourseCover(coursebasicDTO.getCover());
		if (coursebasicDTO.getPrice() != 0)
			course.setPrice(coursebasicDTO.getPrice());
		if (coursebasicDTO.getDiscount() != 0)
			course.setDiscount(coursebasicDTO.getDiscount());

		CourseBasic insertCourseBasic = cService.updateCourseBasic(course);

		return insertCourseBasic;
	}

	@GetMapping("/api/deletecourse/{id}")
	@ResponseBody
	public boolean deleteCourseBasic(@PathVariable("id") int id) throws CourseNotFoundException {

		cService.deleteById(id);
		return true;

	}

	public double rankUtilsByCourseID(int id) {
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

	private CourseBasicDTO dtoutils(CourseBasic courseBasic) throws CourseNotFoundException {
		int courseID = courseBasic.getCourseID();
		String courseName = courseBasic.getCourseName();
		long price = courseBasic.getPrice();
		double discount = courseBasic.getDiscount();
		int discountprice = (int) (price * discount);
		int creatorid = courseBasic.getCreator().getUserId();
		String givenName = acdService.findByID(creatorid).getGivenName();

		int catid = courseBasic.getCategory().getId();
		int statusID = courseBasic.getStatusType().getStatusID();
		String cover = courseBasic.getCourseCover();
		String description = courseBasic.getDescription();
		Integer studentnum = pService.findStudentCount(courseID);

		int ranknum = 0;
		double totalrank = 0;

		String url = "/howhow/product?id=" + Integer.toString(courseID);

		List<CourseRank> list = crService.findByCourseID(courseID);
		for (CourseRank courseRank : list) {
			int ranka = courseRank.getCourseRank();
			totalrank += ranka;
		}

		ranknum = list.size();
		double rank = totalrank / ranknum;

		CourseBasicDTO cb = new CourseBasicDTO(courseID, courseName, price, discountprice, rank, givenName, catid,
				statusID, cover, description, creatorid, studentnum, ranknum, url);
		return cb;
	}

}
