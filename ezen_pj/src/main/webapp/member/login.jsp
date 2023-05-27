<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>




<div id="login_box">
	<h1>로그인 폼</h1>
	<form method="post" action="ticket.do" name="loginFrm">
		<input type="hidden" name="command" value="login">
		<fieldset>
		<legend></legend>
		<div class="login_wrap" >
			<div class="id_input_box">
				<input type="text" name="id" class="id_input"  maxlength="20" placeholder="아이디">
			</div>
			<div class="pwd_input_box">
				<input type="password" name="pwd" class="pwd_input"  maxlength="20" placeholder="비밀번호">
			</div>
			<div class="btn_log">
				<input type="button" value="LOGIN" class="login_btn" onclick="loginCheck();">
			</div>
			<div class="login_option">
				<a href="ticket.do?command=index" class="go_main">메인으로</a>
				<div class="login_option_detail">
					<a href="#" class="check_idpw">아이디/비밀번호 찾기 &nbsp;|&nbsp; </a>
					<a href="ticket.do?command=contract" class="go_join">회원가입</a>
				</div>
			</div>
		</div>
		</fieldset>
	</form>
	<div>&nbsp;&nbsp;&nbsp;${param.message}</div>
	<div>&nbsp;&nbsp;&nbsp;${message}</div>
	<hr/>
	<hr/>
	<div class="login_form_footer">
		<br/>
		<img src="images/logo.png" class="login_form_footer_logo" style="width:60px; opacity:0.5;"/>
		&nbsp; Copyright (C)
		<b>티켓온</b>
		. All rights reserved.
	</div>
</div>


<%@ include file="../footer.jsp" %>