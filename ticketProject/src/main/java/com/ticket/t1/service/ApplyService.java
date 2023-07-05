package com.ticket.t1.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IApplyDao;
import com.ticket.t1.dto.Content_Time_View_VO;

@Service
public class ApplyService {

	@Autowired
	IApplyDao adao;

	public void selectContent(HashMap<String, Object> paramMap) {
		adao.selectContent(paramMap);
	}

	public void selectContentAll(HashMap<String, Object> paramMap) {
		adao.selectContentAll(paramMap);
	}

	public void selectFromContentByTitle(HashMap<String, Object> paramMap1) {
		adao.selectFromContentByTitle(paramMap1);
		
	}

	public void selectFromContentTimeByTitle(HashMap<String, Object> paramMap1) {
		adao.selectFromContentTimeByTitle(paramMap1);
		
	}

	public void selectFromLocationViewByTitle(HashMap<String, Object> paramMap1) {
		adao.selectFromLocationViewByTitle(paramMap1);
		
	}

	public void selectFromContentAreaByTitle(HashMap<String, Object> paramMap2) {
		adao.selectFromContentAreaByTitle(paramMap2);
		
	}

	public void selectTimeByDate(HashMap<String, Object> paramMap4) {
		adao.selectTimeByDate(paramMap4);
		
	}
	
	public List<Content_Time_View_VO> selectTimeByDate(int cseq, String contentDate) {
		
		return adao.selectTimeByDateMybatis(cseq, contentDate);
	}

}
