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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.AdminQnaReplyVO;
import com.ticket.t1.dto.SuccessVO;
import com.ticket.t1.service.AdminService;
import com.ticket.t1.service.ContentService;
import com.ticket.t1.service.QnaService;
import com.ticket.t1.service.ReviewService;
import com.ticket.t1.service.SuccessService;
import com.ticket.t1.util.Paging;


@Controller
public class AdminController {

	@Autowired
	AdminService as;
	
	@Autowired
	ReviewService res;
	
	@Autowired
	SuccessService ss;
	
	@RequestMapping(value="/admin")
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	@RequestMapping(value="/adminLogin")
	public String adminLogin( HttpServletRequest request, Model model, 
			@RequestParam("workId") String workId, 
			@RequestParam("workPwd") String workPwd) {	
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workId", workId);
		paramMap.put( "ref_cursor", null );
		as.getAdmin(paramMap);	 // 아이디로 관리자 조회
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		String url = "admin/adminLoginForm";
		if(list.size() == 0) {  // 입력한 아이디 없다면
			model.addAttribute("message" , "아이디가 없어요");
			return "admin/adminLoginForm";
		}
		HashMap<String, Object> resultMap = list.get(0); 
		if(resultMap.get("PWD")==null) model.addAttribute("message" , "관리자에게 문의하세요");
		else if( !workPwd.equals( (String) resultMap.get("PWD") ) )
			model.addAttribute("message" , "비번이 안맞아요");
		else if( workPwd.equals( (String) resultMap.get("PWD") ) ) { 
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", resultMap);
			url = "redirect:/contentList";
		}
		return url;
	}
	
	
	@RequestMapping(value="/adminLogout")
	public String adminLogout( HttpServletRequest request ) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginAdmin" );
		
