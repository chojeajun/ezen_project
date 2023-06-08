package com.ezen.ticket.dto;

public class Content_Time_View_VO {

	private int cseq;
	private String title;
	private String contentdate;
	private String contenttime;

	public String getContenttime() {
		return contenttime;
	}

	public void setContenttime(String contenttime) {
		this.contenttime = contenttime;
	}

	public int getCseq() {
		return cseq;
	}

	public void setCseq(int cseq) {
		this.cseq = cseq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentdate() {
		return contentdate;
	}

	public void setContentdate(String contentdate) {
		this.contentdate = contentdate;
	}

}
