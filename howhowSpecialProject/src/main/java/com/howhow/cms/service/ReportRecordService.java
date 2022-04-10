package com.howhow.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.ReportRecordRepository;
import com.howhow.entity.ReportRecord;


@Service
public class ReportRecordService {
	@Autowired
	private ReportRecordRepository rrr;
	
	public ReportRecord insertReport(ReportRecord reportRecord) {
		return rrr.save(reportRecord);
	}
	
	public List<ReportRecord> findAll(){
		return rrr.findAll();
	}
	
	public void deleteReport(int id) {
		rrr.deleteById(id);
	}
}
