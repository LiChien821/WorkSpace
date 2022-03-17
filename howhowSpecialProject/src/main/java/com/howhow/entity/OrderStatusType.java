package com.howhow.entity;

import java.util.HashSet;
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
	
	@Id @Column(name="OrderStatusID")
	private int orderStatusID;
	
	@Column(name="OrderStatusName")
	private String orderStatusName;
	
	@Column(name="SystemTime")
	private String systemTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderStatusType", cascade = CascadeType.ALL)
	private Set<OrderMt> orderMts = new HashSet<OrderMt>();
}
