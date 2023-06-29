package com.ticket.t1.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.t1.dto.BannerVO;
import com.ticket.t1.dto.ContentVO;
import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.OrderVO;
import com.ticket.t1.dto.QnaVO;
import com.ticket.t1.service.AdminService;
import com.ticket.t1.service.ContentService;
import com.ticket.t1.service.QnaService;
import com.ticket.t1.util.Paging;


@Controller
public class AdminController {

	@Autowired
	AdminService as;
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	
	@RequestMapping("adminLogin")
	public ModelAndView admin_login(
						@RequestParam(value="workId", required=false) String workId,
						@RequestParam(value="workPwd", required=false) String workPwd, 
						HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		if( workId == null ) {
			mav.addObject("msg" , "아이디를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}else if( workId.equals("") ) {
			mav.addObject("msg" , "아이디를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}else if( workPwd == null) {
			mav.addObject("msg" , "패쓰워드를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}else if( workPwd.equals("")) {
			mav.addObject("msg" , "패쓰워드를 입력하세요");
			mav.setViewName("admin/adminLoginForm");
			return mav;
		}
		
		int result = as.workerCheck( workId, workPwd );
		
		if(result == 1) {
	    		HttpSession session = request.getSession();
	    		session.setAttribute("workId", workId);
	    		mav.setViewName("redirect:/productList");
		} else if (result == 0) {
	        	mav.addObject("message", "비밀번호를 확인하세요.");
	        	mav.setViewName("admin/adminLoginForm");
		} else if (result == -1) {
	    		mav.addObject("message", "아이디를 확인하세요.");
	    		mav.setViewName("admin/adminLoginForm");
		}	
		
		return mav;
	}
	
	@RequestMapping("/contentList")
	public ModelAndView product_list( HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			HashMap<String, Object> result =  as.getContentList( request );
			mav.addObject("productList",  (List<ContentVO>)result.get("contentList")  );
			mav.addObject("paging", (Paging)result.get("paging") );
			mav.addObject("key", (String)result.get("key") );
			mav.setViewName("admin/product/productList");
		}
		return mav;
	}
	

	@RequestMapping(value="/contentWriteForm")
	   public String product_write_form( HttpServletRequest request, Model model) {
	      String kindList[] = { "Heels", "Boots", "Sandals", "Shcakers", "Slipers",  "Sale" };
	      model.addAttribute("kindList", kindList);
	      return "admin/content/contentWriteForm";
	 }
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="fileup", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> fileup(Model model, HttpServletRequest request){
		String path=context.getRealPath("/content_images");
		HashMap<String, Object>result=new HashMap<String, Object>();
		
		try {
			MultipartRequest multi=new MultipartRequest(request,path,5*1024*1024,"UTF-8", new DefaultFileRenamePolicy());
			result.put("STATUS",1);
			result.put("FILENAME", multi.getFilesystemName("fileimage"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/contentWrite", method=RequestMethod.POST)
	public String contentWrite(@ModelAttribute("dto") @Valid ContentVO contentvo, BindingResult result,
			Model model, HttpServletRequest request) {

		String url="admin/content/contentWriteForm";
	    if(result.getFieldError("name")!=null)
	         model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
	      else if(result.getFieldError("price2")!=null)
	         model.addAttribute("message", result.getFieldError("price2").getDefaultMessage());
	      else if(result.getFieldError("content")!=null)
	         model.addAttribute("message", result.getFieldError("content").getDefaultMessage());
	      else if(result.getFieldError("image")!=null)
	         model.addAttribute("message", result.getFieldError("image").getDefaultMessage());
	      else {
	         as.insertContent(contentvo);
	         url="redirect:/contentList";
	      }

		return url;
	}
	
	@Autowired
	ContentService ps;

	@RequestMapping("adminContentDetail")
	public ModelAndView product_detail(
			HttpServletRequest request, @RequestParam("pseq") int pseq) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("contentVO",ps.getContent(pseq));
		
		String kindList[]= {"0","Heels", "Boots", "Sandals", "Slippers", "Sneakers",  "Sale"};
		int index=Integer.parseInt(ps.getContent(pseq).getcategory());
		
		mav.addObject("kind",kindList[index]);
		mav.setViewName("admin/content/contentDetail");
		
		return mav;
	}
	
	@RequestMapping("contentUpdateForm")
	public ModelAndView content_update_form(
			Model model, HttpServletRequest request, @RequestParam("pseq") int pseq) {
		ModelAndView mav=new ModelAndView();
		model.addAttribute("contentVO",ps.getContent(pseq));
		String kindList[]= {"0","Heels", "Boots", "Sandals", "Slippers", "Sneakers",  "Sale"};

		mav.addObject("kindList",kindList);
		mav.setViewName("admin/content/contentUpdate");
		
		return mav;
	}
	
	@RequestMapping(value="contentUpdate",method=RequestMethod.POST)
	public String productUpdate(@ModelAttribute("dto") @Valid ContentVO contentvo, BindingResult result,
								Model model, HttpServletRequest request) {
		
		String url="admin/content/contentUpdate";
	    if(result.getFieldError("name")!=null)
	         model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
	      else if(result.getFieldError("price2")!=null)
	         model.addAttribute("message", result.getFieldError("price2").getDefaultMessage());
	      else if(result.getFieldError("content")!=null)
	         model.addAttribute("message", result.getFieldError("content").getDefaultMessage());
	      else if(result.getFieldError("image")!=null)
	         model.addAttribute("message", result.getFieldError("image").getDefaultMessage());
	      else {
	    	  
	    	  if(request.getParameter("useyn")!= null) {
	    		  contentvo.setUseyn("Y");
	    	  }else contentvo.setUseyn("N");
	    	  
	    	  if(request.getParameter("bestyn")!=null) {
	    		  contentvo.setBestyn("Y");
	    	  }else contentvo.setBestyn("N");
	    	  
	    	  if(contentvo.getImage()==null||contentvo.getImage().equals("")) {
	    		  contentvo.setImage(request.getParameter("oldfilename"));
	    	  }
	         as.updateContent(contentvo);
	         url="redirect:/admincontentDetail?cseq="+contentvo.getCseq();
	      }
		return url;
	}
	
	@RequestMapping("/adminOrderList")
	public ModelAndView adminOrderList(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			HashMap<String, Object> result =  as.getOrderList( request );
			mav.addObject("orderList",  (List<OrderVO>)result.get("orderList")  );
			mav.addObject("paging", (Paging)result.get("paging") );
			mav.addObject("key", (String)result.get("key") );
			mav.setViewName("admin/order/orderList");
		}
		
		return mav;
	}
	
	@RequestMapping("/memberList")
	public ModelAndView memberList(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			HashMap<String, Object> result =  as.getMemberList( request );
			mav.addObject("memberList",  (List<MemberVO>)result.get("memberList")  );
			mav.addObject("paging", (Paging)result.get("paging") );
			mav.addObject("key", (String)result.get("key") );
			mav.setViewName("admin/member/memberList");
		}
		return mav;
	}
	
	@RequestMapping("/adminQnaList")
	public ModelAndView adminQnaList(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			HashMap<String, Object> result =  as.getQnaList( request );
			mav.addObject("qnaList",  (List<QnaVO>)result.get("qnaList")  );
			mav.addObject("paging", (Paging)result.get("paging") );
			mav.addObject("key", (String)result.get("key") );
			mav.setViewName("admin/qna/qnaList");
		}
		
		return mav;
	}
	
	@RequestMapping("/orderUpdateResult")
	public String orderUpdateResult(@RequestParam("result") int [] results){
		
		as.updateOrderResult(results);
		
		return "redirect:/adminOrderList";
	}

	@RequestMapping("/memberReinsert")
	public String memberReinsert(@RequestParam("id") String id, @RequestParam("useyn") String useyn) {
		if(useyn.equals("Y")) useyn="N";
		else useyn="Y";
		
		as.memberReinsert(id,useyn);
		return "redirect:/memberList";
	}
	

	@Autowired
	QnaService qs;
	
	@RequestMapping("/adminQnaView")
	public ModelAndView adminQnaView(HttpServletRequest request, @RequestParam("qseq") int qseq) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("qnaVO",qs.getQna(qseq));
		mav.setViewName("admin/qna/qnaView");
		return mav;
	}


	@RequestMapping(value="/adminQnaRepSave", method=RequestMethod.POST)
	public String adminQnaRepSave(@RequestParam("qseq") int qseq, @RequestParam("reply") String reply) {
		System.out.println(1);
		as.updateQna(qseq,reply);
		return "redirect:/adminQnaView?qseq="+qseq;
	}
	
	
	@RequestMapping(value="/adminBannerList")
	public ModelAndView bannerList() {
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("bannerList",as.getBannerList());
		mav.setViewName("admin/banner/bannerList");
		
		return mav;
	}
	
	
	@RequestMapping("/newBannerWrite")
	public String newBannerWrite() {
		return "admin/banner/writeBanner";
	}
	
	
	@RequestMapping(value="/bannerWrite")
	public String bannerWrite(BannerVO bannervo) {
		if(bannervo.getOrder_seq()==6) bannervo.setUseyn("N");
		else bannervo.setUseyn("N");
		as.insertBanner(bannervo);
		return "admin/banner/bannerList";
	}
	
	
	
	
}
