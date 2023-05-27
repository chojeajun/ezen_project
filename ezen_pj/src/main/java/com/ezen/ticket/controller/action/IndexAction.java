package com.ezen.ticket.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.ticket.dao.ContentDao;
import com.ezen.ticket.dao.SuccessDao;
import com.ezen.ticket.dto.ContentVO;
import com.ezen.ticket.dto.SuccessVO;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println(2);
		// best, concert, musical 받아오기
		ContentDao cdao = ContentDao.getInstance();
		SuccessDao sdao = SuccessDao.getInstance();
		ArrayList<ContentVO> bestContent = cdao.getBestContent();
		ArrayList<ContentVO> concert = cdao.selectContentsByCategory(1);
		ArrayList<ContentVO> musical = cdao.selectContentsByCategory(2);
		ArrayList<SuccessVO> success = sdao.getSuccess();
		
		request.setAttribute("bestContent", bestContent);
		request.setAttribute("concert", concert);
		request.setAttribute("musical", musical);
		request.setAttribute("success", success);
		//System.out.println(3);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
		
	}

}
