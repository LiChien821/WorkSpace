package com.howhow.shopping.dto;

public class CourseBasicDTO {
	
	private int courseid;
	private String coursename;
	private long price;
	private int discountprice;
	private double rank;
	private int creatorid;
	private int categoryid;
	private double discount;
	private int coursestatusid;
	private String cover;
	private String description;
	private String creatorname;
	private boolean favoritecoursestatus;
	private boolean shoppingCartStatus;
	private boolean purchasedStatus;
	private int studentnum;
	private int ranknum;
	
	
	public CourseBasicDTO() {
	}
	
	public CourseBasicDTO(int courseid, String coursename, long price, int discountprice,
			double rank, String creatorname, int categoryid, int coursestatusid, String cover,
			String description, int creatorid, int studentnum, int ranknum) {
		this.courseid = courseid;
		this.coursename = coursename;
		this.price = price;
		this.discountprice = discountprice;
		this.rank = rank;
		this.creatorname=creatorname;
		this.categoryid=categoryid;
		this.coursestatusid=coursestatusid;
		this.cover=cover;
		this.description=description;
		this.creatorid=creatorid;
		this.studentnum=studentnum;
		this.ranknum=ranknum;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getDiscountprice() {
		return discountprice;
	}

	public void setDiscountprice(int discountprice) {
		this.discountprice = discountprice;
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}

	public int getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(int creatorid) {
		this.creatorid = creatorid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getCoursestatusid() {
		return coursestatusid;
	}

	public void setCoursestatusid(int coursestatusid) {
		this.coursestatusid = coursestatusid;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public boolean isFavoritecoursestatus() {
		return favoritecoursestatus;
	}

	public void setFavoritecoursestatus(boolean favoritecoursestatus) {
		this.favoritecoursestatus = favoritecoursestatus;
	}

	public boolean isShoppingCartStatus() {
		return shoppingCartStatus;
	}

	public void setShoppingCartStatus(boolean shoppingCartStatus) {
		this.shoppingCartStatus = shoppingCartStatus;
	}

	public boolean isPurchasedStatus() {
		return purchasedStatus;
	}

	public void setPurchasedStatus(boolean purchasedStatus) {
		this.purchasedStatus = purchasedStatus;
	}

	public int getStudentnum() {
		return studentnum;
	}

	public void setStudentnum(int studentnum) {
		this.studentnum = studentnum;
	}

	public int getRanknum() {
		return ranknum;
	}

	public void setRanknum(int ranknum) {
		this.ranknum = ranknum;
	}
	
	
	

	
}
