<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<article>
<h2> 1:1 고객 게시판 </h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
	<form name="formm" method="post">
		<table id="reviewList">
			<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>답변 여부</th></tr>
      		<c:forEach items="${qnaList}"  var="qnaVO">
      			<tr><td> ${qnaVO.QSEQ} </td>
					<td>
							<c:choose>
									<c:when test="${qnaVO.PASSCHECK == 'Y'}">
											<a href="#" onClick="passCheck('${qnaVO.QSEQ}')">
												${qnaVO.SUBJECT}
											</a>
											<!--&nbsp;<img src="/images/key.png" style="width:20px;vertical-align: middle">-->
									</c:when>
									<c:otherwise>
											<a href="qnaView?qseq=${qnaVO.QSEQ}">${qnaVO.SUBJECT}</a>
									</c:otherwise>
							</c:choose>
					</td>  
					<td>${qnaVO.ID}</td>  
        			<td><fmt:formatDate value="${qnaVO.INDATE}" type="date" /></td>
        			<td>
        				<c:choose>
					        <c:when test="${qnaVO.REP==1}"> N </c:when>
					        <c:when test="${qnaVO.REP==2}"> Y </c:when>
						</c:choose>
					</td>
				</tr>
      		</c:forEach>
		</table>
			<div class="clear"></div>
			<div id="paging" style="font-size: 120%; font-weight: bold;">
				<c:url var="action" value="ticket.do?command=qnaList" />
				<c:if test="${paging.prev}">
					<a href="${action }&page=${paging.beginPage-1}">◀</a>&nbsp;</c:if>
				<c:forEach begin="${paging.beginPage }" end="${paging.endPage }" var="index">
					<c:choose>
						<c:when test="${paging.page==index }">[${index }]</c:when>
						<c:otherwise>
							<a href="${action }&page=${index}">${index }</a> &nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.next }">
					<a href="${action }&page=${paging.endPage+1}">▶</a> &nbsp;</c:if>
			</div>
			<div class="clear"></div>
			<br>
			<div class="rev_btn_box btn_box" style= "float: left; margin-left: 720px;">
				<input type="button" value="메인으로" class="cancel" onclick="location.href='/'">
			</div>
			<div id="buttons" class="rev_btn_box btn_box1">
				<input type="button" value="글쓰기" class="submit" onclick="location.href='ticket.do?command=qnaWrite'">
			</div>
			<div class="clear"></div>
			<br>
	</form>
</article>
<%@ include file="../include/headerfooter/footer.jsp" %>