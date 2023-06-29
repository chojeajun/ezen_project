


import java.util.HashMap;
import java.util.List;

package com.ticket.t1.service;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dao.IContentDao;
import com.dto.ContentVO;

import com.ticket.t1.dao.IContentDao;


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

	public ContentVO getContent(int pseq) {
		return cdao.getContent(pseq);
	}

	public List<BannerVO> getBannerList() {
		return cdao.getBannerList();
	}

	public void getBestNewSuccessBannerList(HashMap<String, Object> paramMap) {
		cdao.getBestNewSuccessBannerList( paramMap );
	}
	
	

}
