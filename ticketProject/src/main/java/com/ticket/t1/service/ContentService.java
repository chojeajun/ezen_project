package com.ticket.t1.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IContentDao;

@Service
public class ContentService {

	@Autowired
	IContentDao cdao;

	public void getBestNewSuccessBannerList(HashMap<String, Object> paramMap) {
		cdao.getBestNewSuccessBannerList( paramMap );
	}
	
	
}
