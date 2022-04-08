package com.howhow.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.FavoriteCourse;
import com.howhow.shopping.exception.FavoriteCourseNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.repository.FavoriteCourseRepository;

@Service
public class FavoriteCourseService {

	@Autowired
	FavoriteCourseRepository repo;

	public FavoriteCourse findByID(int id) {

		Optional<FavoriteCourse> bean = repo.findById(id);
		if (bean.isEmpty()) {
			System.out.println("此favoritecourseid不存在");
			return null;
		}
		return bean.get();
	}

	public List<FavoriteCourse> findByUserID(int userid) {

		List<FavoriteCourse> list = repo.findByUserID(userid);
		return list;
	}

	public FavoriteCourse insertFavoriteCourse(FavoriteCourse fc) throws UserOrCourseNotFoundException {

		try {
			FavoriteCourse bean = repo.save(fc);
			return bean;
		} catch (Exception e) {
			throw new UserOrCourseNotFoundException();
		}
	}

	public boolean deleteByID(int id) throws FavoriteCourseNotFoundException {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			throw new FavoriteCourseNotFoundException();
		}
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
	
	public boolean findFavoriteCourseStatus(int userid, int courseid) {
		FavoriteCourse bean = repo.findFavoriteCourseStatus(userid, courseid);
		if(bean!=null) return true;
		return false;
	}
	
	public boolean removeFavoriteCourse(int userid, int courseid) {
		FavoriteCourse status = repo.findFavoriteCourseStatus(userid, courseid);
		if(status!=null) {
			repo.deleteById(status.getFavoriteCourseID());
			return true;
		}
		return false;
	}
	
	
}
