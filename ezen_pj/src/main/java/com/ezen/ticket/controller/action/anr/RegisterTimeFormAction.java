package com.ezen.ticket.controller.action.anr;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.RegisterTimeDao;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.dto.RegisterTimeVO;

public class RegisterTimeFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "apply_register/register/registerTimeForm.jsp";
		HttpSession session=request.getSession();
		MemberVO mvo=(MemberVO)session.getAttribute("loginUser");
		
		String date = request.getParameter("date");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		
		if(mvo ==null) {
			url="ticket.do?command=loginForm";
		} else {
			RegisterTimeDao rdao = RegisterTimeDao.getInstance();
			rdao.insertRegisterTime(date, starttime, endtime, mvo);
			ArrayList<RegisterTimeVO> regi = rdao.getMyRegister(mvo);
			request.setAttribute("register", regi);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
