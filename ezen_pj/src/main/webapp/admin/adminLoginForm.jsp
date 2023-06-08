<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<style type="text/css">

	#wrap {width:500px;border:1px solid red; margin : 0 auto;}
	#wrap h2 {font-size:24px; font-weight:700; color:#444; text-align: center;}
	#wrap input[type="text"] {width:100%; height:50px;  padding-left:20px; font-size:20px;  margin-bottom:10px;}
	#wrap input[type="password"] {width:100%;  height:50px;  padding-left:20px; font-size:20px;}
	
	#wrap table {width:100%;}
	
	#wrap #loginform {padding:10px; box-sizing: border-box;}
	#wrap #loginform .btn_log {width:100%; height:50px; background: red; font-weight: 500; font-size: 20px; color:#fff; outline: none;margin-top:20px; border:none; transition : 0.5s; font-size:18px;}
	#wrap #loginform .btn_log:hover {font-size:20px; color:#fff; background: #444;}
</style>

<header id="header">
		<div id="logo"><a style="display: block; text-align: center;" href="ticket.do?command=index"><img src="images/logo.png" style="width:189px;"/></a></div>
	</header>
<div id="wrap">

<h2>로그인 폼</h2>
			
	<div class="clear"></div>
	<article>
		<div id="loginform">
			<form name="frm" method="post" action="ticket.do">
				<input type="hidden" name="command" value="adminLogin"/>
				<table>
					<tr><td><input type="text" name="workId" size="10" placeholder="아이디"/></td></tr>
					<tr><td><input type="password" name="workPwd" size="10" placeholder="비밀번호"/></td></tr>
					<tr align="center">
						<td colspan="2">
							<input class="btn_log" type="submit" value="LOGIN" onclick="return workerCheck();"/>
							<input class="btn" value="홈으로" onclick="location.href='ticket.do?command=index'"/>
							<br/><br/><h4 style="color:red">${ msg }</h4></td></tr>
				</table>
			</form>
		</div>
	</article>
</div>

<%@ include file="footer.jsp" %>