package com.ticket.t1.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IAdminDao;
import com.ticket.t1.dao.IReviewDao;
import com.ticket.t1.dao.ISuccessDao;
import com.ticket.t1.dto.AdminQnaReplyVO;
import com.ticket.t1.dto.SuccessVO;
import com.ticket.t1.util.Paging;



@Service
public class AdminService {

	@Autowired
	IAdminDao adao;
	
	@Autowired
	IReviewDao redao;
	
	@Autowired
	ISuccessDao sucdao;

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


	public void selectReview(HashMap<String, Object> paramMap) {
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		HttpSession session = request.getSession();
		
		// 현재 페이지 설정
		int page = 1;
		if( request.getParameter("page")!=null) {
			page = Integer.parseInt( request.getParameter("page") );
			session.setAttribute("page", page);
		}else if( session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		// getAllCount 메서드를 이용한 총 게시물 갯수르 리턴
		//필요한건 게시물 갯수를 계산해서 나에게 담아 보내줄 OUT 변수가 필요합니다
		paramMap.put("cnt", 0);  
		redao.getAllCount( paramMap );
		//getAllCount 가 실행되고 나면 "cnt" 키값에 해당하는 밸류가 총 게시물 갯수가 됩니다
		int count = (Integer)paramMap.get("cnt");  
		
		// 페이징 객체 설정
		paging.setTotalCount(count);
		paging.paging();
		
		paramMap.put("startNum", paging.getStartNum() );  // 전달인수
		paramMap.put("endNum", paging.getEndNum() );  // 전달인수
		
		redao.selectReview( paramMap ); //결과가 ref_cursor에 담깁니다
		
		paramMap.put("paging", paging);  // 리턴될 인수
		
		/*
		for( BoardVO bvo : list) {
			int cnt = bdao.getReplyCount( bvo.getNum() );
			bvo.setReplycnt(cnt);
		}*/
		
	}

	public void getReview(HashMap<String, Object> paramMap) {
		adao.plusOneReadCount(paramMap);
		adao.getReview(paramMap);
		
	}

public HashMap<String, Object> getSuccessList(HttpServletRequest request) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		if(request.getParameter("first") != null) {
			session.removeAttribute("page");
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page") != null) {
			page = (int) session.getAttribute("page");
		} else {
			page =1;
			session.removeAttribute("page");
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		int count = sucdao.getAllCount("success_board");
		paging.setTotalCount(count);
		
		List<SuccessVO> successList = sucdao.listSuccess(paging);
		result.put("successList", successList);
		result.put("paging", paging);
		
		
		return result;
	}

	public void readCountOne(HashMap<String, Object> paramMap1) {
		adao.readCountOne( paramMap1 );
		
	}

	public void getSuccessListBySucseq(HashMap<String, Object> paramMap) {
		adao.getSuccessListBySucseq( paramMap );
		
	}

	public void getReplyList(HashMap<String, Object> paramMap) {
		adao.getReplyList( paramMap );
		
	}

	public void getReplyMember(HashMap<String, Object> paramMap) {
		adao.getReplyMember( paramMap );
	}

	public void deleteAdminQnaRep(int qseq) {
		adao.deleteAdminQnaRep(qseq);

		
	}

	
}
