package com.howhow.course.bulletin.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.course.bulletin.repository.BulletinReplyRepository;
import com.howhow.entity.BulletinReply;

@Service
@Transactional
public class BulletinReplyService {
	
	@Autowired
	private BulletinReplyRepository brRepo;
	
	//insert
	public BulletinReply insert(BulletinReply bReply) {
		return brRepo.save(bReply);
	}
	
	//findById
	public BulletinReply findById(Integer id) {
		Optional<BulletinReply> op1 = brRepo.findById(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1.get();	
	}
	
	//findAllByBulletinId
	public List<BulletinReply> findAllByBulletinId(Integer id) {
		 List<BulletinReply> op1 = brRepo.findAllByBulletinId(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1;	
	}
	
	//findAll
	public List<BulletinReply> findAll() {
		return brRepo.findAll();
	}
	
	//update
	public BulletinReply update(BulletinReply bReply) {
	return brRepo.save(bReply);
	}
	
	//deleteById
	public void deleteById(Integer id) {
		brRepo.deleteById(id);
	}
	
	//deleteAll
	public void deleteAll() {
		brRepo.deleteAll();
	}

}
