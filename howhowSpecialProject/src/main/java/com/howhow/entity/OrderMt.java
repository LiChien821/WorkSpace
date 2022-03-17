package com.howhow.entity;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table(name="ordermt")
@Component
public class OrderMt {
	
	@Id @Column(name="OrderID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderID;
	
	@Column(name="OrderDate")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date orderDate;
	
	@Transient
	private int userID;
	
	@Column(name="TotalPrice")
	private int totalPrice;
	
	@Transient
	private int payMethodID;
	
	@Transient
	private int orderStatusID;
	
	@Column(name="SystemTime")
	private String systemTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMt", cascade = CascadeType.ALL)
	private Set<OrderDt> orderDts = new HashSet<OrderDt>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserID")
	private UserAccountMt userAccountMt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PayMethodID")
	private PayMethodType payMethodtype;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OrderStatusID")
	private OrderStatusType orderStatusType;
	
}
