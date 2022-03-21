package com.howhow.course.common;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.CourseBasic;



public interface APICourseRepository extends PagingAndSortingRepository<CourseBasic, Integer> {

}
