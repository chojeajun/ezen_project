package com.ezen.ticket.controller.action.anr;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.dao.ContentDao;
import com.ezen.ticket.dao.RegisterTimeDao;
import com.ezen.ticket.dto.ContentVO;
import com.ezen.ticket.dto.MemberVO;
import com.ezen.ticket.dto.RegisterTimeVO;

public class ApplySelectCommissionerAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url="apply_register/apply/applySelectCommissioner.jsp";
		
		HttpSession session=request.getSession();
		MemberVO mvo=(MemberVO)session.getAttribute("loginUser");
		if(mvo ==null) {
			url="ticket.do?command=loginForm";
		}else {
		String cseqStr=request.getParameter("cseq");
		int cseq=Integer.parseInt(cseqStr);
//		cseq로 locationName, artist 명 뽑기
		ContentDao cdao=ContentDao.getInstance();
		ArrayList<ContentVO> list=cdao.selectFromContentByTitle(cseq);
		
		
		
		
//		cseq랑 area이름으로 area, 가격, 좌석배치도 필요
		String area=request.getParameter("area");
		ArrayList<ContentVO> list2=cdao.selectAreaPrice(cseq, area);
		

//		list의 tDateTime으로 registerTime이랑 비교해서 대리인 추출
		RegisterTimeDao rtdao=RegisterTimeDao.getInstance();
		String tDate=list.get(0).gettDateTime().substring(0, 8); //날짜 19991111형식으로 자름
		String tTimeStr=list.get(0).gettDateTime().substring(8,12); //시간 0000형식으로 자름
		int tTime=Integer.parseInt(tTimeStr);
		
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfReal= new SimpleDateFormat("yyyy-MM-dd");
		// String 타입을 Date 타입으로 변환
		Date formatDate;
		try {
			formatDate = sdf.parse(tDate);
			String parseTDate= sdfReal.format(formatDate);
			tTimeStr=tTimeStr.substring(0, 2)+":"+tTimeStr.substring(2);
			System.out.println("티켓팅날짜:"+parseTDate+" 시간:"+tTimeStr);
			request.setAttribute("tDateTime", parseTDate+"  "+tTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환
		
		ArrayList<RegisterTimeVO> commissionerList=rtdao.getCommissioner(mvo.getMseq(),tDate, tTime);

		String date=request.getParameter("date").substring(0, 10);
		System.out.println("date:"+date);
		request.setAttribute("detailList",list); //제목, 아티스트,포스터
		request.setAttribute("date", date); //이건 다음페이지 콘텐츠용
		request.setAttribute("time", request.getParameter("time"));//이건 다음페이지 콘텐츠용
		request.setAttribute("areaList", list2); //위치명, area, 가격, 좌석배치도
		request.setAttribute("quantity", Integer.parseInt(request.getParameter("quantity"))); //수량
		request.setAttribute("comList", commissionerList);//대리인 리스트~!
		
		request.getRequestDispatcher(url).forward(request, response);
		}
	}


}
