package com.howhow.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.FavoriteCourse;

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Integer> {
	
	@Query(value="select * from FavoriteCourse where user_id=?1", nativeQuery=true)
	public List<FavoriteCourse> findByUserID(int id);
}
