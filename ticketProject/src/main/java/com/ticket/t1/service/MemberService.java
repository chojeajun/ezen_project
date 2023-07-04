package com.ticket.t1.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IMemberDao;
import com.ticket.t1.dto.MemberVO;

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

//	public void updateMember(HashMap<String, Object> paramMap) {
//		mdao.updateMember(paramMap);
//		
//	}

	public void updateMember(MemberVO membervo) {
		mdao.updateMember2(membervo);
	}



}
