@package com.ezen.ticket.controller.action.anr;

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
		ArrayList<ContentVO> contentTableList =null;	
		ArrayList<ContentVO> contentDateList =null;	
		ArrayList<ContentVO> contentLocationList =null;	
		ArrayList<ContentVO> contentAreaList =null;	
		ArrayList<ContentVO> contentTimeList =null;
		if(mvo ==null) {
			url="ticket.do?command=loginForm";
		}else {
			ContentDao cdao=ContentDao.getInstance();
			
			if(request.getParameter("contentDate")!=null) { //날짜를 선택했을 경우
				System.out.println("contendDate:"+request.getParameter("contentDate"));
				String contentDate=request.getParameter("contentDate").substring(0, 10); //Timestamp 형식에서 시간부분 버림(0000-00-00 형식으로 변환)
				contentDate=contentDate.replace("-", "");//날짜에서 - 부분 버리고 00000000 형식으로 변환 
				System.out.println("contentDate:"+contentDate);
				
				int category=Integer.parseInt(request.getParameter("category"));
				System.out.println("category:" + category);
				list = cdao.selectContent(category);
				
				int cseq=Integer.parseInt(request.getParameter("cseq"));
				
				contentTableList=cdao.selectFromContentByTitle(cseq); //공연기본정보 얻음
				int locationNum=contentTableList.get(0).getLocationNum();
				System.out.println("locationNum:"+locationNum);

				contentDateList=cdao.selectFromContentTimeByTitle(cseq); //공연 날짜 얻음
				contentLocationList=cdao.selectFromLocationViewByTitle(cseq); //공연장 이름, 좌석도 얻음
				contentAreaList=cdao.selectFromContentAreaByTitle(locationNum); //구역, 구역별 가격 얻음
				contentTimeList=cdao.selectTimeByDate(cseq, contentDate); //
				
				request.setAttribute("category", category);
				
				request.setAttribute("contentList", list);
				request.setAttribute("contentTableList", contentTableList);
				request.setAttribute("contentDateList", contentDateList);
				request.setAttribute("contentLocationList", contentLocationList);
				request.setAttribute("contentAreaList", contentAreaList);
				request.setAttribute("contentTimeList", contentTimeList);
			}else { //제목을 선택했을 경우
			
			int category=Integer.parseInt(request.getParameter("category"));
			list = cdao.selectContent(category);
			
			int cseq=Integer.parseInt(request.getParameter("cseq"));
			int locationNum=Integer.parseInt(request.getParameter("locationNum"));
			System.out.println("locationNum:"+locationNum);
			
			contentTableList=cdao.selectFromContentByTitle(cseq);
			contentDateList=cdao.selectFromContentTimeByTitle(cseq);
			contentLocationList=cdao.selectFromLocationViewByTitle(cseq);
			contentAreaList=cdao.selectFromContentAreaByTitle(locationNum);
			
			request.setAttribute("category", category);

			request.setAttribute("contentList", list);
			request.setAttribute("contentTableList", contentTableList);
			request.setAttribute("contentDateList", contentDateList);
			request.setAttribute("contentLocationList", contentLocationList);
			request.setAttribute("contentAreaList", contentAreaList);

			
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
