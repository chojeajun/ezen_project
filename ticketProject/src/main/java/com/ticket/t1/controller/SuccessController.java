package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.service.SuccessService;

@Controller
public class SuccessController {

	@Autowired
	SuccessService ss;

	@RequestMapping("/successView")
	public ModelAndView success_view(HttpServletRequest request, @RequestParam("sucseq") int seq,
					@RequestParam(value = "isTrue", required=false) String isTrue) {

		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			mav.setViewName("member/login");
		} else {
			
			HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("sucseq", seq);
			
			if(isTrue == null) ss.readCountOne(paramMap1);
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sucseq", seq);
			paramMap.put("ref_cursor", null);

			ss.getSuccessListBySucseq(paramMap);

			ArrayList<HashMap<String, Object>> list 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

			mav.addObject("SuccessVO", list.get(0));

			ss.getReplyList(paramMap);

			ArrayList<HashMap<String, Object>> list1 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			ss.getReplyMember( paramMap );
			ArrayList<HashMap<String, Object>> list2 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("replyList", list1);
			mav.addObject("loginUser", loginUser);
			mav.addObject("replyInsertId", list2);
			mav.setViewName("success/successView");
			
		}

		return mav;

	}
	
	@RequestMapping("/successReplyDelete")
	public String success_reply_delete(@RequestParam("srseq") int srseq,
				@RequestParam("sucseq") int sucseq) {
		
		ss.replyDelete(srseq);
		
		return "redirect:/successView?sucseq=" + sucseq + "&isTrue='Yes'";
		
	}
	
	@RequestMapping("/replyInsert")
	public String reply_insert( HttpServletRequest request,  
		@RequestParam("sucseq") String seq,
		@RequestParam("reply") String reply) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "member/login";
		}else {
			
			ss.insertReply(Integer.parseInt(seq), reply, (String)loginUser.get("NICKNAME"));
			
			return "redirect:/successView?sucseq=" + seq + "&isTrue='yes'";
		}
		
	}
}
