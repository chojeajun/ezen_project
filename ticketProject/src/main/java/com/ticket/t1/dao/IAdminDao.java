package com.ticket.t1.dao;


import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.BannerVO;
import com.ticket.t1.dto.ContentVO;
import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.OrderVO;
import com.ticket.t1.dto.QnaVO;
import com.ticket.t1.util.Paging;

@Mapper
public interface IAdminDao {
	

	void getAdmin(HashMap<String, Object> paramMap);
	void adminGetAllCount(HashMap<String, Object> cntMap);
	void getContentList(HashMap<String, Object> paramMap);
	void insertContent(HashMap<String, Object> paramMap);
	void updatecontent(HashMap<String, Object> paramMap);
	void getMemberList(HashMap<String, Object> paramMap);
	void memberReinsert(HashMap<String, Object> paramMap);
	void getOrderList(HashMap<String, Object> paramMap);
	void updateOrderResult(HashMap<String, Object> paramMap);
	void getQnaList(HashMap<String, Object> paramMap);
	void updateOna(HashMap<String, Object> paramMap);
	void getBannerList(HashMap<String, Object> paramMap);
	void insertBanner(HashMap<String, Object> paramMap);
	void updateSeq(HashMap<String, Object> paramMap);
	void updateQna(HashMap<String, Object> paramMap);
    





}

