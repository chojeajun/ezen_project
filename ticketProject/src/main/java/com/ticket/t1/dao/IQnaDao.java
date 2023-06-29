package com.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dto.QnaVO;

@Mapper
public interface IQnaDao {

	List<QnaVO> listQns();
	QnaVO getQna(int qseq);
	void insertQna(QnaVO qnavo);

}
