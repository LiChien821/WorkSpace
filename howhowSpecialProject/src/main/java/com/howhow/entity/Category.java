package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoryform")
@Component
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
	@Column(name = "name", length = 40, nullable = false, unique = true)
	private String name;
	
	@Column(name = "descriptior", length = 200)
	private String descriptior;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<CourseBasic> courseBasicList =new ArrayList<CourseBasic>() ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptior() {
		return descriptior;
	}

	public void setDescriptior(String descriptior) {
		this.descriptior = descriptior;
	}

	public List<CourseBasic> getCourseBasicList() {
		return courseBasicList;
	}

	public void setCourseBasicList(List<CourseBasic> courseBasicList) {
		this.courseBasicList = courseBasicList;
	}

	

	
}
