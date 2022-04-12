package com.howhow.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.cms.dto.ReportDetailObj;
import com.howhow.cms.service.QuestionService;
import com.howhow.cms.service.ReportRecordService;
import com.howhow.course.bulletin.service.BulletinService;
import com.howhow.entity.ReportRecord;

@Controller
@RequestMapping("/cms")
public class ReportController {
	@Autowired
	private ReportRecordService rrs;
	
	@Autowired
	private BulletinService bs;

	@GetMapping("/report")
	public String test() {
		return "cms/reportmain.html";
	}

	// 顯示所有被檢舉的問題
	@ResponseBody
	@GetMapping("/showreport")
	public List<ReportDetailObj> showAllReport() {
		
		List<ReportRecord> records = rrs.findAll();
		List<ReportDetailObj> details = new ArrayList<ReportDetailObj>();
		
		for (ReportRecord record : records) {
			
			ReportDetailObj DO = new ReportDetailObj();
			
			DO.setReportid(record.getReportID());
			DO.setBulletionID(record.getBulletin().getBulletinid());
			DO.setReportedPerson(record.getUsermt().getUserId());
			DO.setReportcontent(record.getBulletin().getContent());
			DO.setReporttypename(record.getTypeobj().getReportname());
			DO.setReporttime(record.getSystemtime());
			
			details.add(DO);
		}
		return details;
	}
	
	// 駁回檢舉
	@ResponseBody
	@DeleteMapping("/reportdata")
	public List<ReportDetailObj> deleteReport(@RequestBody ReportDetailObj reportdetail){
		rrs.deleteReport(reportdetail.getReportid());
		return showAllReport();
	}
	
	// 刪除問題
	@ResponseBody
	@DeleteMapping("/bulletin")
	public List<ReportDetailObj> deleteQuestion(@RequestBody ReportDetailObj reportdetail) {
		bs.deleteById(reportdetail.getBulletionID());
		return showAllReport();
	}
}
