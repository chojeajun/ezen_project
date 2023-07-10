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
	public String qnaCheckPass( 
			@RequestParam("qseq") int qseq, 
			@RequestParam("pass") String pass, Model model ) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		paramMap.put("ref_cursor", null);
		qs.getQna( paramMap );
		
		ArrayList< HashMap<String, Object> > list 
			= (ArrayList< HashMap<String, Object> >) paramMap.get("ref_cursor" );
		HashMap<String, Object> qvo = list.get(0);
		
		if( qvo.get("PASS").equals(pass) ) {
			model.addAttribute("qseq", qseq);
			return "qna/checkPassSuccess";
		}else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			return "qna/checkPass";
		}
			
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
			@RequestParam(value="check", required=false) String check,
			@RequestParam(value="pass", required=false) String pass, 
			//@RequestParam("subject") String subject,
			//@RequestParam("content") String content 
			@RequestParam(value="subject", required=false) String subject,
			@RequestParam(value="content", required=false) String content ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
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
				if( check == null ) {
					paramMap.put("check", "N");
					paramMap.put("pass", "");
				}else {
					paramMap.put("check", "Y");
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
	
	@RequestMapping("/qnaEditForm")
	public String qna_edit_form(
			@RequestParam("qseq") String qseq, Model model, HttpServletRequest request) {
		model.addAttribute("qseq", qseq);
		// System.out.println("왔냐?" + rseq);
		return "qna/qnaCheckPassForm";
	}
	
	@RequestMapping("/qnaEdit")
	public String qna_edit(
			@RequestParam("qseq") int qseq, 
			@RequestParam("pwd") String pwd,
			Model model, HttpServletRequest request) {
		
		//System.out.println("ㅇㅇㅇ"+rseq);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		
		HashMap<String, Object> rvo = list.get(0);
		
		model.addAttribute("qseq", qseq);
		if(pwd.equals(rvo.get("PWD")))
			return "qna/qnaCheckPass";
		else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다. 확인해주세요");
			return "qna/qnaCheckPassForm";
		}
	}
	
	@RequestMapping("/qnaUpdateForm")
	public String qna_update_form(  
			@RequestParam("qseq") int qseq, Model model,  HttpServletRequest request) {
		
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		ArrayList< HashMap<String, Object> > list 
		=  (ArrayList< HashMap<String, Object> >)paramMap.get("ref_cursor1");
		HashMap<String, Object> rvo = list.get(0);
	
		//조회한 게시물을 dto 로 옮겨 담습니다
		QnaVO dto = new QnaVO();
		dto.setQseq( Integer.parseInt(String.valueOf(rvo.get("QSEQ") ) ) );
		dto.setPwd( (String)rvo.get("PWD") );
		dto.setPwd( (String)rvo.get("PWD") );
		dto.setId( (String)rvo.get("ID") );
		dto.setTitle( (String)rvo.get("TITLE") );
		dto.setContent( (String)rvo.get("CONTENT") );
		dto.setMseq( Integer.parseInt(String.valueOf(rvo.get("MSEQ"))));
		model.addAttribute("qseq", qseq);
		model.addAttribute("dto", dto);
		return "qna/qnaUpdateForm";
	}
	
	@RequestMapping(value="/qnaUpdate", method = RequestMethod.POST)
	public String qnaUpdate( 
			@ModelAttribute("dto") @Valid QnaVO qnavo,
			BindingResult result,  
			HttpServletRequest request, Model model) {
		
		String url = "qna/qnaUpdateForm";
		if(result.getFieldError("pwd")!=null)
			model.addAttribute("message", "비밀번호는 게시물 수정 삭제시 필요합니다" );
		else  if(result.getFieldError("title")!=null) 
			model.addAttribute("message" , "제목은 필수입력 사항입니다");
		else if(result.getFieldError("content")!=null) 
			model.addAttribute("message" , "게시물 내용은 비워둘수 없습니다");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("qseq", qnavo.getQseq());
			paramMap.put("id", qnavo.getId());
			paramMap.put("pwd", qnavo.getPwd());
			paramMap.put("mseq", qnavo.getMseq());
			paramMap.put("title", qnavo.getTitle());
			paramMap.put("content", qnavo.getContent());
			qs.updateQna( paramMap );
			url = "redirect:/qnaView?qseq=" + qnavo.getQseq();
		}
		return url;
	}
	
	@RequestMapping("qnaDelete")
	public String review_delete_form(@RequestParam("qseq") int qseq,
			Model model, HttpServletRequest request) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		qs.removeQna(paramMap);	
		return "redirect:/qnaList";
	}
}

















