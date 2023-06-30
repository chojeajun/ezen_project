package com.ticket.t1.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberVO {
	
	private int mseq;
	@NotEmpty(message="id를 입력하세요")
	private String id;
	@NotEmpty(message="id를 입력하세요")
	private String pwd;
	@NotEmpty(message="id를 입력하세요")
	private String name;
	private String nickname;
	private int gender;
	@NotEmpty(message="id를 입력하세요")
	private String email;
	private String phone;
	private String birth;
	private String zip_num;
	private String address1;
	private String address2;
	private String address3;
	private String grade;
	private int success;
	private Timestamp indate;
	private char useyn;
	private String cnickname;
	private String provider;
	
	

}
