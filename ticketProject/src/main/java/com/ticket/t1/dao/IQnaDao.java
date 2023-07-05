package com.ticket.t1.dao;


import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.QnaVO;

@Mapper
public interface IQnaDao {

	void listQna(HashMap<String, Object> paramMap);
	void getQna(HashMap<String, Object> paramMap);
	void insertQna(HashMap<String, Object> paramMap);
	void getAllCount(HashMap<String, Object> paramMap);

	/*
	List<QnaVO> listQns();
	QnaVO getQna(int qseq);
	void insertQna(QnaVO qnavo);
	*/
}
