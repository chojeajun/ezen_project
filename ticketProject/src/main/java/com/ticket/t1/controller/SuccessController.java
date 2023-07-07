package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.SuccessReplyVO;
import com.ticket.t1.dto.SuccessVO;
import com.ticket.t1.service.SuccessService;
import com.ticket.t1.util.Paging;

@Controller
public class SuccessController {

	@Autowired
	SuccessService ss;

	@RequestMapping("/successList")
	public ModelAndView success_list(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		//MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		ModelAndView mav = new ModelAndView();
		System.out.println("로그인유저 떤냐?" + loginUser);
		if(loginUser == null) {
			mav.setViewName("member/login");
		} else {
			HashMap<String, Object> result =  ss.getSuccessList( request );

			mav.addObject("successList", (List<SuccessVO>)result.get("successList"));
			mav.addObject("paging", (Paging)result.get("paging"));
			mav.setViewName("success/successList");
			
//			nav.addObject("successList", ss.listSuccess());
		}
		
		return mav;
	}
	
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
		SuccessReplyVO svo
//		@RequestParam("sucseq") String sucseq,
//		@RequestParam("reply") String reply
		) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
//		System.out.println(svo.getId());
//		System.out.println(svo.getMseq());
		System.out.println("svo 내용은 ? ; " + svo);
		svo.setReplycontent("successcontent");
		if(loginUser == null) {
			return "member/login";
		}else {
			
			System.out.println("받았냐 말았냐 딱대" +  loginUser.get("ID"));
			ss.insertSuccessReply(svo);
			
			return "redirect:/successView?sucseq=" + svo.getSucseq() + "&isTrue=1";
		}
		
	}
	
	
}
