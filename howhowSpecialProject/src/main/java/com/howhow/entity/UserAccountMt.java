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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table(name="useraccountmt")
@Component
public class UserAccountMt {
	
	@Id @Column(name="UserID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int userId;
	
	@Column(name="Account")
	String account;
	
	@Column(name="Password")
	String password;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "SYSTEMTIME")
	private java.util.Date systemTime;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserAccountDt userAccountDt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<PurchasedCourse> purchasedCourses = new HashSet<PurchasedCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<OrderMt> orderMts = new HashSet<OrderMt>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<CFCOrder> cfcOrders = new HashSet<CFCOrder>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<FavoriteCourse> favoriteCourses = new HashSet<FavoriteCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<CourseRank> courseRanks = new HashSet<CourseRank>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<UserBonus> userBonuses = new HashSet<UserBonus>();
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserStatus userstatus;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public java.util.Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(java.util.Date systemTime) {
		this.systemTime = systemTime;
	}

	public UserAccountDt getUserAccountDt() {
		return userAccountDt;
	}

	public void setUserAccountDt(UserAccountDt userAccountDt) {
		this.userAccountDt = userAccountDt;
	}

	public Set<PurchasedCourse> getPurchasedCourses() {
		return purchasedCourses;
	}

	public void setPurchasedCourses(Set<PurchasedCourse> purchasedCourses) {
		this.purchasedCourses = purchasedCourses;
	}

	public Set<OrderMt> getOrderMts() {
		return orderMts;
	}

	public void setOrderMts(Set<OrderMt> orderMts) {
		this.orderMts = orderMts;
	}

	public Set<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public Set<CFCOrder> getCfcOrders() {
		return cfcOrders;
	}

	public void setCfcOrders(Set<CFCOrder> cfcOrders) {
		this.cfcOrders = cfcOrders;
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

	public Set<UserBonus> getUserBonuses() {
		return userBonuses;
	}

	public void setUserBonuses(Set<UserBonus> userBonuses) {
		this.userBonuses = userBonuses;
	}

	public UserStatus getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(UserStatus userstatus) {
		this.userstatus = userstatus;
	}
	
	
	
}
