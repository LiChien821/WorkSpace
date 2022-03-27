package com.howhow.course.bulletin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

@Controller
public class BulletinController {
	
	@Autowired
	private BulletinService bService;

	@Autowired
	private BulletinReplyService brService;

	@Autowired
	private UserAccountDtService uadService;

	@Autowired
	private LearningLecturesService lService;

	@PostMapping("/insertBulletin.controller")
	@ResponseBody
	public Bulletin insertBulletin(@RequestBody Bulletin bulletin) {
		bulletin.setCreationTime(new Date());
		UserAccountDt user1 = uadService.findById(1);
		Lectures lec1 = lService.findByLectureID(1);
		bulletin.setLauncherid(user1);
		bulletin.setLectureid(lec1);
		return bService.insert(bulletin);
	}

	@PostMapping("/insertBulletin2.controller")
	@ResponseBody
	public Bulletin insertBulletin2(@RequestBody Map<String, Object> map) {
//	public void insertBulletin2(@RequestBody Map<String, Object> map) {
		Bulletin bulletin = new Bulletin();
		String title = (String) map.get("title");
		String content = (String) map.get("content");
		Integer launcherid = (Integer) map.get("launcherid");
		Integer lectureid = (Integer) map.get("lectureid");
		UserAccountDt user1 = uadService.findById(launcherid);
		Lectures lec1 = lService.findByLectureID(lectureid);
		bulletin.setBulletinid(10);
		bulletin.setTitle(title);
		bulletin.setContent(content);
		bulletin.setLauncherid(user1);
		bulletin.setLectureid(lec1);
		bulletin.setCreationTime(new Date());
		return bService.insert(bulletin);
	}

	@PostMapping("/queryBulletinById.controller")
	@ResponseBody
	public Bulletin queryBulletinById(@RequestParam Integer id) {
		return bService.findById(id);
	}

	@PostMapping("/findBulletinUserNameById.controller")
	@ResponseBody
	public String findBulletinUserNameById(@RequestParam("id") Integer id) {
		Bulletin btn = bService.findById(id);
		String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
		return launchername;
	}

//	@PostMapping("/init.controller")
//	@ResponseBody
//	public Map<Integer, Object> init() {
//		Map<Integer, Object> map1 = new HashMap<Integer, Object>();
//		int i = 0;
//		List<Bulletin> list = bService.findAll();
//		for (Bulletin btn : list) {
//			i += 1;
//			Map<String, Object> map2 = new HashMap<String, Object>();
//			map2.put("bulletinid", btn.getBulletinid());
//			map2.put("title", btn.getTitle());
//			map2.put("content", btn.getContent());
//			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
//			map2.put("launchername", launchername);
//			map1.put(i, map2);
//		}
//		return map1;
//	}

//	@PostMapping("/init2.controller")
//	@ResponseBody
//	public Map<Integer, Object> init2(@RequestParam("id") Integer id) {
//		Map<Integer, Object> map1 = new HashMap<Integer, Object>();
//		int i = 0;
//		List<Bulletin> list = bService.findAllByCourseId(id);
//		for (Bulletin btn : list) {
//			i += 1;
//			Map<String, Object> map2 = new HashMap<String, Object>();
//			map2.put("bulletinid", btn.getBulletinid());
//			map2.put("title", btn.getTitle());
//			map2.put("content", btn.getContent());
//
//			Date date = btn.getCreationTime();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
//			String creationtime = formatter.format(date);
//
//			map2.put("creationtime", creationtime);
//			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();
//			map2.put("launchername", launchername);
//			map1.put(i, map2);
//		}
//		return map1;
//	}

	@PostMapping("/init3.controller")
	@ResponseBody
	public Map<Integer, BulletinDTO> init3(@RequestParam("id") Integer id) {
		Map<Integer, BulletinDTO> map1 = new HashMap<Integer, BulletinDTO>();
		int i = 0;
		List<Bulletin> blist = bService.findAllByCourseId(id);
		for (Bulletin btn : blist) {
			i += 1;
			Integer bulletinid = btn.getBulletinid();
			String title = btn.getTitle();
			String content = btn.getContent();
			Date date = btn.getCreationTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			String creationtime = formatter.format(date);
			Integer lectureid = btn.getLectureid().getLecturesID();
			String launchername = btn.getLauncherid().getFamilyName() + " " + btn.getLauncherid().getGivenName();

			HashMap<Integer, BulletinReplyDTO> replymap = new HashMap<Integer, BulletinReplyDTO>();
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
					replymap.put(j, brDto);
				}
			} else {
				brlist = null;
			}

			BulletinDTO bDto = new BulletinDTO(bulletinid, title, content, creationtime, launchername, lectureid,
					replymap);
			map1.put(i, bDto);
		}
		return map1;
	}

//	@PostMapping("/findAllBulletin.controller")
//	@ResponseBody
//	public List<Bulletin> findAllBulletin() {
//		return bService.findAll();
//	}

	@PostMapping("/deleteBulletinById.controller")
	@ResponseBody
	public void deleteBulletinById(@RequestParam Integer id) {
		bService.deleteById(id);
	}
}
