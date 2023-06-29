package com.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ContentVO {
	
	private int cseq;
	private String title;
	private int locationNum;
	private String locationName;
	private String artist;
	private String area;
	private int price;
	private Timestamp contentDate;
	private String contentTime;
	private String image;
	private String content;
	private int category;
	private String age;
	private char useyn;
	private char bestyn;
	private String areaImage;
	private String tDateTime;
	
	
	
	
	

	
	
}
