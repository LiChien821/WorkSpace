package com.howhow.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.QuestionRepository;
import com.howhow.entity.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository qr;

	public Question findById(int id) {
		if (null != qr.findById(id)) {
			return qr.findById(id).get();
		}
		return qr.findById(id).get();
	}
	
	public void deleteQuestion(int id) {
		qr.deleteById(id);
	}
}
