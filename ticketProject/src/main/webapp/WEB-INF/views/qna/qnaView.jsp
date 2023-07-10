<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">1:1 고객 게시판</h2>
		<h3>고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
		<form name="rev_formm" method="post" class="review_form" >
			<input type="hidden" name="qseq" value="${ qnaVO.QSEQ }">
			<table class="review_view_table">
				<tr>
					<th>번호</th>
					<td>${qnaVO.QSEQ}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${qnaVO.ID}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td width="200">${qnaVO.SUBJECT}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td align="left">
						<fmt:parseDate var="parseDate" value="${qnaVO.INDATE}" pattern="yyyy-MM-dd" />
						<fmt:formatDate var="resultdate" value="${parseDate}" pattern="yyyy-MM-dd" />
						${qnaVO.INDATE}
					</td>
				</tr>
				<tr>
					<th>질문내용</th>
					<td align="left">
						<textarea cols="" rows="10" readonly="readonly" >${qnaVO.CONTENT}</textarea>
					</td>
				</tr>
				<!-- <tr>
					<th>이미지</th>
					<td align="left" style=" color: white;"><img src="./images/content/${qnaVO.IMAGE }" style="width:200px; "></td>
				</tr>-->
<!-- 				<tr> -->
<!-- 					<th>댓글</th> -->
<%-- 					<td align="left" style=" color: white;">${reviewVO.reply } --%>
<!-- 				</tr>		 -->
			</table>
			<!--  리뷰 댓글box  -->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<table class="reply_box">
				<tr style="height:30px; border-bottom:1px solid #ddd;">
					<th style="width:20%;">댓글 작성자</th>
					<th style="width:20%; border-right:1px solid #ddd; border-left:1px solid #ddd;">작성일</th>
					<th style="width:60%;">댓글내용</th>
				</tr>
				<c:forEach items="${ replyList }" var="reply">
					<tr align="center" style="height:30px;">
						<td style="line-height:30px;">${ reply.USERID }</td>
						<td style="line-height:30px; border-right:1px solid #ddd; border-left:1px solid #ddd;" ><fmt:formatDate value="${ reply.WRITEDATE }" pattern="MM/dd HH:mm" /></td>
						<td style="line-height:30px;" align="left">&nbsp;${ reply.REPLYCONTENT }</td>
						<td style="line-height:30px;">
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="clear"></div>
			<div class="rev_btn_box btn_box" style="float: left">
				<input type="button" value="메인으로" class="cancel" onClick="location.href='/'">
			</div>
			 
			<!--  리뷰 수정box -->
			<div id="buttons" class="rev_btn_box btn_box" style="float: right">
				<input type="button" value="목록보기" class="submit" onClick="location.href='qnaList'">
				<c:if test="${ qnaVO.ID == loginUser.ID }">
					<input type="button" value="게시글 삭제" onclick="location.href='qnaDelete?qseq=${qnaVO.QSEQ}'">
				</c:if>&nbsp; <!-- 로그인 한 유저가 쓴 글만 수정할  수수 있게 버튼을표시  -->
			</div>
		</form>
	</div>
</div>


<%@ include file="../footer.jsp"%>