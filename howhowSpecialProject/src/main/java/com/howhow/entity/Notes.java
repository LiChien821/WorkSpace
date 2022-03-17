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
@Table(name="noteBasic")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int noteID;
	
	
	@ManyToOne
	@JoinColumn(name="AUTHORID")
	private UserAccountDt author;
	
	@ManyToOne
	@JoinColumn(name="NOTEDLECTUREID")
	private Lectures notedlecture;
	
	@Column
	private long duration;
	
	@Column
	private String context;
	
	@Column(name="SYSTEMTIME")
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
