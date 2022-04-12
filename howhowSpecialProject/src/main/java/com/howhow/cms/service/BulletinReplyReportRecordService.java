package com.howhow.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.BulletinReplyReportRecordRepository;
import com.howhow.entity.BulletinReplyReportRecord;

@Service
public class BulletinReplyReportRecordService {
	@Autowired
	private BulletinReplyReportRecordRepository repo;

	public BulletinReplyReportRecord findById(int id) {
		if(null != repo.findById(id)) {
			return repo.findById(id).get();
		}
		return null;
	}
	
	public BulletinReplyReportRecord insertReply(BulletinReplyReportRecord reply) {
		return repo.save(reply);
	}
	
	public List<BulletinReplyReportRecord> findAll(){
		return repo.findAll();
	}
	
	public void deleteReport(int id) {
		repo.deleteById(id);
	}
}
