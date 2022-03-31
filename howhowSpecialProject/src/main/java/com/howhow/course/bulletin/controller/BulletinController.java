package com.howhow.course.bulletin.controller;

import java.text.SimpleDateFormat;
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
import com.howhow.entity.Bulletin;
import com.howhow.entity.BulletinReply;
import com.howhow.entity.Lectures;
import com.howhow.entity.UserAccountDt;
import com.howhow.websecurity.AccountUserDetails;

@Controller
@SessionAttributes(names = {"courseid", "userid"})
public class BulletinController {
	
	@Autowired
	private BulletinService bService;

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
	
	@GetMapping("/findLoggedUserId.controller")
	@ResponseBody
	public Integer findLoggedUserId(@AuthenticationPrincipal AccountUserDetails loggedAccount) {
		Integer loggedUserId = loggedAccount.getLoggedAccount().getUserId();
		return loggedUserId;
	}
	
	@PostMapping("/insertBulletin2.controller")
	@ResponseBody
	public Bulletin insertBulletin2(@RequestBody Map<String, Object> map, @AuthenticationPrincipal AccountUserDetails loggedAccount) {
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
		return (Bulletin) bService.findAllByCourseId((Integer)request.getAttribute("courseid"));
	}

	@GetMapping("/initBulletin.controller")
	@ResponseBody
	public Map<Integer, BulletinDTO> initBulletin(@AuthenticationPrincipal AccountUserDetails loggedAccount, @RequestParam("courseid") Integer courseid) {
		
		Map<Integer, BulletinDTO> map1 = new HashMap<Integer, BulletinDTO>();
		int i = 0;
		
		List<Bulletin> blist = bService.findAllByCourseId(courseid);
		for (Bulletin btn : blist) {
			i += 1;
			Integer bulletinid = btn.getBulletinid();
			String title = btn.getTitle();
			String content = btn.getContent();
			Date date = btn.getCreationTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			String creationTime = formatter.format(date);
			Integer lectureid = btn.getLectureid().getLecturesID();
//			Integer lectureid = loggedAccount.getLoggedAccount().getUserId();
			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();

			HashMap<Integer, BulletinReplyDTO> replies = new HashMap<Integer, BulletinReplyDTO>();
			int j = 0;
			List<BulletinReply> brlist = brService.findAllByBulletinId(bulletinid);
			if (brlist != null) {
				for (BulletinReply bReply : brlist) {
					j += 1;
					Integer bulletinreplyid = bReply.getBulletinreplyid();
					String brcontent = bReply.getReplycontent();
					Date brdate = bReply.getCreationtime();
					String brcreationtime = formatter.format(brdate);
					String respondentname = bReply.getRespondentid().getFamilyName() + " "
							+ bReply.getRespondentid().getGivenName();
					BulletinReplyDTO brDto = new BulletinReplyDTO(bulletinreplyid, brcontent, brcreationtime,
							respondentname);
					replies.put(j, brDto);
				}
			} else {
				brlist = null;
			}
			BulletinDTO bDto = new BulletinDTO(bulletinid, title, content, creationTime, launchername, lectureid,
					replies);
			map1.put(i, bDto);
		}
		return map1;
	}

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
