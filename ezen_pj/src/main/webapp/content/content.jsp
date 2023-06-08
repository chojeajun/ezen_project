<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div id="concert">
	<div class="title">
		<h1>콘서트</h1>
	</div>

	<div class="panel">
		<c:forEach items="${ concert }" begin="0" end="3" var="concertVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ concertVO.cseq }">
					<img src="${ concertVO.image }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ concertVO.title }</h1>
				<a
					href="ticket.do?command=applyContentSelect&cseq=${ concertVO.cseq }&category=${ concertVO.category }&locationNum=${ concertVO.locationNum }"><h3>신청하기</h3>
				</a>
			</div>
		</c:forEach>
	</div>


	<div class="panel">
		<c:forEach items="${ concert }" begin="4" end="7" var="concertVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ concertVO.cseq }">
					<img src="${ concertVO.image }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ concertVO.title }</h1>
				<a
					href="ticket.do?command=applyContentSelect&cseq=${ concertVO.cseq }&category=${ concertVO.category }&locationNum=${ concertVO.locationNum }"><h3>신청하기</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<div id="musical">
	<div class="title">
		<h1>뮤지컬</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ musical }" end="3" var="musicalVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ musicalVO.cseq }">
					<img src="${ musicalVO.image }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ musicalVO.title }</h1>
				<a
					href="ticket.do?command=applyContentSelect&cseq=${ musicalVO.cseq }&category=${ musicalVO.category }&locationNum=${ musicalVO.locationNum }"><h3>신청하기</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<div id="sports">
	<div class="title">
		<h1>스포츠</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ sports }" end="3" var="sportsVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ sportsVO.cseq }">
					<img src="${ sportsVO.image }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ sportsVO.title }</h1>
				<a
					href="ticket.do?command=applyContentSelect&cseq=${ sportsVO.cseq }&category=${ sportsVO.category }&locationNum=${ sportsVO.locationNum }"><h3>신청하기</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<div id="festival">
	<div class="title">
		<h1>페스티벌</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ festival }" end="3" var="festivalVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ festivalVO.cseq }">
					<img src="${ festivalVO.image }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ festivalVO.title }</h1>
				<a
					href="ticket.do?command=applyContentSelect&cseq=${ festivalVO.cseq }&category=${ festivalVO.category }&locationNum=${ festivalVO.locationNum }"><h3>신청하기</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<div id="exhibition">
	<div class="title">
		<h1>전시/행사</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ exhibition }" end="3" var="exhibitionVO">
			<div id="item">
				<a href="ticket.do?command=contentDetail&cseq=${ exhibitionVO.cseq }">
					<img src="${ exhibitionVO.image }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ exhibitionVO.title }</h1>
				<a
					href="ticket.do?command=applyContentSelect&cseq=${ exhibitionVO.cseq }&category=${ exhibitionVO.category }&locationNum=${ exhibitionVO.locationNum }"><h3>신청하기</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<%@ include file="../footer.jsp"%>