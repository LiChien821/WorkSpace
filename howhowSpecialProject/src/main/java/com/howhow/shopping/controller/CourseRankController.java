package com.howhow.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.howhow.shopping.dto.CourseRankDTO;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.student.service.PurchasedCourseService;
import com.howhow.util.UtilityTool;

@Controller
public class CourseRankController {
	
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
	@GetMapping("/querycourserank/{courserankid}")
	@ResponseBody
	public CourseRankDTO findCourseRankByID(@PathVariable("courserankid") int courserankid) {
		
		CourseRank courseRank = cService.findByID(courserankid);
		if(courseRank==null) return null;
		
		CourseRankDTO dto = dtoutils(courseRank);
		
		return dto;
	}
	
	/*
	 * 新增評價
	 * */
	@PostMapping("/insertcourserank")
	@ResponseBody
	public CourseRank insertCourseRank(@RequestBody CourseRankDTO courseRankDTO) {
		
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
	@GetMapping("/deletecourserank/{id}")
	@ResponseBody
	public boolean deleteCourseRank(@PathVariable("id") int id, Model m) {
		
		boolean status = cService.deleteCourseRankByID(id);
		if(status==false) {
//			m.addAttribute("status","刪除失敗");
			
			return false;
		}
		
		System.out.println("刪除成功");
		
		return true;
	}
	
	/*
	 * 更新課程評價
	 * */
	@PostMapping("/updatecourserank")
	@ResponseBody
	public CourseRank updateCourseRank(@RequestBody CourseRankDTO courseRankDTO) {
		
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
	
	@GetMapping("/querycourserankbycourseid/{id}")
	@ResponseBody
	public List<CourseRankDTO> findCourseRankByCourseID(@PathVariable("id") int id) {
		
		List<CourseRankDTO> dtoList = new ArrayList<CourseRankDTO>();
		
		List<CourseRank> list = cService.findByCourseID(id);
		for (CourseRank courseRank : list) {
			CourseRankDTO dto = dtoutils(courseRank);
		
			dtoList.add(dto);
		}

		return dtoList;
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