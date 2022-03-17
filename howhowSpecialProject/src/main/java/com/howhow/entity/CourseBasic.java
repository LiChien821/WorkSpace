package com.howhow.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	@ManyToOne
	@JoinColumn(name="CategoryID")
	private Category category;
	
	@Column(name="CourseStatus")
	private int courseStatus;
	
	@Column(name="CourseCover")
	private String courseCover;
	
	
	@Column
	private String description;
	
	@Column(name="SystemTime")
	private String SystemTime;
	
	@ManyToOne
	@JoinColumn
	private UserAccountDt creator;
	
	@OneToMany(mappedBy = "courseBasic")
	private List<Section> sectionList =new ArrayList<Section>();
	
	
	
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserAccountDt getCreator() {
		return creator;
	}

	public void setCreator(UserAccountDt creator) {
		this.creator = creator;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public Set<PurchasedCourse> getPurchasedCourses() {
		return purchasedCourses;
	}

	public void setPurchasedCourses(Set<PurchasedCourse> purchasedCourses) {
		this.purchasedCourses = purchasedCourses;
	}

	public Set<OrderDt> getOrderDts() {
		return orderDts;
	}

	public void setOrderDts(Set<OrderDt> orderDts) {
		this.orderDts = orderDts;
	}

	public Set<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public Set<CFCourse> getCfCourses() {
		return cfCourses;
	}

	public void setCfCourses(Set<CFCourse> cfCourses) {
		this.cfCourses = cfCourses;
	}

	public Set<FavoriteCourse> getFavoriteCourses() {
		return favoriteCourses;
	}

	public void setFavoriteCourses(Set<FavoriteCourse> favoriteCourses) {
		this.favoriteCourses = favoriteCourses;
	}

	public Set<CourseRank> getCourseRanks() {
		return courseRanks;
	}

	public void setCourseRanks(Set<CourseRank> courseRanks) {
		this.courseRanks = courseRanks;
	}
	
	
	
}
