package com.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminQnaReplyVO {
	
	private int qrseq;
	private int qseq;
	private String id;
	private String qnaContent;
	private Timestamp writeDate;


	

}
