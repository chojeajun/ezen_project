package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView boardView( @RequestParam("rseq") int rseq,  
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rseq", rseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		res.getReview( paramMap );
		
		ArrayList<HashMap<String, Object>> list1
	 		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		
		ArrayList<HashMap<String, Object>> list2
 			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		
		mav.addObject("reviewVO" , list1.get(0) );
		mav.addObject("replyList", list2 );
		mav.setViewName("review/reviewView");		
		
		return mav;
	}
	
	@RequestMapping("/addReply")
	public String addReply( 
			@RequestParam("rseq") int rseq, 
			@RequestParam("mseq") int mseq,
			@RequestParam("content") String replycontent,
			HttpServletRequest request ) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mseq", mseq);
		paramMap.put("content", replycontent);
		paramMap.put("rseq", rseq);
		
		res.insertReply( paramMap );
		
		return "redirect:/boardViewWithoutCount?num=" + rseq;
	}
}
















