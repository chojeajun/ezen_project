<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div id="exhibition">
	<div class="title">
		<h1>전시/행사</h1>
	</div>
	<div class="panel">
		<c:forEach items="${ exhibition }" end="3" var="exhibitionVO">
			<div id="item">
				<a href="contentDetail?cseq=${ exhibitionVO.CSEQ }">
					<img src="${ exhibitionVO.IMAGE }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ exhibitionVO.TITLE }</h1>
				<a href=
"applyContentSelect?cseq=${ exhibitionVO.CSEQ }&category=${ exhibitionVO.CATEGORY }&locationNum=${ exhibitionVO.LOCATIONNUM }">
					<h3>
						신청하기
					</h3>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="panel">
		<c:forEach items="${ exhibition }" begin="4" end="7"
			var="exhibitionVO">
			<div id="item">
				<a href="contentDetail?cseq=${ exhibitionVO.CSEQ }">
					<img src="${ exhibitionVO.IMAGE }" />
				</a>
				<h1 style="line-height: 30px; height: 90px;">${ exhibitionVO.TITLE }</h1>
				<a href=
"applyContentSelect?cseq=${ exhibitionVO.CSEQ }&category=${ exhibitionVO.CATEGORY }&locationNum=${ exhibitionVO.LOCATIONNUM }">
					<h3>
						신청하기
					</h3>
				</a>
			</div>
		</c:forEach>
	</div>
</div>


<%@ include file="../footer.jsp"%>

