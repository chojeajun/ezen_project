package com.ticket.t1.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.Content_Time_View_VO;

@Mapper
public interface IApplyDao {

	void selectContent(HashMap<String, Object> paramMap);

	void selectContentAll(HashMap<String, Object> paramMap);

	void selectFromContentByTitle(HashMap<String, Object> paramMap1);

	void selectFromContentTimeByTitle(HashMap<String, Object> paramMap1);

	void selectFromLocationViewByTitle(HashMap<String, Object> paramMap1);

	void selectFromContentAreaByTitle(HashMap<String, Object> paramMap2);

	void selectTimeByDate(HashMap<String, Object> paramMap4);

	List<Content_Time_View_VO> selectTimeByDateMybatis(int cseq, String contentDate);

	void insertCart(int mseq, int cseq, String date, String time, String area, int mseq2, int quantity, int locationNum);

	void selectContentForLocNum(HashMap<String, Object> paramMap);

	void insertCartNullMseq2(int mseq, int cseq, String date, String time, String area, int quantity, int locationNum);

	void hoonUpdateCart(int cartseq, int mseq2);

	void getCommissioner(HashMap<String, Object> paramMap1);

	void getComFinal(HashMap<String, Object> paramMap3);

	void selectAreaPrice(HashMap<String, Object> paramMap1);
	
}
