package com.ticket.t1.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
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
			mav.setViewName("member/login");
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
		
		HttpSession session = request.getSession();
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginUser");
		System.out.println(mvo);
		mav.addObject("reviewVO" , list1.get(0) );
		mav.addObject("replyList", list2 );
		mav.setViewName("review/reviewView");		
		
		return mav;
	}
	
	@RequestMapping("/addReply")
	public String addReply( ReviewReplyVO rvo, HttpServletRequest request ) {
		System.out.println(rvo.getId());
		System.out.println(rvo.getRseq());
		res.insertReply( rvo );
		
		return "redirect:/reviewViewWithoutCount?rseq=" + rvo.getRseq();
	}
	
	@RequestMapping("/reviewViewWithoutCount")
	public ModelAndView reviewViewWithoutCount( @RequestParam("rseq") int rseq , 
			HttpServletRequest request, Model model ) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rseq", rseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		res.getReviewWithoutCount( paramMap );
		
		ArrayList<HashMap<String, Object>> list1
	 		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		
		ArrayList<HashMap<String, Object>> list2
 			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		
		mav.addObject("reviewVO" , list1.get(0) );
		mav.addObject("replyList", list2 );
		mav.setViewName("review/reviewView");	
		
		return mav;
		
	}
	
	@RequestMapping("/deleteReply")
	public String reply_delete( 
			@RequestParam("repseq") int repseq, 
			@RequestParam("rseq") int rseq,
			HttpServletRequest request	) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("repseq" , repseq);
		res.deleteReply( paramMap );
		
		return "redirect:/reviewViewWithoutCount?rseq=" + rseq;
	}
	
	@RequestMapping("/reviewWriteForm")
	public String write_form(HttpServletRequest request) {
		
		String url = "review/reviewWriteForm";
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)
			url="member/loginForm";		
		
		return url;
	}
	
	@RequestMapping("/selectimg")
	public String selectimg() {
		return "review/selectimg";
	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/fileupload" , method = RequestMethod.POST)
	public String fileupload(
				@RequestParam("image") MultipartFile file, 
				Model model, HttpServletRequest request) {
		
		String path = context.getRealPath("/upload");
		//System.out.println("path = " + path);
		//System.out.println("multipartFile = " + file);
		
		Calendar today = Calendar.getInstance();
		long t = today.getTimeInMillis();
		String filename = file.getOriginalFilename();// 파일 이름 추출
		String fn1 = filename.substring(0, filename.indexOf("."));	//파일이름과 확장자 분리
		String fn2 = filename.substring(filename.indexOf(".")+1);
		
		if(!file.isEmpty()) {		//업로드할 파일이 존재한다면
			String uploadPath = path + "/" + fn1 + t + "." + fn2;
			System.out.println("파일 저장 경로 = " + uploadPath);
			try {
				file.transferTo(new File(uploadPath));
			} catch (IllegalStateException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			}
		}
		model.addAttribute("image", fn1 + t + "." + fn2);
		return "review/completupload";
	}
	
	@RequestMapping( value="/reviewWrite", method=RequestMethod.POST)
	public String review_write( 
			@ModelAttribute("dto") @Valid ReviewVO reviewvo, BindingResult result,
			Model model, HttpServletRequest request ) {
		
		String url = "review/reviewWriteForm";
		if( result.getFieldError("pwd") != null ) 
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else if( result.getFieldError("title")!=null)
			model.addAttribute("message", result.getFieldError("title").getDefaultMessage() );
		else if( result.getFieldError("content")!=null)
			model.addAttribute("message", result.getFieldError("content").getDefaultMessage() );
		else {
			System.out.println("reviewWrite 시작");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mseq", reviewvo.getMseq());
			paramMap.put("id", reviewvo.getId());
			paramMap.put("pwd", reviewvo.getPwd());
			paramMap.put("content", reviewvo.getContent());
			paramMap.put("title", reviewvo.getTitle());
			if(reviewvo.getImgfilename() == null) paramMap.put("imgfilename", "");
			else paramMap.put("imgfilename", reviewvo.getImgfilename());
			res.insertReview(paramMap);
			url = "redirect:/reviewList";
		}
		return url;
	}
	
	@RequestMapping("/reviewEditForm")
	public String review_edit_form(
			@RequestParam("rseq") String rseq, Model model, HttpServletRequest request) {
		model.addAttribute("rseq", rseq);
		System.out.println("왔냐?" + rseq);
		return "review/reviewCheckPassForm";
	}
	
	@RequestMapping("/reviewEdit")
	public String review_edit(
			@RequestParam("rseq") int rseq, 
			@RequestParam("pwd") String pwd,
			Model model, HttpServletRequest request) {
		
		System.out.println("ㅇㅇㅇ"+rseq);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rseq", rseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		res.getReviewWithoutCount(paramMap);
		
		ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		
		HashMap<String, Object> rvo = list.get(0);
		
		model.addAttribute("rseq", rseq);
		if(pwd.equals(rvo.get("PWD")))
			return "review/reviewCheckPass";
		else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다. 확인해주세요");
			return "review/reviewCheckPassForm";
		}
	}
	
	@RequestMapping("/reviewUpdateForm")
	public String review_update_form(  
			@RequestParam("rseq") int rseq, Model model,  HttpServletRequest request) {
		
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rseq", rseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		res.getReviewWithoutCount( paramMap );
		ArrayList< HashMap<String, Object> > list 
		=  (ArrayList< HashMap<String, Object> >)paramMap.get("ref_cursor1");
		HashMap<String, Object> rvo = list.get(0);
	
		//조회한 게시물을 dto 로 옮겨 담습니다
		ReviewVO dto = new ReviewVO();
		dto.setRseq( Integer.parseInt(String.valueOf(rvo.get("RSEQ") ) ) );
		dto.setPwd( (String)rvo.get("PASS") );
		dto.setId( (String)rvo.get("ID") );
		dto.setTitle( (String)rvo.get("TITLE") );
		dto.setContent( (String)rvo.get("CONTENT") );
		dto.setMseq( Integer.parseInt(String.valueOf(rvo.get("MSEQ"))));
		dto.setImgfilename( (String)rvo.get("IMGFILENAME") );
		model.addAttribute("rseq", rseq);
		model.addAttribute("dto", dto);
		return "review/reviewUpdateForm";
	}
	
	@RequestMapping(value="/reviewUpdate", method = RequestMethod.POST)
	public String reviewUpdate( 
			@ModelAttribute("dto") @Valid ReviewVO reviewvo,
			BindingResult result, 
			@RequestParam(value="oldfilename", required=false) String oldfilename, 
			HttpServletRequest request, Model model) {
		
		String url = "review/reviewUpdateForm";
		if(result.getFieldError("pwd")!=null)
			model.addAttribute("message", "비밀번호는 게시물 수정 삭제시 필요합니다" );
		else  if(result.getFieldError("title")!=null) 
			model.addAttribute("message" , "제목은 필수입력 사항입니다");
		else if(result.getFieldError("content")!=null) 
			model.addAttribute("message" , "게시물 내용은 비워둘수 없습니다");
		else {
			if( reviewvo.getImgfilename()==null || reviewvo.getImgfilename().equals("") )
				reviewvo.setImgfilename(oldfilename);
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("rseq", reviewvo.getRseq());
			paramMap.put("id", reviewvo.getId());
			paramMap.put("pwd", reviewvo.getPwd());
			paramMap.put("mseq", reviewvo.getMseq());
			paramMap.put("content", reviewvo.getContent());
			paramMap.put("title", reviewvo.getTitle());
			paramMap.put("imgfilename", reviewvo.getImgfilename());
			res.updateReview( paramMap );
			url = "redirect:/reviewViewWithoutCount?rseq=" + reviewvo.getRseq();
		}
		return url;
	}
	
	@RequestMapping("reviewDelete")
	public String review_delete_form(@RequestParam("rseq") int rseq,
			Model model, HttpServletRequest request) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rseq", rseq);
		res.removeReview(paramMap);	
		return "redirect:/reviewList";
	}
}
















