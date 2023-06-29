package com.dto;

import java.sql.Timestamp;

public class CartVO {
	
	private int cartseq;
	private int mseq;
	private int cseq;
	private Timestamp contentdate;
	private String contenttime;
	private int locationnum;
	private String area;
	private int mseq2;
	private int quantity;
	private Timestamp indate;
	private char buyyn;
	
	
	public int getCartseq() {
		return cartseq;
	}
	public void setCartseq(int cartseq) {
		this.cartseq = cartseq;
	}
	public int getMseq2() {
		return mseq2;
	}
	public void setMseq2(int mseq2) {
		this.mseq2 = mseq2;
	}
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
	}
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public Timestamp getContentdate() {
		return contentdate;
	}
	public void setContentdate(Timestamp contentdate) {
		this.contentdate = contentdate;
	}
	public String getContenttime() {
		return contenttime;
	}
	public void setContenttime(String contenttime) {
		this.contenttime = contenttime;
	}
	public int getLocationnum() {
		return locationnum;
	}
	public void setLocationnum(int locationnum) {
		this.locationnum = locationnum;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	public char getBuyyn() {
		return buyyn;
	}
	public void setBuyyn(char buyyn) {
		this.buyyn = buyyn;
	}
	
}
