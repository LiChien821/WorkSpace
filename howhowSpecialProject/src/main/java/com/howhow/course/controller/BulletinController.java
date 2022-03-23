package com.howhow.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.course.service.BulletinService;
import com.howhow.entity.Bulletin;

@Controller
public class BulletinController {
	
	@Autowired
	private BulletinService bService;
	
//	@PostMapping(value = "/posts")
//	public String createPost(Model model, @Valid @ModelAttribute("postDto") Post post) {
//		logger.info("Creating post >> " + post);
//		this.postService.createPost(post);
//		return "redirect:/posts";
//	}
	
//	@PostMapping("/addnewbulletin.controller")
//	@ResponseBody
//	public Bulletin addNewBulletin (@RequestBody Bulletin bulletin) {
//		bulletin.setCreationdate(new Date());
//		return bService.insert(bulletin);
//	}
	
	@PostMapping("/weijie/querybulletinbyid.controller")
	@ResponseBody
	public Bulletin queryBulletinById(@RequestParam Integer id) {
		return bService.findById(id);
	}
	
	@PostMapping("/weijie/queryallbulletin.controller")
	@ResponseBody
	public List<Bulletin> findAllBulletin() {
		return bService.findAllBulletin();
	}
	
	@PostMapping("/weijie/deletebulletinbyid.controller")
	@ResponseBody
	public void deleteBulletinById(@RequestParam Integer id) {
		bService.deleteBulletinById(id);
	}
}
