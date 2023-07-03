package com.ticket.t1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.ReviewVO;

@Mapper
public interface IReviewDao {

	List<ReviewVO> listReview();

	
}
