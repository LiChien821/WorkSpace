package com.howhow.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.CourseStatuseTypeRepository;
import com.howhow.entity.CourseStatusType;

@Service
public class CourseStatusTypeService {
	@Autowired
	private CourseStatuseTypeRepository cstr;

	public CourseStatusType findById(int id) {
		if (null != cstr.findById(id)) {
			return cstr.findById(id).get();
		}
		return null;
	}
}
