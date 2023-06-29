package com.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
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
	
	

	
	
}
