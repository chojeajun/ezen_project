package com.ezen.ticket.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.QnaDao;

public class DeleteQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt( request.getParameter("num") ); 
		
		QnaDao bdao = QnaDao.getInstance();
		
		bdao.deleteQna( num );
		bdao.deleteReplyByQnanum( num );
		
		String url = "ticket.do?command=main";
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);

	}

}