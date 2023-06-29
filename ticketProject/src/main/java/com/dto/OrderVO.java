package com.dto;

import java.sql.Timestamp;

public class OrderVO {
	private int oseq;
	private Timestamp oindate; //이건 주문내역 날짜
	private int mseq;
	
	private int odseq;
	private int cseq;
	private String image;
	private String title;
	private String artist;
	private int locationNum;
	private String locationName;
	private Timestamp contentDate;
	private String contentTime;
	private String area;
	private int content_price;
	private int mseq2;
	private String com_nickname;
	private String com_grade;
	private int com_price;
	private int quantity;
	private char result;
	private Timestamp odindate; //이건 주문상세내역 날짜
	private int totalPrice;
	private String orderTitle;
	
	
	
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getCom_nickname() {
		return com_nickname;
	}
	public void setCom_nickname(String com_nickname) {
		this.com_nickname = com_nickname;
	}
	public String getCom_grade() {
		return com_grade;
	}
	public void setCom_grade(String com_grade) {
		this.com_grade = com_grade;
	}
	public int getContent_price() {
		return content_price;
	}
	public void setContent_price(int content_price) {
		this.content_price = content_price;
	}
	public int getCom_price() {
		return com_price;
	}
	public void setCom_price(int com_price) {
		this.com_price = com_price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOseq() {
		return oseq;
	}
	public void setOseq(int oseq) {
		this.oseq = oseq;
	}
	public Timestamp getOindate() {
		return oindate;
	}
	public void setOindate(Timestamp oindate) {
		this.oindate = oindate;
	}
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
	}
	public int getOdseq() {
		return odseq;
	}
	public void setOdseq(int odseq) {
		this.odseq = odseq;
	}
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public int getLocationNum() {
		return locationNum;
	}
	public void setLocationNum(int locationNum) {
		this.locationNum = locationNum;
	}
	public Timestamp getContentDate() {
		return contentDate;
	}
	public void setContentDate(Timestamp contentDate) {
		this.contentDate = contentDate;
	}
	public String getContentTime() {
		return contentTime;
	}
	public void setContentTime(String contentTime) {
		this.contentTime = contentTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getMseq2() {
		return mseq2;
	}
	public void setMseq2(int mseq2) {
		this.mseq2 = mseq2;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public char getResult() {
		return result;
	}
	public void setResult(char result) {
		this.result = result;
	}
	public Timestamp getOdindate() {
		return odindate;
	}
	public void setOdindate(Timestamp odindate) {
		this.odindate = odindate;
	}
	
	
	
	
}
