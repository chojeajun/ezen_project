package com.ticket.t1.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Content_Time_View_VO {

	private int cseq;
	private String title;
	private Timestamp contentdate;
	private String contenttime;

	
}
