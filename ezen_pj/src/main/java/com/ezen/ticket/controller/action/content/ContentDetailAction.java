package com.ezen.ticket.controller.action.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.CartDao;
import com.ezen.ticket.dao.ContentDao;
import com.ezen.ticket.dao.Content_Time_View_Dao;
import com.ezen.ticket.dto.CartVO;
import com.ezen.ticket.dto.ContentVO;
import com.ezen.ticket.dto.Content_Time_View_VO;
import com.ezen.ticket.dto.MemberVO;

public class ContentDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "content/contentDetail.jsp";
		ContentDao cdao = ContentDao.getInstance();
		Content_Time_View_Dao ctvdao = Content_Time_View_Dao.getInstance();
		ArrayList<ContentVO> content = new ArrayList<ContentVO>();
		ArrayList<Content_Time_View_VO> list = new ArrayList<Content_Time_View_VO>();

		int cseq = Integer.parseInt(request.getParameter("cseq"));
		content = cdao.selectContentByCseq(cseq);
		list = ctvdao.hoonSelectCartDetail(cseq);

		request.setAttribute("content", content);
		request.setAttribute("list", list);

		request.getRequestDispatcher(url).forward(request, response);

	}

}
