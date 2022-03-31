package com.howhow.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.UserAccountDtService;
import com.howhow.cms.service.SystemMessageService;
import com.howhow.entity.SystemMessage;

@Controller
@RequestMapping("/cms")
public class SystemMessageController {
	
	@Autowired
	private SystemMessageService sms;
	
	@Autowired
	private UserAccountDtService uads;
	
	@GetMapping("/systemmessage")
	public String guidToSystemMessage() {
		return "cms/systemmessagemain.html";
	}
	
	// 發布訊息
	@ResponseBody
	@PostMapping("/systemmessagedata")
	public void sendMessage(@RequestBody SystemMessage message) {
		message.setUserdt(uads.findById(message.getUserdt().getUserId()));
		sms.insertSystemMessage(message);
	}
	
	// 顯示指定用戶訊息
	@ResponseBody
	@GetMapping("/systemmessage/{uerid}")
	public List<SystemMessage> showMessage(@PathVariable("userid") int id){
		return sms.findMessageByUserId(id);
	}
	
}
