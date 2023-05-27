package com.ezen.ticket.dto;

public class RegisterTimeVO {
	
	private int rtseq;
	private int mseq;

	private String registerdate;
	private String starttime;
	private String endtime;
	
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;

	}
	public int getRtseq() {
		return rtseq;
	}
	public void setRtseq(int rtseq) {
		this.rtseq = rtseq;
	}
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
	}
	
}
