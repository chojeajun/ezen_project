package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dto.ContentVO;

@Mapper
public interface IContentDao {

	List<ContentVO> getNewList();

	List<ContentVO> getBestList();

	List<ContentVO> getKindList(String kind);

	ContentVO getContent(int pseq);

	List<BannerVO> getBannerList();

}
