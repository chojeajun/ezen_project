package com.ticket.t1.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.QnaVO;

@Mapper
public interface IQnaDao {

	List<QnaVO> listQns();
	QnaVO getQna(int qseq);
	void insertQna(QnaVO qnavo);

}
