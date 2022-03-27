package com.howhow.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.cms.dto.ReportDetailObj;
import com.howhow.cms.service.QuestionService;
import com.howhow.cms.service.ReportRecordService;
import com.howhow.entity.ReportRecord;

@Controller
@RequestMapping("/cms")
public class CMSController {
	
	@Autowired
	private ReportRecordService rrs;
	
	@GetMapping("/report")
	public String test() {
		return "CMS/cmsreport.html";
	}
	
	@ResponseBody
	@GetMapping("/showreport")
	public List<ReportDetailObj> showAllReport(){
		List<ReportRecord> records = rrs.findAll();
		List<ReportDetailObj> details = new ArrayList<ReportDetailObj>();
		for(ReportRecord record : records) {
			ReportDetailObj DO = new ReportDetailObj();
			DO.setReportedPerson(record.getUsermt().getUserId());
			DO.setReportcontent(record.getQuestion().getQuestionContext());
			DO.setReporttypename(record.getTypeobj().getReportname());
			DO.setReporttime(record.getSystemtime());
			details.add(DO);
		}
		return details;
	}
}
