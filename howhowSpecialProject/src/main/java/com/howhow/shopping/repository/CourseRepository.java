package com.howhow.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.CourseBasic;

public interface CourseRepository extends JpaRepository<CourseBasic, Integer> {

}
