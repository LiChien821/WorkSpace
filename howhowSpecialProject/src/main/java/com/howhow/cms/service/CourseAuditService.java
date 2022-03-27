package com.howhow.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.CourseAuditRepository;
import com.howhow.entity.CourseAudit;

@Service
public class CourseAuditService {
	@Autowired
	private CourseAuditRepository car;
	
	public CourseAudit findById(int id) {
		if(null != car.findById(id)) {
			return car.findById(id).get();
		}
		return null;
	}
}
