package com.howhow.course.bulletin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.UserAccountDtService;
import com.howhow.course.bulletin.service.BulletinReplyService;
import com.howhow.course.bulletin.service.BulletinService;
import com.howhow.entity.Bulletin;
import com.howhow.entity.BulletinReply;
import com.howhow.entity.UserAccountDt;

public class BulletinReplyController {
	
	@Autowired
	private BulletinReplyService brService;
	
	@Autowired
	private BulletinService bService;
	
	@Autowired
	private UserAccountDtService uadService;

	@PostMapping("/insertBulletinReply.controller")
	@ResponseBody
	public BulletinReply insertBulletinReply(@RequestBody BulletinReply bReply) {
		bReply.setCreationtime(new Date());
		Bulletin blt1 = bService.findById(1);
		UserAccountDt user1 = uadService.findById(1);
		bReply.setRespondentid(user1);
		bReply.setBulletinid(blt1);
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