		return "admin/adminLoginForm";
	}
	
	
	
	@RequestMapping(value="/contentList")
	public ModelAndView content_list(HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if( session.getAttribute("loginAdmin")==null) 
			mav.setViewName("admin/adminLoginForm");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			paramMap.put( "ref_cursor", null );
			as.getContentList( paramMap );
			
			ArrayList< HashMap<String,Object> > list 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("contentList", list);
			mav.addObject("paging", paramMap.get("paging") );
			mav.addObject("key", paramMap.get("key") );
			mav.setViewName("admin/content/contentList");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/contentWriteForm")
	public String content_write_form( HttpServletRequest request, Model model) {
		String categoryList[] = { "1", "2", "3", "4", "5" };
		model.addAttribute("categoryList", categoryList );
		return "admin/content/contentWriteForm";
	}
	
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="fileup", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> fileup( 
			@RequestParam("fileimage") MultipartFile file,
			HttpServletRequest request, Model model	) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String path = context.getRealPath("/content_images");	
		Calendar today = Calendar.getInstance();
 		long t = today.getTimeInMillis();
 		String filename = file.getOriginalFilename(); // 파일이름 추출
 		String fn1 = filename.substring(0, filename.indexOf(".") );  // 파일이름과 확장장 분리
 		String fn2 = filename.substring(filename.indexOf(".")+1 );
 		
 		if (!file.isEmpty()) {   // 업로드할 파일이 존재한다면
            String uploadPath = path + "/" + fn1 + t +  "." + fn2;
            System.out.println("파일 저장 경로 = " + uploadPath);
            try {
				file.transferTo( new File(uploadPath) );
			} catch (IllegalStateException e) { e.printStackTrace();
			} catch (IOException e) { e.printStackTrace();
			}
 		}
		result.put("STATUS", 1);
		result.put("FILENAME", fn1 + t +  "." + fn2 );
		return result;
	}
	
	@RequestMapping(value="/contentWrite" , method = RequestMethod.POST)
	public String contentWrite(	Model model ,  HttpServletRequest request ) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", request.getParameter("title") );
		paramMap.put("category", request.getParameter("category") );
		paramMap.put("content", request.getParameter("content") );
		paramMap.put("age", request.getParameter("age") );
		paramMap.put("artist", request.getParameter("artist") );
		
		if( request.getParameter("image") == null )
			paramMap.put("image", "" );
		else 
			paramMap.put("image", request.getParameter("image") );
		
		as.insertContent( paramMap);
		return "redirect:/contentList";
	}
	
	@Autowired
	ContentService ps;
	
	@RequestMapping("/adminContentDetail")
	public ModelAndView adminContentDetail( HttpServletRequest request,
			@RequestParam("cseq") int cseq ) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginAdmin")==null) 
			mav.setViewName("admin/adminLoginForm");
		else {
			HashMap<String , Object>paramMap = new HashMap<String, Object>();
			paramMap.put("cseq", cseq);
			paramMap.put("ref_cursor", null);
			ps.getContent(paramMap);
			ArrayList< HashMap<String , Object> > list
				=(ArrayList< HashMap<String , Object> >) paramMap.get("ref_cursor");
			HashMap<String , Object> cvo = list.get(0);
			//String categoryList[] = { "1", "2", "3", "4", "5" };
			//mav.addObject("kind", categoryList[ Integer.parseInt( cvo.get("CATEGORY").toString() ) ] );
			mav.addObject("contentVO", cvo);
			mav.setViewName("admin/content/contentDetail");
		}
		return mav;
	}
	
	@RequestMapping("contentUpdateForm")
	public ModelAndView contentUpdateForm( HttpServletRequest request,
			@RequestParam("cseq") String cseq ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		if( session.getAttribute("loginAdmin")==null) 
			mav.setViewName("admin/adminLoginForm");
		else {
			HashMap<String , Object>paramMap = new HashMap<String, Object>();
			paramMap.put("cseq", cseq);
			paramMap.put("ref_cursor", null);
			ps.getContent(paramMap);
			ArrayList< HashMap<String , Object> > list
				=(ArrayList< HashMap<String , Object> >) paramMap.get("ref_cursor");
			//HashMap<String , Object> cvo = list.get(0);
			String categoryList[] = { "1", "2", "3", "4", "5" };
			mav.addObject("categoryList", categoryList );
			//mav.addObject("dto", cvo);
			mav.setViewName("admin/content/contentUpdate");
		}
		return mav;
	}
	
	@RequestMapping(value="/contentUpdate", method=RequestMethod.POST)
	public String contentUpdate( HttpServletRequest request , Model model) {
		HashMap<String , Object>paramMap = new HashMap<String, Object>();
		
		paramMap.put("cseq", request.getParameter("cseq") );
		paramMap.put("title", request.getParameter("title") );
		paramMap.put("content", request.getParameter("content") );
		
		if( request.getParameter("useyn") == null)
			paramMap.put("useryn", "N" );
		else
			paramMap.put("useryn", "Y" );
		
		if( request.getParameter("bestyn") == null)
			paramMap.put("bestyn", "N" );
		else
			paramMap.put("bestyn", "Y" );
		
		paramMap.put("image", request.getParameter("oldfilename") );
		model.addAttribute("dto" , paramMap);
		model.addAttribute("newImage", request.getParameter("image"));
		
		
		if( request.getParameter("title") == null || request.getParameter("title").equals("") )
			return "admin/content/contentUpdate";
		else if( request.getParameter("content") == null || request.getParameter("content").equals("") )
			return "admin/content/contentUpdate";
		else { 
			if( request.getParameter("image")!=null || !request.getParameter("image").equals("") )
				paramMap.put("image", request.getParameter("image") );
			
			as.updateContent( paramMap );
			return "redirect:/adminContentDetail?pseq=" + request.getParameter("cseq"); 
		}
		
		
	}
	
	
	
	
	
	@RequestMapping("/memberList")
	public ModelAndView memberList( HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginAdmin")==null) 
			mav.setViewName("admin/adminLoginForm");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			as.getMemberList( paramMap );
			mav.addObject("memberList",  
					(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor")  );
			mav.addObject("paging", (Paging)paramMap.get("paging") );
			mav.addObject("key", (String)paramMap.get("key") );
			mav.setViewName("admin/member/memberList");
		}
		
		return mav;
	}
	
	
	@RequestMapping("/memberReinsert")
	public String memberReinsert( @RequestParam("id") String id, 
			@RequestParam("useyn") String useyn	) {
		
		if( useyn.equals("Y") ) useyn="N";
		else useyn="Y";
		System.out.println("useyn=============================" + useyn);
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("useyn", useyn);
		
		as.memberReinsert( paramMap );
		return "redirect:/memberList";
	}
	
	
	
	@RequestMapping("/adminOrderList")
	public ModelAndView adminOrderList( HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginAdmin")==null) 
			mav.setViewName("admin/adminLoginForm");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			as.getOrderList( paramMap );
			mav.addObject("orderList",  
					(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor")  );
			mav.addObject("paging", (Paging)paramMap.get("paging") );
			mav.addObject("key", (String)paramMap.get("key") );
			mav.setViewName("admin/order/orderList");
		}
		
		return mav;
	}
	
	
	@RequestMapping("/orderUpdateResult")
	public String orderUpdateResult( @RequestParam("result") int [] results  ) {
		
		// 전달된 results 속에 담긴 odseq 들을 하나씩 검색해서 result 값을 다음값으로 변경
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("results", results);
		as.updateOrderResult( paramMap );
		
		return "redirect:/adminOrderList";
	}
	
	
	
	
	
	@RequestMapping("/adminQnaList")
	public ModelAndView adminQnaList( HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if( session.getAttribute("loginAdmin")==null) 
			mav.setViewName("admin/adminLoginForm");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			as.getQnaList( paramMap );
			mav.addObject("qnaList",  
					(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor")  );
			mav.addObject("paging", (Paging)paramMap.get("paging") );
			mav.addObject("key", (String)paramMap.get("key") );
			mav.setViewName("admin/qna/qnaList");
		}
		
		
		return mav;
	}
	
	
	@Autowired
	QnaService qs;
	
	@RequestMapping("/adminQnaView")
	public ModelAndView adminQnaView( @RequestParam("qseq") int qseq   	) {
		System.out.println(qseq);
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		qs.getQna(paramMap);
		ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		System.out.println(qseq);
		HashMap<String, Object> qvo = list.get(0);
		mav.addObject("qnaVO", qvo );
		mav.setViewName("admin/qna/qnaView");
		return mav;
		
		
	}
	
	
	
	
	@RequestMapping(value="/adminQnaRepSave", method=RequestMethod.POST)

	public String adminQnaRepSave( @RequestParam("qseq") int qseq,
								@RequestParam("reply") String reply ) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		paramMap.put("reply", reply);
		as.updateQna( paramMap );
		return "redirect:/adminQnaView?qseq=" + qseq;
	}
	
	
	
	@RequestMapping("/adminBannerList")
	public ModelAndView bannerList( ) {
		ModelAndView mav = new ModelAndView();
	
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor", null);
		as.getBannerList(paramMap);
		ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		mav.addObject("bannerList",  list);
		mav.setViewName("admin/banner/bannerList");
		
		return mav;
	}
	
	
	
	
	@RequestMapping("/newBannerWrite")
	public String newBannerWrite() {
		return "admin/banner/writeBanner";
	}
	
	
	@RequestMapping(value="/bannerWrite" )
	public String bannerWrite( HttpServletRequest request	) {		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subject", request.getParameter("subject") );
		paramMap.put("order_seq", request.getParameter("order_seq") );
		paramMap.put("image", request.getParameter("image") );
		
		if( request.getParameter("order_seq").equals("6") ) paramMap.put("useyn", "N" );
		else paramMap.put("useyn", "Y" );
		
		as.insertBanner( paramMap );
		return "redirect:/adminBannerList";
	}
	
	@RequestMapping("/addReply1")
	public String addReply( AdminQnaReplyVO qvo, HttpServletRequest request ) {
		System.out.println(qvo.getId());
		System.out.println(qvo.getQseq());
		as.insertReply( qvo );
		
		return "redirect:/reviewViewWithoutCount?qseq=" + qvo.getQseq();
	}
	
	@RequestMapping("/reviewViewWithoutCount1")
	public ModelAndView reviewViewWithoutCount( @RequestParam("qseq") int qseq , 
			HttpServletRequest request, Model model ) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qseq", qseq);
		paramMap.put("ref_cursor1", null);
		paramMap.put("ref_cursor2", null);
		
		as.getReviewWithoutCount( paramMap );
		
		ArrayList<HashMap<String, Object>> list1
	 		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
		
		ArrayList<HashMap<String, Object>> list2
 			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
		
		mav.addObject("QnaVO" , list1.get(0) );
		mav.addObject("replyList", list2 );
		mav.setViewName("qna/qnaView");	
		
		return mav;
		
	}
	
	
	@RequestMapping("/chane_order")
	public String chane_order(
			HttpServletRequest request,
			@RequestParam("bseq") int bseq,
			@RequestParam("changeval") int changeval  	) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bseq", bseq );	
		String useyn;
		if( changeval > 5) paramMap.put("useyn", "N" );
		else paramMap.put("useyn", "Y" );
		paramMap.put("changeval", changeval );
		
		as.updateSeq( paramMap );
		
		return "redirect:/adminBannerList";
	}
	

	
	@RequestMapping("/reviewList1")
	public ModelAndView review_list(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin")==null)
			mav.setViewName("admin/adminLoginForm");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("request", request);
			paramMap.put("ref_cursor", null);
			
			res.selectReview(paramMap);
			
			ArrayList<HashMap<String, Object>>list
							=(ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			mav.addObject("reviewList", list);
			mav.addObject("paging",(Paging)paramMap.get("paging"));
			mav.setViewName("admin/review/reviewList");
		}
		return mav;
	}
	
	@RequestMapping("/reviewView1")
	public ModelAndView reviewView( @RequestParam("rseq") int rseq,  
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
		HashMap<String, Object> mvo = (HashMap<String, Object>)session.getAttribute("loginAdmin");
		System.out.println(mvo);
		mav.addObject("reviewVO" , list1.get(0) );
		mav.addObject("replyList", list2 );
		mav.setViewName("admin/review/reviewView");		
		
		return mav;
	}
	
	@RequestMapping("/successList1")
	public ModelAndView success_list(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		//MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		HashMap<String, Object> loginAdmin = (HashMap<String, Object>) session.getAttribute("loginAdmin");
		
		ModelAndView mav = new ModelAndView();
		//System.out.println("로그인유저 떤냐?" + loginUser);
		if(loginAdmin == null) {
			mav.setViewName("admin/adminLoginForm");
		} else {
			HashMap<String, Object> result =  ss.getSuccessList( request );

			mav.addObject("successList", (List<SuccessVO>)result.get("successList"));
			mav.addObject("paging", (Paging)result.get("paging"));
			mav.setViewName("admin/success/successList");
			
//			nav.addObject("successList", ss.listSuccess());
		}
		
		return mav;
	}
	
	@RequestMapping("/successView1")
	public ModelAndView success_view(HttpServletRequest request, @RequestParam("sucseq") int sucseq,
					@RequestParam(value = "isTrue", required=false) String isTrue) {

		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/adminLoginForm");
		} else {
			
			HashMap<String, Object> loginAdmin = (HashMap<String, Object>) session.getAttribute("loginAdmin");
			
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
			
			

			paramMap1.put("ref_cursor", null);
			ss.getReplyList(paramMap1);
			ArrayList<HashMap<String, Object>> list1 
				= (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
			
			
			ss.getReplyMember( paramMap1 );
			ArrayList<HashMap<String, Object>> list2 
				= (ArrayList<HashMap<String, Object>>) paramMap1.get("ref_cursor");
			System.out.println("list2===========================" + list2);
			
			mav.addObject("replyList", list1);
			mav.addObject("loginAdmin", loginAdmin);
			mav.addObject("replyInsertId", list2);
			mav.setViewName("admin/success/successView");
			
		}
		return mav;
  }
  
	@RequestMapping("/adminQnaDelete")
	public String admin_qna_delete( HttpServletRequest request,
			@RequestParam("qseq") int qseq) {
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginAdmin") == null ) {
			return "admin/adminLoginForm";
		}else {
			as.deleteAdminQnaRep(qseq);
			return "redirect:/adminQnaView?qseq=" + qseq;
		}

	}
	
	@RequestMapping("/successReplyDelete1")
	public String success_reply_delete(@RequestParam("srseq") int srseq,
				@RequestParam("sucseq") int sucseq) {
		
		ss.replyDelete(srseq);
		
		return "redirect:/successView1?sucseq=" + sucseq + "&isTrue='Yes'";
		
	}
	
	@RequestMapping("/reviewdeleteReply1")
	public String review_delete_reply(@RequestParam("repseq") int repseq,
				@RequestParam("rseq") int rseq) {
		
		HashMap<String, Object> HashMap = new HashMap<String, Object>();
		HashMap.put("repseq", repseq);
		res.deleteReply(HashMap);
		
		return "redirect:/reviewView1?rseq=" + rseq;
		
	}
	
	@RequestMapping("/deleteReviewList1")
	public String delete_review_list(@RequestParam(value="rseq") String rseqStr, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin") == null) {
			return "admin/adminLoginForm";
		}else {
			int rseq = Integer.parseInt(rseqStr);
			res.deleteReview(rseq);
			return "redirect:/reviewList1";
		}
		
	}
	
	@RequestMapping("/deleteSuccess1")
	public String delete_Success(@RequestParam("sucseq") String sucseqStr, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin") == null) {
			return "admin/adminLoginForm";
		}else {
			int sucseq = Integer.parseInt(sucseqStr);
			ss.deleteSuccess(sucseq);
			return "redirect:/successList1";
		}
	}
	
	@RequestMapping("/deleteContent1")
	public String delete_content(@RequestParam("cseq") String cseqStr, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin") == null) {
			return "admin/adminLoginForm";
		}else {
			int cseq = Integer.parseInt(cseqStr);
			as.deleteContent(cseq);
			return "redirect:/contentList";
		}
	}
	

	
}


