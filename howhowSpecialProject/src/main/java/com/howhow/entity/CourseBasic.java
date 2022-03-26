package com.howhow.entity;

import java.util.ArrayList;
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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "courseID")
@Entity @Table(name = "coursebasic")
@Component
public class CourseBasic {
	
	@Id @Column(name = "course_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int courseID;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "price")
	private long price;
	
	@Column(name = "discount")
	private double discount;
	
	public CourseBasic() {
	}
	
	public CourseBasic(String courseName, long price, double discount, Category category, int courseStatus,
			String courseCover, String description, String systemTime, UserAccountDt creator) {
		this.courseName = courseName;
		this.price = price;
		this.discount = discount;
		this.category = category;
		this.courseStatus = courseStatus;
		this.courseCover = courseCover;
		this.description = description;
		SystemTime = systemTime;
		this.creator = creator;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="course_status")
	private int courseStatus;
	
	@Column(name="course_cover")
	private String courseCover;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "system_time")
	private String SystemTime;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "creator_id", referencedColumnName = "user_id")
	private UserAccountDt creator;
	
	@OneToMany(mappedBy = "courseBasic")
	private List<Section> sectionList =new ArrayList<Section>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private List<PurchasedCourse> purchasedCourses = new ArrayList<PurchasedCourse>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseID", cascade = CascadeType.ALL)
	private List<OrderDt> orderDtList = new ArrayList<OrderDt>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private List<CFCourse> cfCourseList = new ArrayList<CFCourse>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private List<FavoriteCourse> favoriteCourseList = new ArrayList<FavoriteCourse>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBasic", cascade = CascadeType.ALL)
	private List<CourseRank> courseRankList = new ArrayList<CourseRank>();



	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	public String getCourseCover() {
		return courseCover;
	}

	public void setCourseCover(String courseCover) {
		this.courseCover = courseCover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
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

	public List<PurchasedCourse> getPurchasedCourses() {
		return purchasedCourses;
	}

	public void setPurchasedCourses(List<PurchasedCourse> purchasedCourses) {
		this.purchasedCourses = purchasedCourses;
	}

	public List<OrderDt> getOrderDtList() {
		return orderDtList;
	}

	public void setOrderDtList(List<OrderDt> orderDtList) {
		this.orderDtList = orderDtList;
	}

	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public List<CFCourse> getCfCourseList() {
		return cfCourseList;
	}

	public void setCfCourseList(List<CFCourse> cfCourseList) {
		this.cfCourseList = cfCourseList;
	}

	public List<FavoriteCourse> getFavoriteCourseList() {
		return favoriteCourseList;
	}

	public void setFavoriteCourseList(List<FavoriteCourse> favoriteCourseList) {
		this.favoriteCourseList = favoriteCourseList;
	}

	public List<CourseRank> getCourseRankList() {
		return courseRankList;
	}

	public void setCourseRankList(List<CourseRank> courseRankList) {
		this.courseRankList = courseRankList;
	}




	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	
}
