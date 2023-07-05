<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="m_header.jsp"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0,user-scalable=yes">

<link rel="stylesheet" type="text/css" href="/css/mob_main.css?ver=1"/>
<link rel="stylesheet" type="text/css" href="/css/mob_ticketing.css?ver=1"/>

<script type="text/javascript">
// 이미지 배너 페이드인아웃

    var imgNum=2;
$(function() {
	var temp;
    // $('div').fadeOut(1000);
    // for(var i = 0; i <= 5; i++) $('div').eq(i).fadeOut(1000);
    clearInterval(temp);
    temp = setInterval(function() {
        	//console.log( "111111" + imgNum);
              
        	$('.imgview4').fadeOut(500);
            //console.log( "22222222" + imgNum);
           	$('.imgview4').eq(imgNum-1).fadeIn(2000);
           	imgNum++;
			if(imgNum > 5) {
				imgNum = 1;
			}
            //console.log( "33333333" + imgNum);
    },3000);
});



// $(function() {
//     // $('div').fadeOut(1000);
//     // for(var i = 0; i <= 5; i++) $('div').eq(i).fadeOut(1000);
//     var imgNum=0;
//     setInterval(function() {
//         for(var i = 0; i <= 5; i++) { 
//         $('#cont_box').eq(i).fadeOut(1000);
//         imgNum++;
//         $('#cont_box').eq(imgNum).fadeIn(1000);
//         if(imgNum > 5) imgNum = 0;
//        }
//     },1500);
// });

</script>
<div id="bestContent" class="row">
	<div class="title">
		<h1>Best Content</h1>
	</div>
	<div id="bestcontentback">
		<div class="imgview1">
			<div class="imgview2">
				<div id="imgview3">
					<c:forEach items="${ bestList }" end="5" var="bestContentVO">
					<div class="imgview4">
							<div id="cont_box">
								<a href="contentDetail?cseq=${ bestContentVO.CSEQ }">
									<img src="${ bestContentVO.IMAGE }" />
								</a>
							
							<div id="title1">
								<h3>${ bestContentVO.TITLE }</h3>
							</div>
							<div id="apply">
								<a href="applyContentSelect?cseq=${ bestContentVO.CSEQ }
										&category=${ bestContentVO.CATEGORY }&locationNum=${ bestContentVO.LOCATIONNUM }">
									<h3>신청하기</h3>
								</a>
							</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
<!-- 			<div id="remote1"> -->
<!-- 				<ul> -->
<!-- 					<li id="btn2">▶</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 			<div id="remote2"> -->
<!-- 				<ul> -->
<!-- 					<li id="btn1">◀</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
		</div>

	</div>

</div>

<div id="concert" class="row">
	<div class="title">
		<h1>New List</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ newList }" end="3" var="concertVO">
			<div id="item">
				<a href="contentDetail?cseq=${ concertVO.CSEQ }">
					<img src="${ concertVO.IMAGE }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ concertVO.TITLE }</h1>
				<a
					href="applyContentSelect?cseq=${ concertVO.CSEQ }&category=${ concertVO.CATEGORY }
						&locationNum=${ concertVO.LOCATIONNUM }"><h3>신청하기</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<div id="success" class="row">
	<div class="title">
		<h1>성&nbsp;공&nbsp;내&nbsp;역</h1>
	</div>
	<div class="successlist1">
		<div class="successlistrealview">
			<div class="successlistview">
				<c:forEach items="${ successList }" end="3" var="successVO">
					<div class="successitem">
						<div class="successlisttitle">
							<a
								href="successView?sucseq=${ successVO.SUCSEQ }">
								<h2>${ successVO.TITLE }</h2>
							</a>
						</div>
						<div class="successlistid">
							<h2>${ successVO.SUCSEQ }.&nbsp;${ successVO.ID }</h2>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="btn3">
			<ul>
				<li id="successleft">◁</li>
				<li id="successright">▷</li>
			</ul>
		</div>
	</div>

</div>

<div id="bannerList" class="row">
	<div id="view">
		<div id="imgs">
			<c:forEach items="${ bannerList }" var="bannerVO">
				<img src="${ bannerVO.IMAGE }" />
			</c:forEach>
		</div>
	</div>
</div>

<%@ include file="m_footer.jsp"%>