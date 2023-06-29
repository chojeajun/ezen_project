package com.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
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
	
	
	
}
