package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.ReviewReplyVO;
import com.ticket.t1.dto.ReviewVO;
import com.ticket.t1.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	ReviewService res;
	
	@RequestMapping("/reviewList")
	public ModelAndView review_list(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
		= (HashMap<String, Object>) session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			List<ReviewVO> listReview = res.listReview();
			mav.addObject("review", listReview);
			System.out.println(listReview.size());
			mav.setViewName("review/reviewList");
		}
		return mav;
	}
	
	@RequestMapping("/reviewView")
	public ModelAndView reviewView(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
		= (HashMap<String, Object>) session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			res.plusOneReadcount();
			ArrayList<ReviewReplyVO> list = res.selectReply();
			request.setAttribute("replyList", list); /// 댓글 리스트 긁어와서 뿌려
		}
		return mav;
	}
}
