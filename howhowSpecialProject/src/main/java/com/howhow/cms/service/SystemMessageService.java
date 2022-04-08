package com.howhow.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.SystemMessageRepository;
import com.howhow.entity.SystemMessage;

@Service
public class SystemMessageService {
	
	@Autowired
	private SystemMessageRepository smr;
	
	public List<SystemMessage> findMessageByUserId(int id) {
		return smr.findByUserId(id);
	}
	
	public SystemMessage insertSystemMessage(SystemMessage message) {
		return smr.save(message);
	}
	
}
