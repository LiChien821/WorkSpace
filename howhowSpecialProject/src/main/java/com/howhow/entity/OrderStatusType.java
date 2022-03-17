package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name="orderstatustype")
@Component
public class OrderStatusType {
	
	@Id @Column(name="ORDERSTATUSID")
	private int orderStatusID;
	
	@Column(name="ORDERSTATUSNAME")
	private String orderStatusName;
	
	@Column(name="SYSTEMTIME")
	private String systemTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderStatusType", cascade = CascadeType.ALL)
	private List<OrderMt> orderMtList = new ArrayList<OrderMt>();

	public int getOrderStatusID() {
		return orderStatusID;
	}

	public void setOrderStatusID(int orderStatusID) {
		this.orderStatusID = orderStatusID;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public List<OrderMt> getOrderMtList() {
		return orderMtList;
	}

	public void setOrderMtList(List<OrderMt> orderMtList) {
		this.orderMtList = orderMtList;
	}
	
}
