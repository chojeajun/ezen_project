package com.ezen.ticket.controller.action.anr;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ContentDao;
import com.ezen.ticket.dto.ContentVO;
import com.ezen.ticket.dto.MemberVO;

public class ApplyContentSelectAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url="apply_register/apply/applyForm.jsp";
		HttpSession session=request.getSession();
		MemberVO mvo=(MemberVO)session.getAttribute("loginUser");
		ArrayList<ContentVO> list =null;	
		ArrayList<ContentVO> list2 =null;	
		ArrayList<ContentVO> list3 =null;	
		ArrayList<ContentVO> list4 =null;	
		ArrayList<ContentVO> list5 =null;	
		ArrayList<ContentVO> list6 =null;
		if(mvo ==null) {
			url="ticket.do?command=loginForm";
		}else {
			ContentDao cdao=ContentDao.getInstance();
			
			if(request.getParameter("contentDate")!=null) {
				System.out.println(request.getParameter("contentDate"));
				String contentDate=request.getParameter("contentDate").substring(0, 10);
				System.out.println(contentDate);
				contentDate=contentDate.replace("-", "");
				System.out.println(contentDate);
				int category=Integer.parseInt(request.getParameter("category"));
				list = cdao.selectContent(category);
				
				int cseq=Integer.parseInt(request.getParameter("cseq"));
				int locationNum=Integer.parseInt(request.getParameter("locationNum"));
				
				list2=cdao.selectFromContentByTitle(cseq);
				list3=cdao.selectFromContentTimeByTitle(cseq);
				list4=cdao.selectFromLocationViewByTitle(cseq);
				list5=cdao.selectFromContentAreaByTitle(cseq);
				list6=cdao.selectTimeByDate(contentDate);
				
				request.setAttribute("category", category);
				
				request.setAttribute("contentList", list);
				request.setAttribute("contentTableList", list2);
				request.setAttribute("contentDateList", list3);
				request.setAttribute("contentLocationList", list4);
				request.setAttribute("contentAreaList", list5);
				request.setAttribute("contentTimeList", list6);
			}else {
				
			
			int category=Integer.parseInt(request.getParameter("category"));
			list = cdao.selectContent(category);
			
			int cseq=Integer.parseInt(request.getParameter("cseq"));
			int locationNum=Integer.parseInt(request.getParameter("locationNum"));
			
			list2=cdao.selectFromContentByTitle(cseq);
			list3=cdao.selectFromContentTimeByTitle(cseq);
			list4=cdao.selectFromLocationViewByTitle(cseq);
			list5=cdao.selectFromContentAreaByTitle(cseq);
			
			request.setAttribute("category", category);

			request.setAttribute("contentList", list);
			request.setAttribute("contentTableList", list2);
			request.setAttribute("contentDateList", list3);
			request.setAttribute("contentLocationList", list4);
			request.setAttribute("contentAreaList", list5);

			
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
