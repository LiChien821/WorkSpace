package com.howhow.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table(name="useraccountdt")
@Component
public class UserAccountDt {
	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name="property", value="userAccountMt"))
	@Id
	@Column(name="USERID")
	@GeneratedValue(generator = "generator")
	private int userId;
	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name="property" , value="userAccountMt"))
	@Column(name="ACCOUNT", unique = true)
	private String Account;
	
	@Column(name="EMAIL", unique=true)
	private String email;
	
	@Column(name="GIVENNAME")
	private String givenName;
	
	@Column(name="FAMILYNAME")
	private String familyName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name = "BIRTH")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private String Birth;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "ACCOUNTCREATIONTIME")
	private java.util.Date acountCreationTime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "SYSTEMTIME")
	private java.util.Date SystemTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserAccountMt userAccountMt;

	
	@OneToMany(mappedBy = "creator")
	private List<CourseBasic> createdCourseList=new ArrayList<CourseBasic>();
	
	@OneToMany(mappedBy = "launcher")
	private List<Question> questionList=new ArrayList<Question>();
	
	@OneToMany(mappedBy = "answerer")
	private List<Answer> answerList=new ArrayList<Answer>();
	
	@OneToMany(mappedBy = "author")
	private List<Notes> notesList=new ArrayList<Notes>();
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return Birth;
	}

	public void setBirth(String birth) {
		Birth = birth;
	}

	public java.util.Date getAcountCreationTime() {
		return acountCreationTime;
	}

	public void setAcountCreationTime(java.util.Date acountCreationTime) {
		this.acountCreationTime = acountCreationTime;
	}

	public java.util.Date getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(java.util.Date systemTime) {
		SystemTime = systemTime;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}


	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public List<Notes> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<Notes> notesList) {
		this.notesList = notesList;
	}

	public List<CourseBasic> getCreatedCourseList() {
		return createdCourseList;
	}

	public void setCreatedCourseList(List<CourseBasic> createdCourseList) {
		this.createdCourseList = createdCourseList;
	}
	
	
	
}