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
import com.ticket.t1.util.Paging;

@Controller
public class ReviewController {

	@Autowired
	ReviewService res;
	
	@RequestMapping("/reviewList")
	public ModelAndView review_list(HttpServletRequest request) {
		
ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null)
			mav.setViewName("member/loginForm");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			paramMap.put("ref_cursor", null);
			
			res.selectReview(paramMap);
			
			ArrayList<HashMap<String, Object>>list
							=(ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			mav.addObject("reviewList", list);
			mav.addObject("paging",(Paging)paramMap.get("paging"));
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
//			res.plusOneReadcount();
//			ArrayList<ReviewReplyVO> list = res.selectReply();
//			request.setAttribute("replyList", list); /// 댓글 리스트 긁어와서 뿌려
		}
		return mav;
	}
}
