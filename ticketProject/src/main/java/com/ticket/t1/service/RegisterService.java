package com.ticket.t1.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IRegisterDao;
import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.RegisterTimeVO;

@Service
public class RegisterService {

	@Autowired
	IRegisterDao rdao;
	
	public void insertRegisterTime(int mseq, String string, String string2, String string3) {
		rdao.insertRegisterTime(mseq, string, string2, string3);
		
	}

	public ArrayList<RegisterTimeVO> getMyRegister(MemberVO membervo) {
		return rdao.getMyRegister(membervo);
	}

	
}