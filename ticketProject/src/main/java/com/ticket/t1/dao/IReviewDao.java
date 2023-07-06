package com.ticket.t1.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.ReviewReplyVO;
import com.ticket.t1.dto.ReviewVO;
import com.ticket.t1.util.Paging;

@Mapper
public interface IReviewDao {

	int getAllCount(String string, String string2);
	List<ReviewVO> listReview(Paging paging);
	void getAllCount(HashMap<String, Object> paramMap);
	void selectReview(HashMap<String, Object> paramMap);
	void plusOneReadCount(HashMap<String, Object> paramMap);
	void getReview(HashMap<String, Object> paramMap);
	void insertReply(ReviewReplyVO rvo);
	void getReviewWithoutCount(HashMap<String, Object> paramMap);
	void deleteReply(HashMap<String, Object> paramMap);
	void insertReview(HashMap<String, Object> paramMap);
	void updateReview(HashMap<String, Object> paramMap);
	void removeReview(HashMap<String, Object> paramMap);

	
}
