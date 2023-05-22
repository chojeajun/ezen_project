<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket Shop</title>

<link href="css/ticketing.css" rel="stylesheet">


<link rel="stylesheet" href="css/login.css?ver=1" />
<link rel="stylesheet" href="css/qna.css?ver=1" />
<script src="script/jquery-3.7.0.min.js"></script>
<script src="script/main.js"></script>
<script src="script/member.js"></script>
<script src="apply_register/apply_register.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
var imgNum = 0;
var dist = 0;
 $(function(){
	 $('#btn2').click(function(){
		 if( imgNum == 2){
			 imgNum = 0;
			 dist = imgNum * -380;
			 $('#imgview3').animate({left:dist}, 1000);
		 }else{
			 imgNum++;
			 dist = imgNum * -380;
			 $('#imgview3').animate({left:dist}, 1000);
		 }
	 });
 });
 $(function(){
	 $('#btn1').click(function(){
		 if( imgNum == 0){
			 imgNum = 2;
			 dist = imgNum * -380;
			 $('#imgview3').animate({left:dist}, 1000);
		 }else{
			 imgNum--;
			 dist = imgNum * -380;
			 $('#imgview3').animate({left:dist}, 1000);
		 }
	 });
 });
</script>

<style type="text/css">

</style>
</head>
<body>
	<div id="wrap">
		<header id="header">
			<div class="header_inner">
				<div class="top_menu">
					<ul>
						<li><a href="ticket.do?command=qnaList">1:1 문의하기</a></li>
						<li><a href="ticket.do?command=myPage">마이 페이지</a></li>
						<li><a href="ticket.do?command=cartList">장바구니</a></li>
						<c:choose>
							<c:when test="${ empty loginUser }">
								<li><a href="ticket.do?command=contract">회원가입</a></li>
								<li><a href="ticket.do?command=loginForm">로그인</a></li>
							</c:when>
							<c:otherwise>
								<li>${ loginUser.name }(${ loginUser.id })</li>
								<li><a href="ticket.do?command=editForm">정보수정</a></li>
								<li><a href="ticket.do?command=logout">로그아웃</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="top_header_inner" >
					<div id="logo">
						<a href="ticket.do?command=index"> <img src="images/logo.png" />
						</a>
					</div>

					<nav id="category_menu">
						<ul>
							<li><a href="ticket.do?command=proxyTicketingForm">대리
									티켓팅</a>
								<ul class="depth2_menu">
									<li><a href="ticket.do?command=musicalForm">콘서트</a></li>
									<li><a href="ticket.do?command=concertForm">뮤지컬</a></li>
									<li><a href="ticket.do?command=sportsForm">스포츠</a></li>
									<li><a href="ticket.do?command=festivalForm">페스티벌</a></li>
									<li><a href="ticket.do?command=exhibitionForm">전시/행사</a></li>
								</ul></li>
							<li><a
								href="ticket.do?command=applyAndRegister">신청/등록</a></li>
							<li><a href="ticket.do?command=successHistoryForm">성공내역</a></li>
							<li><a href="ticket.do?command=epilogueForm">후기</a></li>
						</ul>
					</nav>
					<div class="search_box">
						<input class="input_search" type="text" name="key"
							value="${ key }" placeholder="공연명을 검색해주세요" /> <input
							type="button" class="btn" value="검색"
							onClick="go_search('contentList'); " />
					</div>
				</div>
			</div>
		</header>