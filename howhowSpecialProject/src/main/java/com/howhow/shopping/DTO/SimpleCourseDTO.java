package com.howhow.shopping.DTO;

public class SimpleCourseDTO {
	
	private int id;
	private int courseID;
	private int userID;
	private String courseName;
	private long price;
	private int discountPrice;
	private double rank;
	
	
	public SimpleCourseDTO(int id, int courseID, String courseName, long price, int discountPrice,
			double rank) {
		super();
		this.id=id;
		this.courseID = courseID;
		this.courseName = courseName;
		this.price = price;
		this.discountPrice = discountPrice;
		this.rank = rank;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public double getRank() {
		return rank;
	}
	public void setRank(double rank) {
		this.rank = rank;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
}
