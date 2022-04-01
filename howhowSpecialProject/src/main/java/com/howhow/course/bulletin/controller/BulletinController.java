package com.howhow.course.bulletin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.howhow.account.service.UserAccountDtService;
import com.howhow.course.bulletin.dto.BulletinDTO;
import com.howhow.course.bulletin.dto.BulletinReplyDTO;
import com.howhow.course.bulletin.service.BulletinReplyService;
import com.howhow.course.bulletin.service.BulletinService;
import com.howhow.course.common.LearningLecturesService;
import com.howhow.course.common.LearningSectionService;
import com.howhow.entity.Bulletin;
import com.howhow.entity.BulletinReply;
import com.howhow.entity.Lectures;
import com.howhow.entity.Section;
import com.howhow.entity.UserAccountDt;
import com.howhow.websecurity.AccountUserDetails;

@Controller
@SessionAttributes(names = { "courseid", "userid" })
public class BulletinController {

	@Autowired
	private BulletinService bService;

	@Autowired
	private LearningSectionService lsService;
	
	@Autowired
	private LearningLecturesService llService;
	
	@Autowired
	private BulletinReplyService brService;

	@Autowired
	private UserAccountDtService uadService;

	@Autowired
	private LearningLecturesService lService;

	@GetMapping("/course/{courseid}.controller")
	public String processBulletin(HttpServletRequest request, Model model, @PathVariable("courseid") Integer courseid) {
		model.addAttribute("userid", request.getAttribute("userid"));
		model.addAttribute("courseid", courseid);
//		return "course-demo-wj";
		return "course-demo-wj-toVue";
	}

	@GetMapping("/findCreatorIdByCourseId.controller")
	@ResponseBody
	public Integer findCreatorIdByCourseId(@RequestParam("courseid") Integer courseid) {
		return bService.findCreatorIdByCourseId(courseid);
	}

	@GetMapping("/findLoggedUser.controller")
	@ResponseBody
	public Map<String, Object> findLoggedUser(@AuthenticationPrincipal AccountUserDetails loggedAccount) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Integer loggedUserId = loggedAccount.getLoggedAccount().getUserId();
		UserAccountDt user = uadService.findById(loggedUserId);
		String loggedUserName = user.getFamilyName() + " " + user.getGivenName();
		map1.put("loggedUserId", loggedUserId);
		map1.put("loggedUserName", loggedUserName);
		return map1;
	}

	@PostMapping("/insertBulletin2.controller")
	@ResponseBody
	public Bulletin insertBulletin2(@RequestBody Map<String, Object> map,
			@AuthenticationPrincipal AccountUserDetails loggedAccount) {
		Bulletin bulletin = new Bulletin();
		String title = (String) map.get("title");
		String content = (String) map.get("content");
		Integer launcherid = loggedAccount.getLoggedAccount().getUserId();
		UserAccountDt user = uadService.findById(launcherid);
		Integer lectureid = (Integer) map.get("lectureid");
		Lectures lecture = lService.findByLectureID(lectureid);
		bulletin.setTitle(title);
		bulletin.setContent(content);
		bulletin.setLauncherid(user);
		bulletin.setLectureid(lecture);
		bulletin.setCreationTime(new Date());
		return bService.insert(bulletin);
	}

	@GetMapping("/findAllByCourseId.controller")
	@ResponseBody
	public Bulletin findAllByCourseId(HttpServletRequest request) {
		return (Bulletin) bService.findAllByCourseId((Integer) request.getAttribute("courseid"));
	}

	@GetMapping("/initBulletin.controller")
	@ResponseBody
	public List<Object> initBulletin(@RequestParam("courseid") Integer courseid) {
		List<Object> list = new ArrayList<Object>();
		List<Bulletin> blist = bService.findAllByCourseId(courseid);
		for (Bulletin btn : blist) {
			Integer bulletinid = btn.getBulletinid();
			String title = btn.getTitle();
			String content = btn.getContent();
			Date date = btn.getCreationTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			String creationTime = formatter.format(date);
			Integer lectureid = btn.getLectureid().getLecturesID();
			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
			Lectures lecture = llService.findByLectureID(lectureid);
			String lecturename = lecture.getLecturesName();
		    String sectionname = lecture.getSection().getSectionName();
			List<BulletinReplyDTO> replies = new ArrayList<BulletinReplyDTO>();
			int j = 0;
			List<BulletinReply> brlist = brService.findAllByBulletinId(bulletinid);
			Integer replyCount = 0;
			if (brlist != null) {
				for (BulletinReply bReply : brlist) {
					j += 1;
					Integer bulletinreplyid = bReply.getBulletinreplyid();
					String brcontent = bReply.getReplycontent();
					Date brdate = bReply.getCreationtime();
					String brcreationtime = formatter.format(brdate);
					Integer respondentId = bReply.getRespondentid().getUserId();
					String respondentname = bReply.getRespondentid().getFamilyName() + " "
							+ bReply.getRespondentid().getGivenName();
					BulletinReplyDTO brDto = new BulletinReplyDTO(bulletinreplyid, brcontent, brcreationtime,
							respondentname, respondentId);
					replies.add(brDto);
				}
				replyCount = brlist.size();
			} else {
				brlist = null;
			} 
			
			BulletinDTO bDto = new BulletinDTO(bulletinid, title, content, creationTime, launchername, lectureid,
					replies, replyCount, sectionname, lecturename);
			list.add(bDto);
		}
		return list;
	}
