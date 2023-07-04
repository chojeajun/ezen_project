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
		RegisterTimeVO rtvo = new RegisterTimeVO();
		rtvo.setMseq(mseq);
		rtvo.setRegisterdate(string);
		rtvo.setStarttime(string2);
		rtvo.setEndtime(string3);
		rdao.insertRegisterTime(rtvo);
		
	}

	public ArrayList<RegisterTimeVO> getMyRegister(int mseq) {
		return rdao.getMyRegister(mseq);
	}

	
}
