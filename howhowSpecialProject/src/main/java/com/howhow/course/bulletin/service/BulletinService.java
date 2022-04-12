package com.howhow.course.bulletin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.howhow.course.bulletin.repository.BulletinRepository;
import com.howhow.entity.Bulletin;
import com.howhow.entity.Lectures;
import com.howhow.entity.Section;

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
	
	//findAllByLectureId
	public List<Bulletin> findAllByLectureId(Integer id) {
		List<Bulletin> op1 = bRepo.findAllByLectureId(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1;
	}
	
	//findAllBySearch
	public List<Bulletin> findAllBySearch(String str, int cid) {
		List<Bulletin> op1 = bRepo.findAllBySearch("%"+str+"%", cid);
		if (op1.isEmpty()) {
			return null;
		}
		return op1;
	}
	
//	findSectionInfoByCourseId
//	public List<Object> findSectionInfoByCourseId(Integer id) {
//		List<Object> op1 = bRepo.findSectionInfoByCourseId(id);
//		if (op1.isEmpty()) {
//			return null;
//		}
//		return op1;
//	}
	
	
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