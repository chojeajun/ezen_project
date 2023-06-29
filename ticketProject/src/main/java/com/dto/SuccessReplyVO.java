package com.dto;

import java.sql.Timestamp;

public class SuccessReplyVO {
	
	private int sucseq;  // 성공후기 번호 
	private int srseq; // 댓글번호
	private int mseq; // 멤버번호 
	private String id;
	private String replycontent;
	private String successcontent;
	private Timestamp writedate;
	
	public String getSuccesscontent() {
		return successcontent;
	}
	public void setSuccesscontent(String successcontent) {
		this.successcontent = successcontent;
	}
	public int getSrseq() {
		return srseq;
	}
	public void setSrseq(int srseq) {
		this.srseq = srseq;
	}
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
	}
	public int getSucseq() {
		return sucseq;
	}
	public void setSucseq(int sucseq) {
		this.sucseq = sucseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	
}
