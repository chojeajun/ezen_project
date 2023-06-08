package com.ezen.ticket.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.AdminDao;
import com.ezen.ticket.dto.AdminVO;
import com.ezen.ticket.dto.ContentVO;

public class AdminContentListDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url ="admin/adminContentListDetail.jsp";
		int cseq = Integer.parseInt(request.getParameter("cseq"));
		System.out.println(cseq);
		
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if(avo == null) {
			url = "ticket.do?command=admin";
		} else {
			
			
			AdminDao adao=AdminDao.getInstance();
			ContentVO content=adao.getAdminContent(Integer.parseInt(request.getParameter("cseq")));
			ArrayList<ContentVO> locList=adao.getAdminContentLoc(Integer.parseInt(request.getParameter("cseq")));
			ArrayList<ContentVO> timeList=adao.getAdminContentTime(Integer.parseInt(request.getParameter("cseq")));
			
			request.setAttribute("ContentVO", content);
			request.setAttribute("locationList", locList);
			request.setAttribute("timeList", timeList);
			
		}
		
		
		request.getRequestDispatcher(url).forward(request, response);
		

	}

}
