package com.ezen.ticket.controller.action.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ContentDao;
import com.ezen.ticket.dto.ContentVO;

public class ContentSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		String key = request.getParameter("key");
		ContentDao cdao = ContentDao.getInstance();
		list = cdao.contentSearch(key);
		String url = "ticket.do?command=contentDetail&cseq=" + list.get(0).getCseq();

		request.getRequestDispatcher(url).forward(request, response);

	}

}
