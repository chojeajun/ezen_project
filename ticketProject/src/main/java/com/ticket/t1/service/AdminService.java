package com.ticket.t1.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IAdminDao;
import com.ticket.t1.dto.AdminQnaReplyVO;
import com.ticket.t1.util.Paging;



@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public void getAdmin(HashMap<String, Object> paramMap) {
		adao.getAdmin( paramMap );		
	}

	public void getContentList(HashMap<String, Object> paramMap) {
		
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		HttpSession session = request.getSession();
		
		if( request.getParameter("first")!=null ) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}
		
		int page=1;
		if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		}else if( session.getAttribute("page") != null ) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		String key = "";
		if( request.getParameter("key") != null ) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if( session.getAttribute("key")!= null ) {
			key = (String)session.getAttribute("key");
		} else {
			session.removeAttribute("key");
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 0);  // OUT 변수용
		cntMap.put("tableName" , 1 );
		cntMap.put("key", key);
		adao.adminGetAllCount(cntMap);
		
		int count = Integer.parseInt( cntMap.get("cnt").toString() );
		paging.setTotalCount(count);
		paging.paging();
		
		paramMap.put("startNum", paging.getStartNum());
		paramMap.put("endNum", paging.getEndNum());
		paramMap.put("key", key);
		
		adao.getContentList(paramMap);
		paramMap.put("paging", paging);
	}

	

	public void getMemberList(HashMap<String, Object> paramMap) {
		
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		HttpSession session = request.getSession();
		
		if( request.getParameter("first")!=null ) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}
		
		int page = 1;
		if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if( session.getAttribute("page")!= null ) {
			page = (int) session.getAttribute("page");
		} else {
			page = 1;
			session.removeAttribute("page");
		}
		String key = "";
		if( request.getParameter("key") != null ) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if( session.getAttribute("key")!= null ) {
			key = (String)session.getAttribute("key");
		} else {
			session.removeAttribute("key");
			key = "";
		} 
		Paging paging = new Paging();
		paging.setPage(page);
		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 1);  // OUT 변수용
		cntMap.put("tableName" , 1 );
		cntMap.put("key", key);
		adao.adminGetAllCount(cntMap);
		
		int count = Integer.parseInt( cntMap.get("cnt").toString() );
		paging.setTotalCount(count);
		paging.paging();
		
		paramMap.put("startNum", paging.getStartNum());
		paramMap.put("endNum", paging.getEndNum());
		paramMap.put("key", key);
		
		adao.getMemberList(paramMap);
		
		paramMap.put("paging", paging);
		
	}

	public void memberReinsert(HashMap<String, Object> paramMap) {
		adao.memberReinsert( paramMap );
	}

	public void getOrderList(HashMap<String, Object> paramMap) {
		
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		HttpSession session = request.getSession();
		if( request.getParameter("first")!=null ) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}
		int page = 1;
		if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if( session.getAttribute("page")!= null ) {
			page = (int) session.getAttribute("page");
		} else {
			page = 1;
			session.removeAttribute("page");
		}
		String key = "";
		if( request.getParameter("key") != null ) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if( session.getAttribute("key")!= null ) {
			key = (String)session.getAttribute("key");
		} else {
			session.removeAttribute("key");
			key = "";
		} 
		Paging paging = new Paging();
		paging.setPage(page);
		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 1);  // OUT 변수용
		cntMap.put("tableName" , 1 );
		cntMap.put("key", key);
		adao.adminGetAllCount(cntMap);
		
		int count = Integer.parseInt( cntMap.get("cnt").toString() );
		paging.setTotalCount(count);
		paging.paging();
		System.out.print(count);
		
		paramMap.put("startNum", paging.getStartNum());
		paramMap.put("endNum", paging.getEndNum());
		paramMap.put("key", key);
		
		adao.getOrderList(paramMap);
		
		paramMap.put("paging", paging);
		
	}

	public void updateOrderResult(HashMap<String, Object> paramMap) {
		
		int [] results = (int []) paramMap.get("results");
		for( int odseq : results) {
			paramMap.put("odseq", odseq);
			adao.updateOrderResult(paramMap);
		}
			
		
	}

	public void getQnaList(HashMap<String, Object> paramMap) {
		
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		HttpSession session = request.getSession();
		if( request.getParameter("first")!=null ) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}
		int page = 1;
		if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if( session.getAttribute("page")!= null ) {
			page = (int) session.getAttribute("page");
		} else {
			page = 1;
			session.removeAttribute("page");
		}
		String key = "";
		if( request.getParameter("key") != null ) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if( session.getAttribute("key")!= null ) {
			key = (String)session.getAttribute("key");
		} else {
			session.removeAttribute("key");
			key = "";
		} 
		Paging paging = new Paging();
		paging.setPage(page);
		HashMap<String, Object> cntMap = new HashMap<String, Object>();
		cntMap.put("cnt", 0); 
		cntMap.put("tableName" ,1 );
		cntMap.put("key", key);
		adao.adminGetAllCount(cntMap);
		
		int count = Integer.parseInt( cntMap.get("cnt").toString() );
		paging.setTotalCount(count);
		paging.paging();
		
		paramMap.put("startNum", paging.getStartNum());
		paramMap.put("endNum", paging.getEndNum());
		paramMap.put("key", key);
		adao.getQnaList(paramMap);
		
		paramMap.put("paging", paging);
	
		
	}

	public void getBannerList(HashMap<String, Object> paramMap) {
		adao.getBannerList( paramMap );
		
	}

	public void insertBanner(HashMap<String, Object> paramMap) {
		adao.insertBanner(paramMap);
		
	}

	public void updateSeq(HashMap<String, Object> paramMap) {
		adao.updateSeq( paramMap);		
	}

	public void insertContent(HashMap<String, Object> paramMap) {
		adao.insertContent(paramMap);	
		
	}

	public void updateContent(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}


	public void updateQna(HashMap<String, Object> paramMap) {
		adao.updateQna( paramMap );
		
	}



	public void insertReply(AdminQnaReplyVO qvo) {
		// TODO Auto-generated method stub
		
	}

	public void getReviewWithoutCount(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}

	
}
