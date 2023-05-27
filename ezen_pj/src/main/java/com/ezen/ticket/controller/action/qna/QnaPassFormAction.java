package com.ezen.ticket.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.controller.action.Action;

public class QnaPassFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "qna/qnaCheckPass.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);

	}

}