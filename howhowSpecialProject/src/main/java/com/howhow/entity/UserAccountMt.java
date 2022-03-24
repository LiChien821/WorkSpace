package com.howhow.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

@Entity @Table(name = "useraccountmt")
@Component
public class UserAccountMt implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int userID;
	
	@Column(name = "account")
	String account;
	
	@Column(name = "password")
	String password;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "system_time")
	private java.util.Date systemTime;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserAccountDt userAccountDt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<PurchasedCourse> purchasedCourseList = new ArrayList<PurchasedCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<OrderMt> orderMtList = new ArrayList<OrderMt>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<CFCOrder> cfcOrderList = new ArrayList<CFCOrder>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<FavoriteCourse> favoriteCourseList = new ArrayList<FavoriteCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<CourseRank> courseRankList = new ArrayList<CourseRank>();
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserBonus userBonus;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserStatus userstatus;

	public int getUserID() {
		return userID;
	}

	public void setUserId(int userID) {
		this.userID = userID;
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


	public UserStatus getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(UserStatus userstatus) {
		this.userstatus = userstatus;
	}

	public List<PurchasedCourse> getPurchasedCourseList() {
		return purchasedCourseList;
	}

	public void setPurchasedCourseList(List<PurchasedCourse> purchasedCourseList) {
		this.purchasedCourseList = purchasedCourseList;
	}

	public List<OrderMt> getOrderMtList() {
		return orderMtList;
	}

	public void setOrderMtList(List<OrderMt> orderMtList) {
		this.orderMtList = orderMtList;
	}

	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public List<CFCOrder> getCfcOrderList() {
		return cfcOrderList;
	}

	public void setCfcOrderList(List<CFCOrder> cfcOrderList) {
		this.cfcOrderList = cfcOrderList;
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

	public UserBonus getUserBonus() {
		return userBonus;
	}

	public void setUserBonus(UserBonus userBonus) {
		this.userBonus = userBonus;
	}
		
	
}
