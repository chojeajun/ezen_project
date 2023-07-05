package com.ticket.t1.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReviewVO {
	private int rseq;
	private int mseq;
	private String id;
	
	@NotEmpty(message="비밀번호를 입력하세요(게시글 수정 삭제시 필요합니다)")
	@NotNull(message="비밀번호를 입력하세요(게시글 수정 삭제시 필요합니다)")
	private String pwd;
	@NotEmpty(message="제목을 입력하세요")
	@NotNull(message="제목을 입력하세요")
	private String title;
	private String indate;
	@NotEmpty(message="내용을 입력하세요")
	@NotNull(message="내용을 입력하세요")
	private String content;
	private String reply;
	private String repyn;
	private String image;
	private int readcount;
	private String imgfilename;
	
	
	

	
}
