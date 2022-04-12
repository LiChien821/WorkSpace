package com.howhow.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.BulletinReportRecordRepository;
import com.howhow.entity.BulletinReportRecord;


@Service
public class BulletinReportRecordService {
	@Autowired
	private BulletinReportRecordRepository rrr;
	
	public BulletinReportRecord insertReport(BulletinReportRecord reportRecord) {
		return rrr.save(reportRecord);
	}
	
	public List<BulletinReportRecord> findAll(){
		return rrr.findAll();
	}
	
	public void deleteReport(int id) {
		rrr.deleteById(id);
	}
}
