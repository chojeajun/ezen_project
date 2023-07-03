package com.ticket.t1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IReviewDao;
import com.ticket.t1.dto.ReviewVO;

@Service
public class ReviewService {

	@Autowired
	IReviewDao redao;

	public List<ReviewVO> listReview() {
		return redao.listReview();
	}
	
}
