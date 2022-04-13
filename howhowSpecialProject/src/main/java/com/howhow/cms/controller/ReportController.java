package com.howhow.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.cms.dto.BulletinDTO;
import com.howhow.cms.dto.BulletinReplyDTO;
import com.howhow.cms.dto.BulletinReportDTO;
import com.howhow.cms.dto.ReplyReportDTO;
import com.howhow.cms.service.BulletinReplyReportRecordService;
import com.howhow.cms.service.BulletinReportRecordService;
import com.howhow.cms.service.ReportTypeService;
import com.howhow.course.bulletin.service.BulletinReplyService;
import com.howhow.course.bulletin.service.BulletinService;
import com.howhow.entity.Bulletin;
import com.howhow.entity.BulletinReply;
import com.howhow.entity.BulletinReplyReportRecord;
import com.howhow.entity.BulletinReportRecord;
import com.howhow.util.UtilityTool;

@Controller
public class ReportController {
	@Autowired
	private BulletinReportRecordService brrs;

	@Autowired
	private BulletinService bs;

	@Autowired
	private ReportTypeService rts;

	@Autowired
	private BulletinReplyReportRecordService brrrs;

	@Autowired
	private BulletinReplyService brs;

	@GetMapping("/cms/report")
	public String guidToReport() {
		return "cms/reportmain.html";
	}

	//	檢舉bulletin
	@PostMapping("/bulletinreport")
	@ResponseBody
	public boolean addBulletinReport(@RequestBody BulletinDTO bulletinId) {
		BulletinReportRecord reportrecord = new BulletinReportRecord();
		Bulletin bulletin = bs.findById(bulletinId.getBulletinid());
		reportrecord.setTypeobj(rts.findById(bulletinId.getReporttypeid()));
		reportrecord.setBulletin(bulletin);
		reportrecord.setSystemtime(UtilityTool.getSysTime());
		reportrecord.setUserdt(bulletin.getLauncherid());

		brrs.insertReport(reportrecord);
		return true;
	}
	
	//	檢舉reply
	@PostMapping("/replyreport")
	@ResponseBody
	public boolean addReplyReport(@RequestBody BulletinReplyDTO replyId) {
		BulletinReplyReportRecord  reportrecord = new BulletinReplyReportRecord();
		BulletinReply reply = brs.findById(replyId.getBulletinreplyid());
		
		reportrecord.setBulletinreply(reply);
		reportrecord.setUserdt(reply.getRespondentid());
		reportrecord.setSystemtime(UtilityTool.getSysTime());
		reportrecord.setTypeobj(rts.findById(replyId.getReporttypeid()));
	
		brrrs.insertReply(reportrecord);
		return true;
	}

	// 顯示所有被檢舉的問題
	@ResponseBody
	@GetMapping("/cms/bulletinreport")
	public List<BulletinReportDTO> showAllReport() {

		List<BulletinReportRecord> records = brrs.findAll();
		List<BulletinReportDTO> bulletinDTOs = new ArrayList<BulletinReportDTO>();

		for (BulletinReportRecord record : records) {

			BulletinReportDTO bulletinDTO = new BulletinReportDTO();

			bulletinDTO.setReportid(record.getReportID());
			bulletinDTO.setBulletionID(record.getBulletin().getBulletinid());
			bulletinDTO.setReportedPerson(record.getUserdt().getUserId());
			bulletinDTO.setReportcontent(record.getBulletin().getContent());
			bulletinDTO.setReporttypename(record.getTypeobj().getReportname());
			bulletinDTO.setReporttime(record.getSystemtime());

			bulletinDTOs.add(bulletinDTO);
		}
		return bulletinDTOs;
	}

	// 顯示所有被檢舉的回答
	@ResponseBody
	@GetMapping("/cms/replyreport")
	public List<ReplyReportDTO> showAllReplyReport() {
		List<ReplyReportDTO> replyDTOs = new ArrayList<ReplyReportDTO>();
		List<BulletinReplyReportRecord> records = brrrs.findAll();

		for (BulletinReplyReportRecord record : records) {
			ReplyReportDTO replyDTO = new ReplyReportDTO();

			replyDTO.setReplyID(record.getBulletinreplyID());
			replyDTO.setReportcontent(record.getBulletinreply().getReplycontent());
			replyDTO.setReporttypename(record.getTypeobj().getReportname());
			replyDTO.setReportedPerson(record.getUserdt().getUserId());
			replyDTO.setReportid(record.getReportID());
			replyDTO.setReporttime(record.getSystemtime());

			replyDTOs.add(replyDTO);
		}
		return replyDTOs;
	}

	// 問題檢舉處理
	@ResponseBody
	@DeleteMapping("/cms/bulletinreport/{handle}")
	public List<BulletinReportDTO> deleteReport(@RequestBody BulletinReportDTO reportdetail, @PathVariable("handle") int handle) {
		if(handle == 1) {
			brrs.deleteReport(reportdetail.getReportid()); // 駁回檢舉
		}else {
			bs.deleteById(reportdetail.getBulletionID()); // 刪除問題
		}
		
		return showAllReport();
	}

	// 回答檢舉處理
	@ResponseBody
	@DeleteMapping("/cms/replyreport/{handle}")
	public List<ReplyReportDTO> deleteReplyReport(@RequestBody ReplyReportDTO replyDTO, @PathVariable("handle") int handle) {
		if(handle == 1) {
			brs.deleteById(replyDTO.getReportid()); //駁回檢舉
		}else {
			bs.deleteById(replyDTO.getReplyID()); //刪除回答
		}
		
		return showAllReplyReport();
	}
}
