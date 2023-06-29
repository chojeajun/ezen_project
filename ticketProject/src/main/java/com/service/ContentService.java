package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IContentDao;
import com.dto.ContentVO;

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
}
