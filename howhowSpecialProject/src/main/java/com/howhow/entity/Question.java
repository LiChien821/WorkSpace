package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="questionBasic")
public class Question {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionID;
	
	@ManyToOne
	@JoinColumn(name="LECTURESID")
	private Lectures lectures;
	
	@ManyToOne
	@JoinColumn(name="LAUNCHERID")
	private UserAccountDt launcher;
	
	@Column
	private String questionTitle;
	
	@Column
	private String questionContext;
	
	@Column(name="SYSTEMTIME")
	private String SystemTime;
	
	@OneToMany(mappedBy = "question")
	private List<Answer> answerList = new ArrayList<Answer>(); 

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}



	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContext() {
		return questionContext;
	}

	public void setQuestionContext(String questionContext) {
		this.questionContext = questionContext;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public Lectures getLectures() {
		return lectures;
	}

	public void setLectures(Lectures lectures) {
		this.lectures = lectures;
	}

	public UserAccountDt getLauncher() {
		return launcher;
	}

	public void setLauncher(UserAccountDt launcher) {
		this.launcher = launcher;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}
}
