package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IQnaDao;
import com.dto.QnaVO;


@Service
public class QnaService {

	@Autowired
	IQnaDao qdao;

	public List<QnaVO> listQna() {
		return qdao.listQns();
	}

	public QnaVO getQna(int qseq) {
		return qdao.getQna( qseq );
	}

	public void insertQna(QnaVO qnavo) {
		qdao.insertQna( qnavo);
	}
}
