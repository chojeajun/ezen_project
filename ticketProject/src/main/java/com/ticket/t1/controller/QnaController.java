package com.ticket.t1.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.QnaVO;
import com.ticket.t1.dto.ReviewVO;
import com.ticket.t1.service.QnaService;
import com.ticket.t1.util.Paging;

@Controller
public class QnaController {

	@Autowired
	QnaService qs;
	
	@RequestMapping("/customer")
	public String customer() {
		return "qna/intro";
	}
	
	
	@RequestMapping(value="/qnaList")
	public ModelAndView qna_list( HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			paramMap.put("ref_curser", null);
			qs.listQna( paramMap );
			ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			mav.addObject("qnaList", list);
			mav.addObject("paging",(Paging)paramMap.get("paging"));
			mav.setViewName("qna/qnaList");
		}
		return mav;
	}
	
	@RequestMapping("/passCheck")
	public ModelAndView passCheck( @RequestParam("qseq") int qseq ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qseq", qseq);
		mav.setViewName("qna/checkPass");
		return mav;
	}
	
	@RequestMapping(value="/qnaCheckPass", method=RequestMethod.POST)
	public ModelAndView qnaCheckPass( 
			@RequestParam("qseq") int qseq, 
			@RequestParam("pass") String pass ) {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String , Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		paramMap.put("ref_cursor", null);
		qs.getQna( paramMap );
		
		ArrayList< HashMap<String, Object> > list 
			= (ArrayList< HashMap<String, Object> >)paramMap.get("ref_cursor");
		
		HashMap<String, Object> qvo = list.get(0);
		
		
		if( qvo.get("PASS").equals(pass) ){
			mav.addObject("qseq", qseq);
			mav.setViewName("qna/checkPassSuccess");
		}else {
			mav.addObject("message" , "비밀번호가 맞지 않습니다");
			mav.setViewName("qna/checkPass");
		}
		return mav;
		
	}
	
	
	@RequestMapping(value="/qnaView")
	public ModelAndView qna_view(	HttpServletRequest request,
			@RequestParam("qseq") int qseq	) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("qseq", qseq );
			paramMap.put("ref_cursor", null);
			qs.getQna( paramMap );
			
			ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			
			mav.addObject("qnaVO", list.get(0) );		
			mav.setViewName("qna/qnaView");
		}
		return mav;
	}
	
	
	@RequestMapping("/qnaWriteForm")
	public String qna_write_form(HttpServletRequest request) {
		
		String url = "qna/qnaWriteForm";
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)
			url="member/loginForm";		
		
		return url;
	}
	
	
	
	@RequestMapping("qnaWrite")
	public ModelAndView qna_write( HttpServletRequest request,
			@RequestParam(value="passCheck", required=false) String passCheck,
			@RequestParam(value="pass", required=false) String pass, 
			@RequestParam(value="subject", required=false) String subject,
			@RequestParam(value="content", required=false) String content ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		System.out.println("ddddd" + passCheck);
		HashMap<String, Object> loginUser 
			= (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) 
			mav.setViewName("member/login");
		else {
			
			if( subject == null || subject.equals("") )
				mav.setViewName("member/login");
			else if(content == null || content.equals("") )
				mav.setViewName("member/login");
			else {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				String id = (String)loginUser.get("ID");
				paramMap.put("id", id);
				if( passCheck == null ) {
					paramMap.put("passCheck", "N");
					paramMap.put("pass", "");
				}else {
					paramMap.put("passCheck", "Y");
					paramMap.put("pass", pass);
				}
				paramMap.put("subject", subject);
				paramMap.put("content", content);
				qs.insertQna( paramMap);
				mav.setViewName("redirect:/qnaList");
			}
		}
		return mav;
	}
	
	@RequestMapping("qnaDelete")
	public String qna_delete_form(@RequestParam("qseq") int qseq,
			Model model, HttpServletRequest request) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		qs.removeQna(paramMap);	
		return "redirect:/qnaList";
	}
}

















