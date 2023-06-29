

package com.ticket.t1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IContentDao {

	void getBestNewSuccessBannerList(HashMap<String, Object> paramMap);
	void getContentTimeList(HashMap<String, Object> paramMap);
	void getAllContent(HashMap<String, Object> paramMap);
	void getConcert(HashMap<String, Object> paramMap);
	void getMusical(HashMap<String, Object> paramMap);
	void getSports(HashMap<String, Object> paramMap);
	void getFestival(HashMap<String, Object> paramMap);
	void getExhibition(HashMap<String, Object> paramMap);


}
