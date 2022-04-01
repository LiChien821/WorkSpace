package com.howhow.course.bulletin.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.course.bulletin.repository.BulletinRepository;
import com.howhow.entity.Bulletin;

@Service
@Transactional
public class BulletinService {
	
	@Autowired
	private BulletinRepository bRepo;
	
	//insert
	public Bulletin insert(Bulletin bulletin) {
		return bRepo.save(bulletin);
	}
	
	//findById
	public Bulletin findById(Integer id) {
		Optional<Bulletin> op1 = bRepo.findById(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1.get();	
	}
	
	//findAllByCourseId
	public List<Bulletin> findAllByCourseId(Integer id) {
		 List<Bulletin> op1 = bRepo.findAllByCourseId(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1;	
	}
	
	//findCreatorIdByCourseId
	public Integer findCreatorIdByCourseId(Integer id) {
		Integer creatorid = bRepo.findCreatorIdByCourseId(id);
		return creatorid;	
	}
	
	//findCreatorIdByCourseId
	public List<Bulletin> findAllByLectureId(Integer id) {
		List<Bulletin> op1 = bRepo.findAllByLectureId(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1;
	}
	
	//findAll
	public List<Bulletin> findAll() {
		return bRepo.findAll();
	}
	
	//update
	public Bulletin update(Bulletin bulletin) {
	return bRepo.save(bulletin);
	}
	
	//deleteById
	public void deleteById(Integer id) {
		bRepo.deleteById(id);
	}
	
	//deleteAll
	public void deleteAll() {
		bRepo.deleteAll();
	}

}