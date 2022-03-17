package com.howhow.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table(name="cfcourse")
@Component
public class CFCourse {
	
	@Id @Column(name="CROWDFUNDID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int crowdFundID;
	
	@Transient
	private int courseID;
	
	@Column(name="EXPIREDATE")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date expireDate;
	
	@Column(name="EXPECTVALUE")
	private int expectValue;
	
	@Column(name="DISCOUNTPRICE")
	private int discountPrice;
	
	@Column(name="SYSTEMTIME")
	private String systemTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COURSEID")
	private CourseBasic courseBasic;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cfCourse", cascade = CascadeType.ALL)
	private List<CFCOrder> cfcOrderList = new ArrayList<CFCOrder>();

	public int getCrowdFundID() {
		return crowdFundID;
	}

	public void setCrowdFundID(int crowdFundID) {
		this.crowdFundID = crowdFundID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getExpectValue() {
		return expectValue;
	}

	public void setExpectValue(int expectValue) {
		this.expectValue = expectValue;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public CourseBasic getCourseBasic() {
		return courseBasic;
	}

	public void setCourseBasic(CourseBasic courseBasic) {
		this.courseBasic = courseBasic;
	}

	public List<CFCOrder> getCfcOrderList() {
		return cfcOrderList;
	}

	public void setCfcOrderList(List<CFCOrder> cfcOrderList) {
		this.cfcOrderList = cfcOrderList;
	}

}
