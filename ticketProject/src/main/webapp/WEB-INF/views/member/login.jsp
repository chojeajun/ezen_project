<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<style type="text/css">
	.sns_log_box {}
	.btn_log {width:50%; display:inline-block;}
</style>
 
<article>
	<form method="post" action="login" name="loginFrm">
		<fieldset><legend>LogIn&nbsp;&nbsp;&nbsp;&nbsp;${message} </legend>
			<label>User ID</label><input name="id" type="text" value="${dto.id}"><br> 
			<label>Password</label><input name="pwd" type="password" >
			<div>
	            <input type="submit" value="로그인"  style="width:200px;">
	            <input type="button" value="일반회원가입"  onclick="location.href='contract'"  style="width:200px;">
	            <input type="button" value="아이디 비밀번호 찾기"  onclick=""  style="width:200px;">
	            <hr>
	            <div class="sns_log_box">
					<a class="btn_log" href="kakaostart"><img src="/images/kakao.png"    style="width:300px;"></a>
					<a class="btn_log href="#"><img src="/images/naver.png" style="width:300px;"></a>
					<a class="btn_log href="#"><img src="/images/google.png"    style="width:300px;"> </a>
					<a class="btn_log href="#"><img src="/images/facebook.png"    style="width:300px;"></a>
				</div>
	        </div>
        </fieldset>
	</form>    
	
</article>

<%@ include file="../footer.jsp" %>