package com.howhow.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.FavoriteCourse;

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Integer> {

}
