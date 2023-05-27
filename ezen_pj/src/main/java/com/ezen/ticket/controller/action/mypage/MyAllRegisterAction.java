package com.ezen.ticket.controller.action.mypage;

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

public class MyAllRegisterAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String url = "mypage/myallregister.jsp";
      HttpSession session = request.getSession();
      MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
      if(mvo == null) {
         url = "ticket.do?command=loginForm";
      }else {
         RegisterTimeDao rgtdao = RegisterTimeDao.getInstance();
         ArrayList<RegisterTimeVO> myallregister = rgtdao.getMyRegister(mvo);
         String member = mvo.getNickname();
         request.setAttribute("myAllRegister", myallregister);
         request.setAttribute("member", member);
      }
      
      request.getRequestDispatcher(url).forward(request, response);
      
   }

}