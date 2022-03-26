package com.howhow.course.common;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.Category;

public interface CommonCategoryRepository extends PagingAndSortingRepository<Category, Integer>{

}
