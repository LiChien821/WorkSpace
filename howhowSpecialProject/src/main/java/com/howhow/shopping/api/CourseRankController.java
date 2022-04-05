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
import com.howhow.account.service.AccountService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.dto.CourseBasicDTO;
import com.howhow.shopping.dto.CourseRankDTO;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.exception.CourseRankNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.student.service.PurchasedCourseService;
import com.howhow.util.UtilityTool;

@Controller
public class CourseRankController {
	
	public final int PAGESIZE=2;
	
	@Autowired
	CourseRankService cService;
	
	@Autowired
	AccountDetailService acdService;
	
	@Autowired
	CourseBasicService cbService;
	
	@Autowired
	AccountService accService;
	
	@Autowired
	PurchasedCourseService pService;
	/*
	 * ID尋找
	 * */
	@GetMapping("/api/querycourserank/{courserankid}")
	@ResponseBody
	public CourseRankDTO findCourseRankByID(@PathVariable("courserankid") int courserankid) throws CourseNotFoundException {
		
		CourseRank courseRank = cService.findByID(courserankid);
		CourseRankDTO dto = dtoutils(courseRank);
		
		return dto;
	}
	
	/*
	 * 新增評價
	 * */
	@PostMapping("/api/insertcourserank")
	@ResponseBody
	public CourseRank insertCourseRank(@RequestBody CourseRankDTO courseRankDTO) throws CourseRankNotFoundException, UserOrCourseNotFoundException {
		
		if(!pService.findPurchasedStatus(courseRankDTO.getUserid(), courseRankDTO.getCourseid())) return null;
		
		CourseBasic coursebasic = cbService.findByID(courseRankDTO.getCourseid());
		UserAccountMt user = accService.findByID(courseRankDTO.getUserid());
		CourseRank courseRank = new CourseRank(courseRankDTO.getRank(), courseRankDTO.getMessage(), UtilityTool.getSysTime(), UtilityTool.getSysTime(), user, coursebasic);
		
		CourseRank insertCourseRank = cService.insertCourseRank(courseRank);
		
		return insertCourseRank;
	}
	
	/*
	 * 刪除課程評價
	 * */
	@GetMapping("/api/deletecourserank/{id}")
	@ResponseBody
	public boolean deleteCourseRank(@PathVariable("id") int id, Model m) throws CourseRankNotFoundException {
		
		cService.deleteCourseRankByID(id);
		return true;
	}
	
	/*
	 * 更新課程評價
	 * */
	@PostMapping("/api/updatecourserank")
	@ResponseBody
	public CourseRank updateCourseRank(@RequestBody CourseRankDTO courseRankDTO) throws CourseRankNotFoundException {
		
		int id = courseRankDTO.getCourserankid();
		CourseRank courseRank = cService.findByID(id);
		String msg=courseRankDTO.getMessage();
		if(msg!=null) courseRank.setRankMessage(msg);
		Integer rank=courseRankDTO.getRank();
		if(rank!=null) courseRank.setCourseRank(rank);
		courseRank.setRankDate(UtilityTool.getSysTime());
		courseRank.setSystemTime(UtilityTool.getSysTime());
		CourseRank updateCourseRank = cService.updateCourseRank(courseRank);
		
		return updateCourseRank;
	}
	
	@GetMapping("/api/querycourserankbycourseid/{id}/{pageNo}")
	@ResponseBody
	public Page<CourseRankDTO> findCourseRankByCourseID(@PathVariable("id") int id, @PathVariable("pageNo") int pageNo) throws CourseNotFoundException {
		
		Pageable pageable = PageRequest.of(pageNo - 1, PAGESIZE);
		
		List<CourseRankDTO> dtoList = new ArrayList<CourseRankDTO>();
		
		List<CourseRank> list = cService.findByCourseID(id);
		for (CourseRank courseRank : list) {
			CourseRankDTO dto = dtoutils(courseRank);
		
			dtoList.add(dto);
		}
		
		Page<CourseRankDTO> page = toPage(dtoList, pageable);
		return page;
	}
	
	public Page<CourseRankDTO> toPage(List<CourseRankDTO> list, Pageable pageable) {
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), list.size());
		if (start > list.size())
			return new PageImpl<>(new ArrayList<>(), pageable, list.size());
		return new PageImpl<>(list.subList(start, end), pageable, list.size());
	}
	
	
	
	private CourseRankDTO dtoutils(CourseRank courseRank) {
		int courseID = courseRank.getCourseBasic().getCourseID();	
		int userid = courseRank.getUserAccountMt().getUserId();		
		UserAccountDt userdetail = acdService.findByID(userid);
		String givenName = userdetail.getGivenName();				
		int rank = courseRank.getCourseRank();						
		String rankDate = courseRank.getRankDate();					
		String rankMessage = courseRank.getRankMessage();			
		int rankid = courseRank.getCourseRankID();					
		CourseBasic coursebasic = cbService.findByID(courseID);
		String courseName = coursebasic.getCourseName();
		
		CourseRankDTO dto = new CourseRankDTO(rankid, courseID, userid, rank, rankDate, rankMessage, courseName, givenName);
		return dto;
	}
}