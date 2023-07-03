<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="reply_update_box" style="width: 1000px; height: 100%; margin: 0 auto;">
	<form name="formm" method="post" class="review_form">
		<input type="hidden" name="rseq" value="${ reviewVO.rseq }">
		<input type="hidden" name="content" value="${ reply.replycontent}">
		<c:set var="now" value="<%=new java.util.Date()%>" />
		<table class="reply_box">
			<tr style="height: 30px; border-bottom: 1px solid #ddd;">
				<th style="width: 15%;">댓글 작성자</th>
				<th style="width: 15%; border-right: 1px solid #ddd; border-left: 1px solid #ddd;">작성일</th>
				<th style="width: 60%;">댓글내용</th>
			</tr>
			<c:forEach items="${ replyList }" var="reply">
				<tr align="center" style="height: 30px;">
					<td style="line-height: 30px;">${ reply.id }</td>
					<td style="line-height: 30px; border-right: 1px solid #ddd; border-left: 1px solid #ddd;">
						<fmt:formatDate value="${ reply.writedate }" pattern="MM/dd HH:mm" />
					</td>
					<td style="line-height: 30px;" align="left">
						<input type="text" value="${ reply.replycontent }" name="content" style="width: 700px; height: 100%; padding: 10px">
					</td>
					<td class="btn_del" style="line-height: 0;">


						<c:if test="${ reply.id == loginUser.id }">
							<input type="button" value="수정" onclick="reply_update()">
						</c:if>
						&nbsp;
						<!-- 로그인 한 유저가 쓴 댓글만 삭제할 수 있게 버튼을표시  -->
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</div>
