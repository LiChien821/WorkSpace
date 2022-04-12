package com.howhow.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/cms")
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
	


	@GetMapping("/report")
	public String guidToReport() {
		return "cms/reportmain.html";
	}

	// 檢舉bulletin
	@PostMapping("/bulletinreport")
	public void addBulletinReport(@RequestBody BulletinDTO bulletinId) {
		BulletinReportRecord reportrecord = new BulletinReportRecord();
		Bulletin bulletin = bs.findById(bulletinId.getBulletinid());
		
		reportrecord.setTypeobj(rts.findById(bulletinId.getReporttypeid()));
		reportrecord.setBulletin(bulletin);
		reportrecord.setSystemtime(UtilityTool.getSysTime());
		reportrecord.setUserdt(bulletin.getLauncherid());
		
		brrs.insertReport(reportrecord);
	}
	
	// 檢舉reply
	@PostMapping("/replyreport")
	public void addReplyReport(@RequestBody BulletinReplyDTO replyId) {
		BulletinReplyReportRecord  reportrecord = new BulletinReplyReportRecord();
		BulletinReply reply = brs.findById(replyId.getBulletinreplyid());
		
		reportrecord.setBulletinreply(reply);
		reportrecord.setUserdt(reply.getRespondentid());
		reportrecord.setSystemtime(UtilityTool.getSysTime());
		reportrecord.setTypeobj(rts.findById(replyId.getReporttypeid()));
		
		brrrs.insertReply(reportrecord);
	}
	
	// 顯示所有被檢舉的問題
	@ResponseBody
	@GetMapping("/showbulletinreport")
	public List<BulletinReportDTO> showAllReport() {
		
		List<BulletinReportRecord> records = brrs.findAll();
		List<BulletinReportDTO> details = new ArrayList<BulletinReportDTO>();
		
		for (BulletinReportRecord record : records) {
			
			BulletinReportDTO DO = new BulletinReportDTO();
			
			DO.setReportid(record.getReportID());
			DO.setBulletionID(record.getBulletin().getBulletinid());
			DO.setReportedPerson(record.getUserdt().getUserId());
			DO.setReportcontent(record.getBulletin().getContent());
			DO.setReporttypename(record.getTypeobj().getReportname());
			DO.setReporttime(record.getSystemtime());
			
			details.add(DO);
		}
		return details;
	}
	
	//顯示所有被檢舉的回答
	@ResponseBody
	@GetMapping("/showreplyreport")
	public List<ReplyReportDTO> showAllReplyReport(){
		List<ReplyReportDTO> replyDTOs = new ArrayList<ReplyReportDTO>();
		List<BulletinReplyReportRecord> reportrecords = brrrs.findAll();
		
		for(BulletinReplyReportRecord reportrecord : reportrecords) {
			ReplyReportDTO replyDTO = new ReplyReportDTO();
			
			replyDTO.setReplyID(reportrecord.getBulletinreplyID());
			replyDTO.setReportcontent(reportrecord.getBulletinreply().getReplycontent());
			replyDTO.setReporttypename(reportrecord.getTypeobj().getReportname());
			replyDTO.setReportedPerson(reportrecord.getUserdt().getUserId());
			replyDTO.setReportid(reportrecord.getReportID());
			replyDTO.setReporttime(reportrecord.getSystemtime());
			
			replyDTOs.add(replyDTO);
		}
		return replyDTOs;
	}
	
	
	// 駁回問題檢舉
	@ResponseBody
	@DeleteMapping("/reportdata")
	public List<BulletinReportDTO> deleteReport(@RequestBody BulletinReportDTO reportdetail){
		brrs.deleteReport(reportdetail.getReportid());
		return showAllReport();
	}
	
	// 刪除問題
	@ResponseBody
	@DeleteMapping("/bulletin")
	public List<BulletinReportDTO> deleteQuestion(@RequestBody BulletinReportDTO reportdetail) {
		bs.deleteById(reportdetail.getBulletionID());
		return showAllReport();
	}
}
