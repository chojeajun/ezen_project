package com.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QnaVO {
	
	private Integer qseq;
	private Integer mseq;
	private String subject;
	private String title;
	private String content;
	private String reply;
	private String id;
	private String pwd;
	private String repyn;
	private Timestamp indate;
	private String num;
	private String image;
	private int readcount;
	
	
	

	
	
	
}
