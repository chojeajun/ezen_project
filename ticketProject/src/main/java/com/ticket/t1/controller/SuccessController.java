package com.ticket.t1.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
		//System.out.println("로그인유저 떤냐?" + loginUser);
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
	public ModelAndView success_view(HttpServletRequest request, @RequestParam("sucseq") int sucseq,
					@RequestParam(value = "isTrue", required=false) String isTrue) {

		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			mav.setViewName("member/login");
		} else {
			
			HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("sucseq", sucseq);
			
			if(isTrue == null) ss.readCountOne(paramMap1);
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sucseq", sucseq);
			paramMap.put("ref_cursor1", null);
			paramMap.put("ref_cursor2", null);
			ss.getSuccessListBySucseq(paramMap);

			// getsuccessListBySucseq : 성공후기 번호로 조회해서 댓글 리스트, 내용 가져오기
			ArrayList<HashMap<String, Object>> list3 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
			ArrayList<HashMap<String, Object>> list4 = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");

			mav.addObject("SuccessVO", list3.get(0));
			mav.addObject("replyList", list4);
			
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
	
	@RequestMapping("/successWriteForm")
	public String success_write_form(Model model , HttpServletRequest request	) {
		
		HttpSession session = request.getSession();
		//MemberVO mvo = (MemberVO) session.getAttribute("loginUser"); 없음
		// 존재하지 않는 세션값을 가지고오니 null 이 아닌 오류뜸
		//System.out.println("mvo 에서 받아온 로그인유저 세션" +  mvo);
		
		HashMap<String, Object> loginUser = (HashMap<String , Object>) session.getAttribute("loginUser");
		System.out.println("hashmap 으로 받아온 로그인유저 세션" +  loginUser);
		
		if(loginUser == null) {
			return "member/login";
		} else {
			return "success/successWriteForm";
		}	
	}
	
	@RequestMapping("/s_selectimg")
	public String selectimg() {
		return "success/selectimg";
	}
	
	@Autowired
	ServletContext context;
	// 파일 업로드
	@RequestMapping(value="/s_fileupload" , method = RequestMethod.POST)
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
	
	// 리뷰 작성
	@RequestMapping( value="/successWrite", method=RequestMethod.POST)
	public String success_write_form( 
			@ModelAttribute("dto") @Valid SuccessVO successvo, BindingResult result,
			Model model, HttpServletRequest request ) {

		String url = "success/successWriteForm";
		if( result.getFieldError("pwd") != null ) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		}else if( result.getFieldError("title")!=null) {
			model.addAttribute("message", result.getFieldError("title").getDefaultMessage() );
		}else if( result.getFieldError("content")!=null)
			model.addAttribute("message", result.getFieldError("content").getDefaultMessage() );
		else {
			System.out.println("성공후기 작성 ㄱㄱ");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mseq", successvo.getMseq());
			paramMap.put("id", successvo.getId());
			paramMap.put("pwd", successvo.getPwd());
			paramMap.put("content", successvo.getContent());
			paramMap.put("title", successvo.getTitle());
			System.out.println("들어간 것들" + paramMap);
			if(successvo.getImgfilename() == null) paramMap.put("imgfilename", "");
			else paramMap.put("imgfilename", successvo.getImgfilename());
			ss.insertSuccess(paramMap);
			url = "redirect:/successList";
		}
		return url;
	}
	
	
	@RequestMapping("/successEditForm")
	public String success_edit_form(
			@RequestParam("sucseq") String sucseq, Model model, HttpServletRequest request) {
		model.addAttribute("sucseq", sucseq);
		// System.out.println("왔냐?" + rseq);
		return "success/successCheckPassForm";
	}
	
    // 성공 후기 수정	
	@RequestMapping("/successEdit")
	public String success_edit(
			@RequestParam("sucseq") int sucseq, 
			@RequestParam("pwd") String pwd,
			Model model, HttpServletRequest request) {
		
		//System.out.println("ㅇㅇㅇ"+rseq);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sucseq", sucseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		ss.getSuccessWithoutCount(paramMap);
		
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		HashMap<String, Object> svo = list.get(0);
		
		model.addAttribute("sucseq", sucseq);
		if(pwd.equals(svo.get("PWD").toString())) {
			return "success/successCheckPass";
		}else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다. 확인해주세요");
			return "success/successCheckPassForm";
		}

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
			
			//System.out.println("받았냐 말았냐 딱대" +  loginUser.get("ID"));
			ss.insertSuccessReply(svo);
			
			return "redirect:/successView?sucseq=" + svo.getSucseq() + "&isTrue=1";
		}
		
	}
	
	
}
