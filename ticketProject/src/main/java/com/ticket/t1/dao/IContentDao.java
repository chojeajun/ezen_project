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

	ContentVO getContent(int pseq);

	List<BannerVO> getBannerList();

	void getBestNewSuccessBannerList(HashMap<String, Object> paramMap);


}
