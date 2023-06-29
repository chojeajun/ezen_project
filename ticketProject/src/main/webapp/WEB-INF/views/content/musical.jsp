<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div id="musical">
	<div class="title">
		<h1>뮤지컬</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ musical }" end="3" var="musicalVO">
			<div id="item">
				<a href="contentDetail?cseq=${ musicalVO.CSEQ }">
					<img src="${ musicalVO.IMAGE }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ musicalVO.TITLE }</h1>
				<a href=
"applyContentSelect&cseq=${ musicalVO.CSEQ }&category=${ musicalVO.CATEGORY }&locationNum=${ musicalVO.LOCATIONNUM }">
					<h3>
						신청하기
					</h3>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="panel">
		<c:forEach items="${ musical }" begin="4" end="7" var="musicalVO">
			<div id="item">
				<a href="contentDetail?cseq=${ musicalVO.CSEQ }">
					<img src="${ musicalVO.IMAGE }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ musicalVO.TITLE }</h1>
				<a href=
"applyContentSelect?cseq=${ musicalVO.CSEQ }&category=${ musicalVO.CATEGORY }&locationNum=${ musicalVO.LOCATIONNUM }">
					<h3>
						신청하기
					</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<%@ include file="../footer.jsp"%>