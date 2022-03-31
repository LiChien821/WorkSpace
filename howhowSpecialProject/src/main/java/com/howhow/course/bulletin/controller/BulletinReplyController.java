package com.howhow.course.bulletin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.UserAccountDtService;
import com.howhow.course.bulletin.service.BulletinReplyService;
import com.howhow.course.bulletin.service.BulletinService;
import com.howhow.entity.Bulletin;
import com.howhow.entity.BulletinReply;
import com.howhow.entity.Lectures;
import com.howhow.entity.UserAccountDt;
import com.howhow.websecurity.AccountUserDetails;

@Controller
public class BulletinReplyController {
	
	@Autowired
	private BulletinReplyService brService;
	
	@Autowired
	private BulletinService bService;
	
	@Autowired
	private UserAccountDtService uadService;
	
	@PostMapping("/insertBulletinReply.controller")
	@ResponseBody
	public BulletinReply insertBulletinReply(@RequestBody Map<String, Object> map, @AuthenticationPrincipal AccountUserDetails loggedAccount) {
		BulletinReply bReply = new BulletinReply();
		String replycontent = (String) map.get("replycontent");
		Integer bulletinid = (Integer) map.get("bulletinid");
		Bulletin blt = bService.findById(bulletinid);
			
		try {
			Integer respondentid = loggedAccount.getLoggedAccount().getUserId();
			UserAccountDt user = uadService.findById(respondentid);
			bReply.setRespondentid(user);
		} catch (NullPointerException e) {
			System.out.println("loggedAccount is null");
		}
		
		bReply.setReplycontent(replycontent);
		bReply.setBulletinid(blt);
		bReply.setCreationtime(new Date());
		return brService.insert(bReply);
	}

	@PostMapping("/queryBulletinReplyById.controller")
	@ResponseBody
	public BulletinReply queryBulletinReplyById(@RequestParam Integer id) {
		return brService.findById(id);
	}

	@PostMapping("/findBulletinReplyUserNameById.controller")
	@ResponseBody
	public String findBulletinReplyUserNameById(@RequestParam("id") Integer id) {
		BulletinReply btn = brService.findById(id);
		String givenname = btn.getRespondentid().getGivenName();
		return givenname;
	}

	@PostMapping("/findAllBulletinReply.controller")
	@ResponseBody
	public List<BulletinReply> findAllBulletinReply() {
		return brService.findAll();
	}

	@PostMapping("/deleteBulletinReplyById.controller")
	@ResponseBody
	public void deleteBulletinReplyById(@RequestParam Integer id) {
		brService.deleteById(id);
	}
}
