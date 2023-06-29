package com.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SuccessReplyVO {
	
	private int sucseq;  // 성공후기 번호 
	private int srseq; // 댓글번호
	private int mseq; // 멤버번호 
	private String id;
	private String replycontent;
	private String successcontent;
	private Timestamp writedate;
	
	
	
}
