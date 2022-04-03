package com.howhow.course.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.howhow.course.api.JsonNoteRecevier;
import com.howhow.entity.Lectures;
import com.howhow.entity.Notes;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;

@Service
public class LearningNotesService {

	@Autowired

	private CommonNotesRepo notesRepo;

	@Autowired

	private CommonAccountRepository accountRepo;

	@Autowired

	private CommonLectureRepository lectureRepo;
	
	

	public boolean createNote(JsonNoteRecevier reciver) {

		try {
			
			if(!isDuplication(reciver)) {
				buildNewNotesAndSave(reciver);
			}	
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void buildNewNotesAndSave(JsonNoteRecevier reciver) {
		UserAccountMt theuser = accountRepo.findById(reciver.getUserID()).get();
		UserAccountDt theUDT = theuser.getUserAccountDt();
		Long duration = reciver.getDuration();
		Lectures thelecture = lectureRepo.findById(reciver.getLectureID()).get();

		Notes notes = new Notes();
		notes.setAuthor(theUDT);
		notes.setDuration(duration);
		notes.setNotedlecture(thelecture);
		notes.setContext(reciver.getNotescontext());

		notesRepo.save(notes);
	}

	private boolean isDuplication(JsonNoteRecevier reciver)  {
		UserAccountMt theuser = accountRepo.findById(reciver.getUserID()).get();
		UserAccountDt theUDT = theuser.getUserAccountDt();
		Long duration = reciver.getDuration();
		Lectures thelecture = lectureRepo.findById(reciver.getLectureID()).get();
		if (notesRepo.checkDuplication(theUDT,duration,thelecture) !=null) {
			return true;
		}else {
			return false;
		}
		
	}

	public Iterable<Notes> findAllNotesListByUIDAndLectureID(int UID, int lecturesID) {
		return notesRepo.findAllByUIDAndLectureID(UID,lecturesID);
		
	}

	public Iterable<Notes> findAllNotesListByUIDAndLectureID(JsonNoteRecevier reciver) {
		
		return notesRepo.findAllByUIDAndLectureID(reciver.getUserID(),reciver.getLectureID());
	}

	public Iterable<Notes> findAllNotesList() {
		Pageable pageable= PageRequest.of(0, 3);
		
		return notesRepo.findAll(pageable);
	}
}
