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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="script/jquery-3.7.0.min.js"></script>

<script type="text/javascript" src="/script/main.js"></script>
<script type="text/javascript" src="./script/member.js"></script>
<script type="text/javascript" src="/script/cartlist.js"></script>
<script type="text/javascript" src="/script/apply_register.js"></script>
<script type="text/javascript" src="/script/review.js"></script>
<script type="text/javascript" src="/script/success.js"></script>
<script type="text/javascript" src="/script/contentDetail.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script type="text/javascript">

// 	var imgNum = 0;
 	var dist = 0;
 	var wwidth;
 	var movewidth;

	$(function(){
		
		$(window).on('load', function() {
			movewidth = $("#bannerList #view #imgs img").innerWidth();
		});
		
		
		$(window).resize(function () {
			movewidth = $("#bannerList #view #imgs img").innerWidth();
			console.log(movewidth);
		});
		
		var num = 0;
		setInterval(function(){
			$('#imgs').animate({
				left : num * -movewidth
			}, 1000);
			num++;
			if( num == 4 )
				num = 0;
		}, 2000);
		
	});
	
// 	$(function() {
// 		$("#btn2").click(function() {
// 			if (imgNum == 2) {
// 				imgNum = 0;
// 				dist = imgNum * -420;
// 				$("#imgview3").animate({
// 					left : dist
// 				}, 300);
// 			} else {
// 				imgNum++;
// 				dist = imgNum * -420;
// 				$("#imgview3").animate({
// 					left : dist
// 				}, 300);
// 			}
// 		});
// 	});

// 	$(function() {
// 		$("#btn1").click(function() {
// 			if (imgNum == 0) {
// 				imgNum = 2;
// 				dist = imgNum * -420;
// 				$("#imgview3").animate({
// 					left : dist
// 				}, 300);
// 			} else {
// 				imgNum--;
// 				dist = imgNum * -420;
// 				$("#imgview3").animate({
// 					left : dist
// 				}, 300);
// 			}
// 		})
// 	});

	$(function() {
		var successNum = 0;
		$('#successleft').click(function() {
			if (successNum == 0) {
				successNum = 2;
				dist = successNum * -220;
				$('.successlistview').animate({
					top : dist
				}, 300);
			} else {
				successNum--;
				dist = successNum * -220;
				$('.successlistview').animate({
					top : dist
				}, 300);
			}
		});
	});

	$(function() {
		var successNum = 0;
		$('#successright').click(function() {
			if (successNum == 2) {
				successNum = 0;
				dist = successNum * -220;
				$('.successlistview').animate({
					top : dist
				}, 300);
			} else {
				successNum++;
				dist = successNum * -220;
				$('.successlistview').animate({
					top : dist
				}, 300);
			}
		});
	});
</script>

</head>
<body>
<div class="container-fluid row" style="margin: 0 auto; padding:0;">
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
								<li><a href="qnaList">1:1 문의하기</a></li>
								<li><a href="admin">admin</a>
							</c:when>
							<c:otherwise>
								<li>(${ loginUser.ID })님</li>
								<li><a href="logout">로그아웃</a></li>
								<li><a href="myPage">마이페이지</a></li>
								<li><a href="cartList">장바구니</a></li>
								<li><a href="memberEditForm">정보수정</a></li>
								<li><a href="qnaList">1:1 문의하기</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="top_header_inner">
<!-- 					<nav id="category_menu"> -->
<!-- 						<ul> -->
<!-- 							<li><a href="category?kind=0">대리 티켓팅</a> -->
<!-- 								<ul class="depth2_menu"> -->
<!-- 									<li><a href="category?kind=0">전체</a></li> -->
<!-- 									<li><a href="category?kind=1">콘서트</a></li> -->
<!-- 									<li><a href="category?kind=2">뮤지컬</a></li> -->
<!-- 									<li><a href="category?kind=3">스포츠</a></li> -->
<!-- 									<li><a href="category?kind=4">페스티벌</a></li> -->
<!-- 									<li><a href="category?kind=5">전시/행사</a></li> -->
<!-- 								</ul></li> -->
<!-- 							<li><a href="applyAndRegister">신청/등록</a></li> -->
<!-- 							<li><a href="successList">성공내역</a></li> -->
<!-- 							<li><a href="reviewList">후기</a></li> -->
<!-- 						</ul> -->
<!-- 					</nav> -->

							<nav class="category_menu navbar bg-light fixed-top" style="left:auto;">
								<div class="container-fluid">
									<a class="navbar-brand"></a>
									<button class="navbar-toggler" type="button"
										data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
										aria-controls="offcanvasNavbar">
										<span class="navbar-toggler-icon"></span>
									</button>
									<div class="offcanvas offcanvas-end" tabindex="-1"
										id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
										<div class="offcanvas-header">
											<h5 class="offcanvas-title" id="offcanvasNavbarLabel">Dropdown</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="offcanvas" aria-label="Close"></button>
										</div>
										<div class="offcanvas-body">
											<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
											<!--  menulist  -->
												<li class="nav-item"><a href="applyAndRegister" class="nav-link" >신청/등록</a></li>
												<li class="nav-item"><a href="successList" class="nav-link" >성공내역</a></li>
												<li class="nav-item"><a href="reviewList" class="nav-link" >후기</a></li>
												
												<!-- dropdown menu -->
												<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" role="button"
													data-bs-toggle="dropdown" aria-expanded="false"> Dropdown </a>
													<ul class="dropdown-menu">
														<li><a href="category?kind=0" class="dropdown-item">전체</a></li>
														<li><a href="category?kind=1" class="dropdown-item">콘서트</a></li>
														<li><a href="category?kind=2" class="dropdown-item">뮤지컬</a></li>
														<li><a href="category?kind=3" class="dropdown-item">스포츠</a></li>
														<li><a href="category?kind=4" class="dropdown-item">페스티벌</a></li>
														<li><a href="category?kind=5" class="dropdown-item">전시/행사</a></li>
													</ul></li>
											</ul>
												<form class="d-flex mt-3" role="search">
												<input class="input_search form-control me-2" type="text" name="key"
													placeholder="검색어를 입력해주세요" aria-label="Search">
												<button class="btn btn-outline-success" type="submit" onclick="contentSearch();">검색</button>
											</form>
										</div>
									</div>
								</div>
							</nav>

<!-- 							<div class="search_box"> -->
<!-- 								<input class="input_search" type="text" name="key" -->
<!-- 								placeholder="공연명을 검색해주세요" /> <input -->
<!-- 								type="submit" class="btn" value="검색" onClick="contentSearch();" /> -->
<!-- 							</div> -->
				</div>
			</div>
		</header>
		
	</div>
</form>