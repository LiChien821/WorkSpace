package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name="paymethodtype")
@Component
public class PayMethodType {
	
	@Id @Column(name="PAYMETHODID")
	private int payMethodID;

	@Column(name="PAYMETHODNAME")
	private String payMethodName;
	
	@Column(name="SYSTEMTIME")
	private String systemTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payMethodtype", cascade = CascadeType.ALL)
	private List<OrderMt> orderMtList = new ArrayList<OrderMt>();

	public int getPayMethodID() {
		return payMethodID;
	}

	public void setPayMethodID(int payMethodID) {
		this.payMethodID = payMethodID;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
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
