package com.ticket.t1.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	
	private int mseq;
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private int gender;
	private String email;
	private String phone;
	private String birth;
	private String zip_num;
	private String address1;
	private String address2;
	private String grade;
	private int success;
	private Timestamp indate;
	private char useyn;
	private String cnickname;
	
	
	

}
