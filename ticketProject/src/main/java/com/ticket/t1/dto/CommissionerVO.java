package com.ticket.t1.dto;

import lombok.Data;

@Data
public class CommissionerVO {
	private int mseq;
	private String cid;
	private String cnickname;
	private int grade;
	private String gname;
	private int success;
	private String registerdate;
	private String starttime;
	private String endtime;
	private int com_price;
	
}
