package com.ezen.ticket.controller;

import com.ezen.ticket.controller.action.Action;
import com.ezen.ticket.controller.action.IndexAction;
import com.ezen.ticket.controller.action.anr.ApplyAndRegisterAction;
import com.ezen.ticket.controller.action.anr.ApplyContentSelectAction;
import com.ezen.ticket.controller.action.anr.ApplyFormAction;
import com.ezen.ticket.controller.action.anr.ApplySelectCommissionerAction;
import com.ezen.ticket.controller.action.anr.CategorySelectAction;
import com.ezen.ticket.controller.action.anr.RegisterFormAction;
import com.ezen.ticket.controller.action.anr.RegisterTimeFormAction;
import com.ezen.ticket.controller.action.cart.CartDeleteAction;
import com.ezen.ticket.controller.action.cart.CartListAction;
import com.ezen.ticket.controller.action.content.ContentFormAction;
import com.ezen.ticket.controller.action.member.ContractAction;
import com.ezen.ticket.controller.action.member.EditFormAction;
import com.ezen.ticket.controller.action.member.FindZipNumAction;
import com.ezen.ticket.controller.action.member.IdCheckFormAction;
import com.ezen.ticket.controller.action.member.JoinAction;
import com.ezen.ticket.controller.action.member.JoinFormAction;
import com.ezen.ticket.controller.action.member.LoginAction;
import com.ezen.ticket.controller.action.member.LoginFormAction;
import com.ezen.ticket.controller.action.member.LogoutAction;
import com.ezen.ticket.controller.action.member.MemberUpdateAction;
import com.ezen.ticket.controller.action.mypage.MyAllRegisterAction;
import com.ezen.ticket.controller.action.mypage.MyRegisterAction;
import com.ezen.ticket.controller.action.mypage.MyRegisteredAction;
import com.ezen.ticket.controller.action.mypage.MypageAction;
import com.ezen.ticket.controller.action.qna.DeleteQnaAction;
import com.ezen.ticket.controller.action.qna.QnaCheckPassAction;
import com.ezen.ticket.controller.action.qna.QnaListAction;
import com.ezen.ticket.controller.action.qna.QnaPassFormAction;
import com.ezen.ticket.controller.action.qna.QnaViewAction;
import com.ezen.ticket.controller.action.qna.QnaViewNoCountAction;
import com.ezen.ticket.controller.action.qna.QnaWriteAction;
import com.ezen.ticket.controller.action.qna.QnaWriteFormAction;
import com.ezen.ticket.controller.action.qna.UpdateQnaAction;
import com.ezen.ticket.controller.action.qna.UpdateQnaFormAction;
import com.ezen.ticket.controller.action.review.ReviewListAction;
import com.ezen.ticket.controller.action.review.ReviewViewAction;
import com.ezen.ticket.controller.action.review.ReviewWriteAction;
import com.ezen.ticket.controller.action.review.ReviewWriteFormAction;


public class ActionFactory {

	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		
		//System.out.println("22 "+ command);
		Action ac = null;
		
		if(command.equals("index")) ac = new IndexAction();

		//qna

		else if(command.equals("qnaList")) ac = new QnaListAction();
		else if (command.equals("qnaView")) ac = new QnaViewAction();
		else if (command.equals("qnaWriteForm")) ac = new QnaWriteFormAction();
		else if (command.equals("qnaWrite")) ac = new QnaWriteAction();

		else if (command.equals("deleteQna")) ac = new DeleteQnaAction();
		else if (command.equals("updateQna")) ac = new UpdateQnaAction();
		else if (command.equals("updateQnaForm")) ac = new UpdateQnaFormAction();
		else if( command.equals("qnaViewNoCount") ) ac = new QnaViewNoCountAction();
		else if( command.equals("qnaPassForm") ) ac = new QnaPassFormAction();
		else if( command.equals("qnaCheckPass") ) ac = new QnaCheckPassAction();
		
		

		//anr
		else if (command.equals("applyAndRegister")) ac = new ApplyAndRegisterAction();
		else if (command.equals("applyForm")) ac = new ApplyFormAction();
		else if (command.equals("categorySelect")) ac = new CategorySelectAction();
		else if (command.equals("applyContentSelect")) ac = new ApplyContentSelectAction();

		else if (command.equals("applyselectCommissioner")) ac = new ApplySelectCommissionerAction();

		else if (command.equals("registerForm")) ac = new RegisterFormAction();
		else if (command.equals("registerTimeForm")) ac = new RegisterTimeFormAction();
		// login
		else if(command.equals("loginForm")) ac = new LoginFormAction();
		else if(command.equals("login")) ac = new LoginAction();
		else if(command.equals("logout")) ac = new LogoutAction();
		// joi
		else if(command.equals("contract")) ac = new ContractAction();
		else if(command.equals("joinForm")) ac = new JoinFormAction();
		else if(command.equals("idCheckForm")) ac = new IdCheckFormAction();
		else if(command.equals("findZipNum")) ac = new FindZipNumAction();
		else if(command.equals("join")) ac = new JoinAction();
		// update, delete
		else if(command.equals("editForm")) ac = new EditFormAction();
		else if(command.equals("memberUpdate")) ac = new MemberUpdateAction();
		// delete 는 마이페이지에서 기능추가
		// review
		else if(command.equals("reviewList")) ac = new ReviewListAction();
		else if(command.equals("reviewView")) ac = new ReviewViewAction();
		else if(command.equals("reviewWriteForm")) ac = new ReviewWriteFormAction();
		else if(command.equals("reviewWrite")) ac = new ReviewWriteAction();  
		// cart
		else if(command.equals("cartList")) ac = new CartListAction();
		else if(command.equals("cartDelete")) ac = new CartDeleteAction();
		// mypage
		else if(command.equals("mypage")) ac = new MypageAction();
		else if(command.equals("myRegister")) ac = new MyRegisterAction();
		else if(command.equals("myRegistered")) ac = new MyRegisteredAction();
		else if(command.equals("myAllRegister")) ac = new MyAllRegisterAction();
		// main 화면 헤더부분
		else if(command.equals("contentForm")) ac = new ContentFormAction();
		//System.out.println("33 "+ command);
		return ac;
	}

	
	
	

}
