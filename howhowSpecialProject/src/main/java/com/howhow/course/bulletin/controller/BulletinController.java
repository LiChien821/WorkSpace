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
import com.howhow.shopping.exception.UserNotFoundException;
import com.howhow.websecurity.AccountUserDetails;

@Controller
@SessionAttributes(names = { "courseid", "userid" })
public class BulletinController {

	@Autowired
	private BulletinService bService;
	
	@Autowired
	private LearningLecturesService llService;
	
	@Autowired
	private LearningSectionService lsService;
	
	@Autowired
	private BulletinReplyService brService;

	@Autowired
	private UserAccountDtService uadService;

	@Autowired
	private LearningLecturesService lService;

	@GetMapping("/api/course/{courseid}.controller")
	public String processBulletin(HttpServletRequest request, Model model, @PathVariable("courseid") Integer courseid) {
		model.addAttribute("userid", request.getAttribute("userid"));
		model.addAttribute("courseid", courseid);
//		return "course-demo-wj";
		return "course-demo-wj-toVue";
	}

	@GetMapping("/api/findCreatorIdByCourseId.controller")
	@ResponseBody
	public Integer findCreatorIdByCourseId(@RequestParam("courseid") Integer courseid) {
		return bService.findCreatorIdByCourseId(courseid);
	}

	@GetMapping("/api/findAllSectionByCourseId.controller")
	@ResponseBody
	public List<Object> findAllLectureByCourseId(@RequestParam("courseid") Integer courseid) {
		List<Object> list = new ArrayList<Object>();		
		List<Section> slist = lsService.findAllSectionsByCourseId(courseid);		
		for (Section sec : slist) {
			Map<String, Object> object = new HashMap<String, Object>();
			Integer sectionid = sec.getSectionID();
			object.put("sectionId", sectionid);
			object.put("sectionName", sec.getSectionName());
			List<Lectures> llist = llService.findAllLecturesBySectionId(sectionid);
			List<Object> olist = new ArrayList<Object>();
			for (Lectures lec : llist) {
				Map<String, Object> object2 = new HashMap<String, Object>();
				object2.put("lectureId", lec.getLecturesID());
				object2.put("lectureName", lec.getLecturesName());
				olist.add(object2);
			}
			object.put("lectures", olist);
			list.add(object);
		}
		return list;
	}
	
	@GetMapping("/api/findLoggedUser.controller")
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

	@PostMapping("/api/insertBulletin2.controller")
	@ResponseBody
	public BulletinDTO insertBulletin2(@RequestBody Map<String, Object> map,
			@AuthenticationPrincipal AccountUserDetails loggedAccount) throws UserNotFoundException{
		Bulletin bulletin = new Bulletin();
		if (loggedAccount == null) {
			throw new UserNotFoundException();
		}
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
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(bulletin);
		
		Bulletin output = bService.insert(bulletin);
		Date date = output.getCreationTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		String creationTime = formatter.format(date);
		String launchername = output.getLauncherid().getFamilyName() + " " + output.getLauncherid().getGivenName();
		Lectures lecture1 = llService.findByLectureID(lectureid);
		String lecturename = lecture1.getLecturesName();
	    String sectionname = lecture1.getSection().getSectionName();
		BulletinDTO bDto = new BulletinDTO(
				output.getBulletinid(), output.getTitle(), output.getContent(),
				creationTime, launchername, lectureid,
				null, 0, sectionname, lecturename);
		return bDto;
	}

	@GetMapping("/api/findAllByCourseId.controller")
	@ResponseBody
	public Bulletin findAllByCourseId(HttpServletRequest request) {
		return (Bulletin) bService.findAllByCourseId((Integer) request.getAttribute("courseid"));
	}

	@GetMapping("/api/initBulletin.controller")
	@ResponseBody
	public List<Object> initBulletin(@RequestParam("courseid") Integer courseid) {
		List<Object> list = new ArrayList<Object>();
		List<Bulletin> blist = bService.findAllByCourseId(courseid);
		if (blist == null) return list;
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
			List<BulletinReply> brlist = brService.findAllByBulletinId(bulletinid);
			Integer replyCount = 0;
			if (brlist != null) {
				for (BulletinReply bReply : brlist) {
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

	@GetMapping("/api/initBulletinByLectureId.controller")
	@ResponseBody
	public List<Object> initBulletinByLectureId(@RequestParam("lectureid") Integer lectureid) {
		List<Object> list = new ArrayList<Object>();
		List<Bulletin> blist = bService.findAllByLectureId(lectureid);
		if (blist == null) return list; 
		for (Bulletin btn : blist) {
			Integer bulletinid = btn.getBulletinid();
			String title = btn.getTitle();
			String content = btn.getContent();
			Date date = btn.getCreationTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			String creationTime = formatter.format(date);
			Integer lectureid1 = btn.getLectureid().getLecturesID();
			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
			Lectures lecture = llService.findByLectureID(lectureid1);
			String lecturename = lecture.getLecturesName();
		    String sectionname = lecture.getSection().getSectionName();
			List<BulletinReplyDTO> replies = new ArrayList<BulletinReplyDTO>();
			List<BulletinReply> brlist = brService.findAllByBulletinId(bulletinid);
			Integer replyCount = 0;
			if (brlist != null) {
				for (BulletinReply bReply : brlist) {
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
			BulletinDTO bDto = new BulletinDTO(bulletinid, title, content, creationTime, launchername, lectureid1,
					replies, replyCount, sectionname, lecturename);
			list.add(bDto);
		}
		return list;
	}
	
	@GetMapping("/api/findBulletinBySearch.controller")
	@ResponseBody
	public List<Object> findBulletinBySearch(@RequestParam("query") String query) {
		List<Object> list = new ArrayList<Object>();
		List<Bulletin> blist = bService.findAllBySearch(query);
		if (blist == null) return list;
		
		for (Bulletin btn : blist) {
			Integer bulletinid = btn.getBulletinid();
			String title = btn.getTitle();
			String content = btn.getContent();
			Date date = btn.getCreationTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			String creationTime = formatter.format(date);
			Integer lectureid1 = btn.getLectureid().getLecturesID();
			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
			Lectures lecture = llService.findByLectureID(lectureid1);
			String lecturename = lecture.getLecturesName();
		    String sectionname = lecture.getSection().getSectionName();
			List<BulletinReplyDTO> replies = new ArrayList<BulletinReplyDTO>();
			List<BulletinReply> brlist = brService.findAllByBulletinId(bulletinid);
			Integer replyCount = 0;
			if (brlist != null) {
				for (BulletinReply bReply : brlist) {
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
			BulletinDTO bDto = new BulletinDTO(bulletinid, title, content, creationTime, launchername, lectureid1,
					replies, replyCount, sectionname, lecturename);
			list.add(bDto);
		}
		return list;
	}
	
	@GetMapping("/api/findAllBulletin.controller")
	@ResponseBody
	public List<Bulletin> findAllBulletin() {
		return bService.findAll();
	}

	@PostMapping("/api/deleteBulletinById.controller")
	@ResponseBody
	public void deleteBulletinById(@RequestParam Integer id) {
		bService.deleteById(id);
	}

}
