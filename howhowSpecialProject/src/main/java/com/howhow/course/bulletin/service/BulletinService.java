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
	
	public Bulletin insert(Bulletin bulletin) {
		return bRepo.save(bulletin);
	}
	
	public Bulletin update(Bulletin bulletin) {
	return bRepo.save(bulletin);
	}
	
	public void deleteById(Integer id) {
		bRepo.deleteById(id);
	}
	
	public Bulletin findById(Integer id) {
		Optional<Bulletin> op1 = bRepo.findById(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1.get();	
	}
	
	public List<Bulletin> findAllBulletin() {
		return bRepo.findAll();
	}
	
	public void deleteBulletinById(Integer id) {
		bRepo.deleteById(id);
	}
}