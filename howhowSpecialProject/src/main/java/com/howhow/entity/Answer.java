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
@Table(name="answerBasic")
public class Answer {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerID;
	
	@ManyToOne
	@JoinColumn(name="QUESTIONID")
	private Question question;
	
	@ManyToOne
	@JoinColumn(name="ANSWER_ID")
	private UserAccountDt answerer; 
	
	@Column
	private String answerContext;
	
	@Column(name="SYSTEMTIME")
	private String SystemTime;

	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswerContext() {
		return answerContext;
	}

	public void setAnswerContext(String answerContext) {
		this.answerContext = answerContext;
	}

	public UserAccountDt getAnswerer() {
		return answerer;
	}

	public void setAnswerer(UserAccountDt answerer) {
		this.answerer = answerer;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}
	
	
}
