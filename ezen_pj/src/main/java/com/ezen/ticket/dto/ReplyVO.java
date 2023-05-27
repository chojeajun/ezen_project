package com.ezen.ticket.dto;

import java.sql.Timestamp;

public class ReplyVO {
	
	private int replynum;
	private int qnanum;
	private String userid;
	private Timestamp writedate;
	private String content;
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public int getQnanum() {
		return qnanum;
	}
	public void setQnanum(int qnanum) {
		this.qnanum = qnanum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
