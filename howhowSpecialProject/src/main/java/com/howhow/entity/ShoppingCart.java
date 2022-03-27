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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="shoppingcart",
uniqueConstraints=@UniqueConstraint(columnNames={"user_id", "course_id"}))
@Component
public class ShoppingCart {
	
	@Id @Column(name = "shoppingcart_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shoppingCartID;
	
	@Column(name = "system_time")
	private String systemTime;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserAccountMt userAccountMt;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private CourseBasic courseBasic;

	public ShoppingCart() {
	}
	
	
	public ShoppingCart(String systemTime, UserAccountMt userAccountMt, CourseBasic courseBasic) {
		super();
		this.systemTime = systemTime;
		this.userAccountMt = userAccountMt;
		this.courseBasic = courseBasic;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public int getShoppingCartID() {
		return shoppingCartID;
	}

	public void setShoppingCartID(int shoppingCartID) {
		this.shoppingCartID = shoppingCartID;
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
	
	
}