//	@GetMapping("/initBulletin.controller")
//	@ResponseBody
//	public Map<Integer, Object> initBulletin(@RequestParam("courseid") Integer courseid) {
//		Map<Integer, Object> map1 = new HashMap<Integer, Object>();
//		int i = 0;
//		List<Bulletin> blist = bService.findAllByCourseId(courseid);
//		for (Bulletin btn : blist) {
//			i += 1; 
//			Integer bulletinid = btn.getBulletinid();
//			String title = btn.getTitle();
//			String content = btn.getContent();
//			Date date = btn.getCreationTime();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
//			String creationTime = formatter.format(date);
//			Integer lectureid = btn.getLectureid().getLecturesID();
//			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
//			Lectures lecture = llService.findByLectureID(lectureid);
//			String lecturename = lecture.getLecturesName();
//		    String sectionname = lecture.getSection().getSectionName();
//			HashMap<Integer, BulletinReplyDTO> replies = new HashMap<Integer, BulletinReplyDTO>();
//			int j = 0;
//			List<BulletinReply> brlist = brService.findAllByBulletinId(bulletinid);
//			Integer replyCount = 0;
//			if (brlist != null) {
//				for (BulletinReply bReply : brlist) {
//					j += 1;
//					Integer bulletinreplyid = bReply.getBulletinreplyid();
//					String brcontent = bReply.getReplycontent();
//					Date brdate = bReply.getCreationtime();
//					String brcreationtime = formatter.format(brdate);
//					Integer respondentId = bReply.getRespondentid().getUserId();
//					String respondentname = bReply.getRespondentid().getFamilyName() + " "
//							+ bReply.getRespondentid().getGivenName();
//					BulletinReplyDTO brDto = new BulletinReplyDTO(bulletinreplyid, brcontent, brcreationtime,
//							respondentname, respondentId);
//					replies.put(j, brDto);
//				}
//				replyCount = brlist.size();
//			} else {
//				brlist = null;
//			} 
//			
//			BulletinDTO bDto = new BulletinDTO(bulletinid, title, content, creationTime, launchername, lectureid,
//					replies, replyCount, sectionname, lecturename);
//			map1.put(i, bDto);
//		}
//		return map1;
//	}
//	@GetMapping("/initBulletinByLectureId.controller")
//	@ResponseBody
//	public Map<Integer, Object> initBulletinByLectureId(@RequestParam("lectureid") Integer lectureid) {
//		Map<Integer, Object> map1 = new HashMap<Integer, Object>();
//		int i = 0;
//		List<Bulletin> blist = bService.findAllByLectureId(lectureid);
//		for (Bulletin btn : blist) {
//			i += 1; 
//			Integer bulletinid = btn.getBulletinid();
//			String title = btn.getTitle();
//			String content = btn.getContent();
//			Date date = btn.getCreationTime();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
//			String creationTime = formatter.format(date);
//			Integer lectureid1 = btn.getLectureid().getLecturesID();
//			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
//			Lectures lecture = llService.findByLectureID(lectureid1);
//			String lecturename = lecture.getLecturesName();
//		    String sectionname = lecture.getSection().getSectionName();
//			HashMap<Integer, BulletinReplyDTO> replies = new HashMap<Integer, BulletinReplyDTO>();
//			int j = 0;
//			List<BulletinReply> brlist = brService.findAllByBulletinId(bulletinid);
//			Integer replyCount = 0;
//			if (brlist != null) {
//				for (BulletinReply bReply : brlist) {
//					j += 1;
//					Integer bulletinreplyid = bReply.getBulletinreplyid();
//					String brcontent = bReply.getReplycontent();
//					Date brdate = bReply.getCreationtime();
//					String brcreationtime = formatter.format(brdate);
//					Integer respondentId = bReply.getRespondentid().getUserId();
//					String respondentname = bReply.getRespondentid().getFamilyName() + " "
//							+ bReply.getRespondentid().getGivenName();
//					BulletinReplyDTO brDto = new BulletinReplyDTO(bulletinreplyid, brcontent, brcreationtime,
//							respondentname, respondentId);
//					replies.put(j, brDto);
//				}
//				replyCount = brlist.size();
//			} else {
//				brlist = null;
//			} 
//			
//			BulletinDTO bDto = new BulletinDTO(bulletinid, title, content, creationTime, launchername, lectureid1,
//					replies, replyCount, sectionname, lecturename);
//			map1.put(i, bDto);
//		}
//		return map1;
//	}
	
	@GetMapping("/findAllBulletin.controller")
	@ResponseBody
	public List<Bulletin> findAllBulletin() {
		return bService.findAll();
	}

	@PostMapping("/deleteBulletinById.controller")
	@ResponseBody
	public void deleteBulletinById(@RequestParam Integer id) {
		bService.deleteById(id);
	}

}
