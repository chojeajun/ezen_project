package com.dto;

import java.sql.Timestamp;

public class AdminQnaReplyVO {
	
	private int qrseq;
	private int qseq;
	private String id;
	private String qnaContent;
	private Timestamp writeDate;
	public int getQrseq() {
		return qrseq;
	}
	public void setQrseq(int qrseq) {
		this.qrseq = qrseq;
	}
	public int getQseq() {
		return qseq;
	}
	public void setQseq(int qseq) {
		this.qseq = qseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}

	

}
