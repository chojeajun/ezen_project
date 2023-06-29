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
	
	String getPwd(String workId);
	int getAllCount(String string, String string2, String key);
	List<ContentVO> listProduct(Paging paging, String key);
	void insertContent(@Valid ContentVO contentvo);
	void updateContent(ContentVO contentvo);
	List<OrderVO> listOrders(Paging paging, String key);
	List<MemberVO> listMember(Paging paging, String key);
	List<QnaVO> listQna(Paging paging, String key);
	void updateOrderResult(int odseq);
	void memberReinsert(String id, String useyn);
	void updateQna(int qseq, String reply);
	List<BannerVO> getBannerList();
	void insertBanner(BannerVO bannervo);
	void getAdmin(HashMap<String, Object> paramMap);
    





}

