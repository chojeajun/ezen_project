package com.dto;

import lombok.Data;

@Data
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
	
	
	
	
}
