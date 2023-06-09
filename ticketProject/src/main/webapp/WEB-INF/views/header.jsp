<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ticket Shop</title>
<link rel="stylesheet" type="text/css" href="/css/board.css"/>
<!-- <script type="text/javascript" src="/script/board.js"></script> -->

<link rel="stylesheet" type="text/css" href="/css/ticketing.css"/>
<link rel="stylesheet" type="text/css" href="/css/apply.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/review.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/member.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/qna.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/main.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/cartlist.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/contentDetail.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/order.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/mypage.css?ver=1"/>

<script src="script/jquery-3.7.0.min.js"></script>

<script type="text/javascript" src="/script/main.js"></script>
<script type="text/javascript" src="/script/qna.js"></script>
<script type="text/javascript" src="./script/member.js"></script>
<script type="text/javascript" src="/script/cartlist.js"></script>
<script type="text/javascript" src="/script/apply_register.js"></script>
<script type="text/javascript" src="/script/review.js"></script>
<script type="text/javascript" src="/script/success.js"></script>
<script type="text/javascript" src="/script/contentDetail.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script type="text/javascript">
	var imgNum = 0;
	var dist = 0;

	$(function(){
		var num = 0;
		setInterval(function(){
			$('#imgs').animate({
				left : num * -600
			}, 1000);
			num++;
			if( num == 5 )
				num = 0;
		}, 2000);
	});
	
	$(function() {
		$("#btn2").click(function() {
			if (imgNum == 2) {
				imgNum = 0;
				dist = imgNum * -420;
				$("#imgview3").animate({
					left : dist
				}, 300);
			} else {
				imgNum++;
				dist = imgNum * -420;
				$("#imgview3").animate({
					left : dist
				}, 300);
			}
		});
	});

	$(function() {
		$("#btn1").click(function() {
			if (imgNum == 0) {
				imgNum = 2;
				dist = imgNum * -420;
				$("#imgview3").animate({
					left : dist
				}, 300);
			} else {
				imgNum--;
				dist = imgNum * -420;
				$("#imgview3").animate({
					left : dist
				}, 300);
			}
		})
	});

	$(function() {
		var successNum = 0;
		$('#successleft').click(function() {
			if (successNum == 0) {
				successNum = 2;
				dist = successNum * -350;
				$('.successlistview').animate({
					left : dist
				}, 300);
			} else {
				successNum--;
				dist = successNum * -350;
				$('.successlistview').animate({
					left : dist
				}, 300);
			}
		});
	});

	$(function() {
		var successNum = 0;
		$('#successright').click(function() {
			if (successNum == 2) {
				successNum = 0;
				dist = successNum * -350;
				$('.successlistview').animate({
					left : dist
				}, 300);
			} else {
				successNum++;
				dist = successNum * -350;
				$('.successlistview').animate({
					left : dist
				}, 300);
			}
		});
	});
</script>

</head>
<body>
<div class="container">
<form name="log_frm">
	<div id="wrap">
	
		<header id="header">
		<div id="logo">
			<a href="/"> <img src="images/logo.png" />
			</a>
		</div>
			<div class="header_inner">
				<div class="top_menu">
					<ul>
						<c:choose>
							<c:when test="${ empty loginUser }">
								<li><a href="loginForm">로그인</a></li>
								<li><a href="contract">회원가입</a></li>
								<li><a href="cartList">장바구니</a></li>
								<li><a href="loginForm">1:1 문의하기</a></li>
								<li><a href="admin">admin</a>
							</c:when>
							<c:otherwise>
								<li>${ loginUser.NAME }(${ loginUser.ID })</li>
								<li><a href="logout">로그아웃</a></li>
								<li><a href="myPage">마이페이지</a></li>
								<li><a href="cartList">장바구니</a></li>
								<li><a href="memberEditForm">정보수정</a></li>
								<li><a href="customer">1:1 문의하기</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="top_header_inner">
					<nav id="category_menu">
						<ul>
							<li><a href="category?kind=0">대리 티켓팅</a>
								<ul class="depth2_menu">
									<li><a href="category?kind=0">전체</a></li>
									<li><a href="category?kind=1">콘서트</a></li>
									<li><a href="category?kind=2">뮤지컬</a></li>
									<li><a href="category?kind=3">스포츠</a></li>
									<li><a href="category?kind=4">페스티벌</a></li>
									<li><a href="category?kind=5">전시/행사</a></li>
								</ul></li>
							<li><a href="applyAndRegister">신청/등록</a></li>
							<li><a href="successList?first=1">성공내역</a></li>
							<li><a href="reviewList?first=1">후기</a></li>
						</ul>
					</nav>
					<div class="search_box">
						
							<input class="input_search" type="text" name="key"
								 placeholder="공연명을 검색해주세요" /> <input
								type="submit" class="btn" value="검색" onClick="contentSearch();" />
						
					</div>
				</div>
			</div>
		</header>
		
	</div>
</form>