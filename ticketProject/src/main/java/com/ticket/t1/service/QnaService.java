package com.ticket.t1.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.IQnaDao;
import com.ticket.t1.dto.QnaVO;
import com.ticket.t1.util.Paging;


@Service
public class QnaService {

	@Autowired
	IQnaDao qdao;

	public void listQna(HashMap<String, Object> paramMap) {
		
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		HttpSession session = request.getSession();
		
		if(request.getParameter("first") != null) {
			session.removeAttribute("page");
		}
		
		// 현재 페이지 설정
		int page = 1;
		if( request.getParameter("page")!=null) {
			page = Integer.parseInt( request.getParameter("page") );
			session.setAttribute("page", page);
		}else if( session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		}else {
			page=1;
			session.removeAttribute("page");
		}
		
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		// getAllCount 메서드를 이용한 총 게시물 갯수르 리턴
		//필요한건 게시물 갯수를 계산해서 나에게 담아 보내줄 OUT 변수가 필요합니다
		paramMap.put("cnt", 0);  
		qdao.getAllCountQna( paramMap );
		//getAllCount 가 실행되고 나면 "cnt" 키값에 해당하는 밸류가 총 게시물 갯수가 됩니다
		int count = (Integer)paramMap.get("cnt");  
		
		// 페이징 객체 설정
		paging.setTotalCount(count);
		paging.paging();
		
		paramMap.put("startNum", paging.getStartNum() );  // 전달인수
		paramMap.put("endNum", paging.getEndNum() );  // 전달인수
		
		qdao.listQna( paramMap ); //결과가 ref_cursor에 담깁니다
		
		paramMap.put("paging", paging);  // 리턴될 인수
		
		
	}

	public void getQna(HashMap<String, Object> paramMap) {
		qdao.getQna( paramMap );		
	}

	public void insertQna(HashMap<String, Object> paramMap) {
		qdao.insertQna( paramMap);
	}


	public void updateQna(HashMap<String, Object> paramMap) {
		qdao.updateQna( paramMap);
		
	}

	public void removeQna(HashMap<String, Object> paramMap) {
		qdao.removeQna(paramMap);
		
	}

	
}
