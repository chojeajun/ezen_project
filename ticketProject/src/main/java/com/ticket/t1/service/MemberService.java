package com.ticket.t1.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IMemberDao;

@Service
public class MemberService {

	@Autowired
	IMemberDao mdao;


	public void getMember(HashMap<String, Object> paramMap) {

		mdao.getMember(paramMap);
	}

	public void joinKakao(HashMap<String, Object> paramMap) {
		mdao.joinKakao(paramMap);

	}

	public void insertMember(HashMap<String, Object> paramMap) {
		
		mdao.insertMember(paramMap);
		
	}

	public void getMyRegister(HashMap<String, Object> paramMap) {

		mdao.getMyRegister(paramMap);
		
	}



}
