package com.dto;

import java.sql.Timestamp;

public class ReviewReplyVO {

	private int repseq; // 댓글번호
	private int mseq; // member_sequence/ 번호
	private int rseq; // 리뷰번호
	private String id;
	private String replycontent;
	private Timestamp writedate;
	
	public int getRepseq() {
		return repseq;
	}
	public void setRepseq(int repseq) {
		this.repseq = repseq;
	}
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
	}
	public int getRseq() {
		return rseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
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
