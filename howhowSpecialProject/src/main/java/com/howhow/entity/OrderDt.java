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

import org.springframework.stereotype.Component;

@Entity @Table(name="orderdt")
@Component
public class OrderDt {
	
	@Id @Column(name="ORDERDTID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int OrderDtID;
	
	@Transient
	private int OrderID;
	
	@Transient
	private int CourseID;
	
	@Column(name="DISCOUNT")
	private double discount;
	
	@Column(name="UNITPRICE")
	private int unitPrice;
	
	@Column(name="SYSTEMTIME")
	private String systemTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDERID")
	private OrderMt orderMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSEID")
	private CourseBasic courseBasic;

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public int getOrderDtID() {
		return OrderDtID;
	}

	public void setOrderDtID(int orderDtID) {
		OrderDtID = orderDtID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getCourseID() {
		return CourseID;
	}

	public void setCourseID(int courseID) {
		CourseID = courseID;
	}

	public OrderMt getOrderMt() {
		return orderMt;
	}

	public void setOrderMt(OrderMt orderMt) {
		this.orderMt = orderMt;
	}

	public CourseBasic getCourseBasic() {
		return courseBasic;
	}

	public void setCourseBasic(CourseBasic courseBasic) {
		this.courseBasic = courseBasic;
	}
	
	
	
}
