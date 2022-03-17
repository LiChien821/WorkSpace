package com.howhow.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name="coursebasic")
@Component("coursebasic")
public class CourseBasic {
	
	@Id @Column(name="CourseID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int courseId;
	
	@Column(name="CourseName")
	private String courseName;
	
	@Column(name="Price")
	private int price;
	
	@Column(name="Discount")
	private double discount;
	
	@Column(name="CategoryID")
	private int categoryId;
	
	@Column(name="CourseStatus")
	private int courseStatus;
	
	@Column(name="CourseCover")
	private String courseCover;
	
	@Column(name="SystemTime")
	private String SystemTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private Set<PurchasedCourse> purchasedCourses = new HashSet<PurchasedCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private Set<OrderDt> orderDts = new HashSet<OrderDt>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private Set<CFCourse> cfCourses = new HashSet<CFCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private Set<FavoriteCourse> favoriteCourses = new HashSet<FavoriteCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private Set<CourseRank> courseRanks = new HashSet<CourseRank>();
	
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getCourseCover() {
		return courseCover;
	}

	public void setCourseCover(String courseCover) {
		this.courseCover = courseCover;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}
	
	
	
}
