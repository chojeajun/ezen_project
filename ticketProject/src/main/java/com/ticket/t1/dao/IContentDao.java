package com.ticket.t1.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.BannerVO;
import com.ticket.t1.dto.ContentVO;

@Mapper
public interface IContentDao {

	List<ContentVO> getNewList();

	List<ContentVO> getBestList();

	List<ContentVO> getKindList(String kind);

	void getContent(HashMap<String, Object> paramMap);

	List<BannerVO> getBannerList();

	void getBestNewSuccessBannerList(HashMap<String, Object> paramMap);
	void getContentTimeList(HashMap<String, Object> paramMap);
	void getAllContent(HashMap<String, Object> paramMap);
	void getConcert(HashMap<String, Object> paramMap);
	void getMusical(HashMap<String, Object> paramMap);
	void getSports(HashMap<String, Object> paramMap);
	void getFestival(HashMap<String, Object> paramMap);
	void getExhibition(HashMap<String, Object> paramMap);
	void searchContentByTitle(HashMap<String, Object> paramMap);


}
