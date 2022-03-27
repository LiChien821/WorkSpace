package com.howhow.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.FavoriteCourse;
import com.howhow.shopping.repository.FavoriteCourseRepository;

@Service
public class FavoriteCourseService {

	@Autowired
	FavoriteCourseRepository repo;
	
	public FavoriteCourse findByID(int id) {
		
		Optional<FavoriteCourse> bean = repo.findById(id);
		if(bean.isEmpty()) {
			System.out.println("此favoritecourseid不存在");
			return null;
		}
		return bean.get();
	}
	
	public List<FavoriteCourse> findByUserID(int userid) {
		
		List<FavoriteCourse> list = repo.findByUserID(userid);
		return list;
	} 
	
	public FavoriteCourse insertFavoriteCourse(FavoriteCourse fc) {
		
		FavoriteCourse bean = repo.save(fc);
		return bean;
	}
	
	public boolean deleteByID(int id) {
		if(repo.findById(id).isEmpty()) {
			System.out.println("此favoritecourseid不存在，無法刪除");
			return false;
		}
		repo.deleteById(id);
		return true;
	}
	
	public List<Integer> findFavoriteCourseStatusForSearch(int userid) {
		List<Integer> list = new ArrayList<Integer>();
		List<FavoriteCourse> favorite = repo.findByUserID(userid);
		for (FavoriteCourse favoriteCourse : favorite) {
			int favoriteCourseID = favoriteCourse.getFavoriteCourseID();
			list.add(favoriteCourseID);
		}
		return list;
	}
}
