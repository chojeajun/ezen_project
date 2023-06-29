package com.ticket.t1.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.ISuccessDao;

@Service
public class SuccessService {

	@Autowired
	ISuccessDao sucdao;
	
	public void getSuccessListBySusseq(HashMap<String, Object> paramMap) {
		sucdao.getSuccessListBySusseq( paramMap );
	}
	
}
