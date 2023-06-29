package com.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewReplyVO {

	private int repseq; // 댓글번호
	private int mseq; // member_sequence/ 번호
	private int rseq; // 리뷰번호
	private String id;
	private String replycontent;
	private Timestamp writedate;
	

}
