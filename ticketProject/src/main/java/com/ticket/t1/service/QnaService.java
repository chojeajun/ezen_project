package com.ticket.t1.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IQnaDao;
import com.ticket.t1.dto.QnaVO;


@Service
public class QnaService {

	@Autowired
	IQnaDao qdao;

	public void listQna(HashMap<String, Object> paramMap) {
		qdao.listQna(paramMap);		
	}

	public void getQna(HashMap<String, Object> paramMap) {
		qdao.getQna( paramMap );		
	}

	public void insertQna(HashMap<String, Object> paramMap) {
		qdao.insertQna( paramMap);
	}

	
}
