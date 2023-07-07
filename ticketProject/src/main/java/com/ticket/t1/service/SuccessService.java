package com.ticket.t1.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.t1.dao.ISuccessDao;
import com.ticket.t1.dto.SuccessReplyVO;
import com.ticket.t1.dto.SuccessVO;
import com.ticket.t1.util.Paging;

@Service
public class SuccessService {

	@Autowired
	ISuccessDao sucdao;
	
	public void getSuccessListBySucseq(HashMap<String, Object> paramMap) {
		sucdao.getSuccessListBySucseq( paramMap );
	}

	public void readCountOne(HashMap<String, Object> paramMap) {
		sucdao.readCountOne( paramMap );
	}

	public void getReplyList(HashMap<String, Object> paramMap) {
		sucdao.getReplyList( paramMap );
		
	}

	public void getReplyMember(HashMap<String, Object> paramMap) {
		sucdao.getReplyMember( paramMap );
	}

	public void replyDelete(int srseq) {
		sucdao.replyDelete(srseq);
		
	}

	// list 형태로 리턴받을거야
//	public List<SuccessVO> listSuccess() {
//		return sucdao.listSuccess();
//	}

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

	public void insertSuccessReply(SuccessReplyVO svo) {
		sucdao.insertSuccessReply(svo);
	}

	
	
}
