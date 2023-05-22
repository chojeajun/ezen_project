package com.ezen.ticket.dto;

import java.sql.Timestamp;

public class ContentVO {
	
	private int cseq;
	private String title;
	private int locationNum;
	private String artist;
	private String image;
	private String content;
	private int category;
	private String age;
	private char bestyn;
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLocationNum() {
		return locationNum;
	}
	public void setLocationNum(int locationNum) {
		this.locationNum = locationNum;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public char getBestyn() {
		return bestyn;
	}
	public void setBestyn(char bestyn) {
		this.bestyn = bestyn;
	}

	
	
}
