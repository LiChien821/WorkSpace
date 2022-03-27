package com.howhow.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.ReportTypeRepository;
import com.howhow.entity.ReportType;


@Service
public class ReportTypeService {
	@Autowired
	private ReportTypeRepository rtr;
	
	public ReportType findById(int id) {
		if(rtr.findById(id).isPresent()) {
			return rtr.findById(id).get();
		}
		return null;
	}
}
