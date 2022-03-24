package com.howhow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="courserank",
uniqueConstraints=@UniqueConstraint(columnNames={"course_id", "user_id"}))
@Component
public class CourseRank {
	
	@Id @Column(name = "courserank_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseRankID;
	
	@Column(name = "course_rank")
	private int courseRank;
	
	@Column(name = "rank_message")
	private String rankMessage;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "rank_date")
	private String rankDate;
	
	@Column(name = "system_time")
	private String systemTime;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserAccountMt userAccountMt;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private CourseBasic courseBasic;

	public CourseRank(int courseRank, String rankMessage, String rankDate, String systemTime,
			UserAccountMt userAccountMt, CourseBasic courseBasic) {
		this.courseRank = courseRank;
		this.rankMessage = rankMessage;
		this.rankDate = rankDate;
		this.systemTime = systemTime;
		this.userAccountMt = userAccountMt;
		this.courseBasic = courseBasic;
	}
	
	public CourseRank() {
	}
	
	public int getCourseRank() {
		return courseRank;
	}

	public void setCourseRank(int courseRank) {
		this.courseRank = courseRank;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public int getCourseRankID() {
		return courseRankID;
	}

	public void setCourseRankID(int courseRankID) {
		this.courseRankID = courseRankID;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}

	public CourseBasic getCourseBasic() {
		return courseBasic;
	}

	public void setCourseBasic(CourseBasic courseBasic) {
		this.courseBasic = courseBasic;
	}

	public String getRankMessage() {
		return rankMessage;
	}

	public void setRankMessage(String rankMessage) {
		this.rankMessage = rankMessage;
	}

	public String getRankDate() {
		return rankDate;
	}

	public void setRankDate(String rankDate) {
		this.rankDate = rankDate;
	}

	
}
