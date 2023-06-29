package com.dto;

public class RegisterTimeVO {
	
	private int rtseq;
	private int mseq;

	private String registerdate;
	private String starttime;
	private String endtime;
	
	private String cid;
	private String cnickname;
	private int grade;
	private String gname;
	private int success;
	private int com_price;
	
	
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCnickname() {
		return cnickname;
	}
	public void setCnickname(String cnickname) {
		this.cnickname = cnickname;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getCom_price() {
		return com_price;
	}
	public void setCom_price(int com_price) {
		this.com_price = com_price;
	}
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
