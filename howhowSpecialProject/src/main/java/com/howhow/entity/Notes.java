package com.howhow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private int noteID;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private UserAccountDt author;
	
	@ManyToOne
	@JoinColumn(name="notedlecture_id")
	private Lectures notedlecture;
	
	@Column(name = "duration")
	private long duration;
	
	@Column(name = "context")
	private String context;
	
	@Column(name = "system_time")
	private String SystemTime;
	
	public int getNoteID() {
		return noteID;
	}

	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}

	public UserAccountDt getAuthor() {
		return author;
	}

	public void setAuthor(UserAccountDt author) {
		this.author = author;
	}

	public Lectures getNotedlecture() {
		return notedlecture;
	}

	public void setNotedlecture(Lectures notedlecture) {
		this.notedlecture = notedlecture;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}
	
}
