package com.ticket.t1.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IContentDao;
import com.ticket.t1.dto.BannerVO;
import com.ticket.t1.dto.ContentVO;


@Service
public class ContentService {

	@Autowired
	IContentDao cdao;

	public HashMap<String, Object> getBestNewList() {
		HashMap<String, Object> result=new HashMap<String, Object>();
		
		List<ContentVO> newList=cdao.getNewList();
		List<ContentVO> bestList=cdao.getBestList();
		
		result.put("newContentList", newList);
		result.put("bestContentList", bestList);
		
		return result;
	}

	public List<ContentVO> getKindList(String kind) {
		return cdao.getKindList(kind);
	}



	public List<BannerVO> getBannerList() {
		return cdao.getBannerList();
	}


	public void getBestNewSuccessBannerList(HashMap<String, Object> paramMap) {
		cdao.getBestNewSuccessBannerList( paramMap );
	}

	public void getContentTimeList(HashMap<String, Object> paramMap) {
		cdao.getContentTimeList( paramMap );
	}

	public void getAllContent(HashMap<String, Object> paramMap) {
		cdao.getAllContent( paramMap );
	}

	public void getConcert(HashMap<String, Object> paramMap) {
		cdao.getConcert( paramMap );
	}

	public void getMusical(HashMap<String, Object> paramMap) {
		cdao.getMusical( paramMap );
	}

	public void getSports(HashMap<String, Object> paramMap) {
		cdao.getSports( paramMap );
	}

	public void getFestival(HashMap<String, Object> paramMap) {
		cdao.getFestival( paramMap );
	}

	public void getExhibition(HashMap<String, Object> paramMap) {
		cdao.getExhibition( paramMap );
	}


	public void getContent(HashMap<String, Object> paramMap) {
		cdao.getContent(paramMap);
	}
	public void searchContentByTitle(HashMap<String, Object> paramMap) {
		cdao.searchContentByTitle(paramMap);

		
	}
	
}
