package com.ezen.ticket.dto;

import java.sql.Timestamp;

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
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Integer getQseq() {
		return qseq;
	}
	public void setQseq(Integer qseq) {
		this.qseq = qseq;
	}
	public Integer getMseq() {
		return mseq;
	}
	public void setMseq(Integer mseq) {
		this.mseq = mseq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRepyn() {
		return repyn;
	}
	public void setRepyn(String repyn) {
		this.repyn = repyn;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
}
