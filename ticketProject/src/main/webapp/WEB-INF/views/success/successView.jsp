<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">성공 후기 게시판</h2>
		<h3>고객님들의 성공 후기를 상세히 볼 수 있는 게시판 입니다.</h3>
		<form name="rev_formm" method="get" class="review_form" action="ticket.do">

			<input type="hidden" name="command" value="successReply">
			<input type="hidden" name="rseq" value="${ SuccessVO.sucseq }">
			
			<table class="review_view_table">
			
			
				<tr>
					<th>번호</th>
					<td>${SuccessVO.sucseq }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${SuccessVO.id }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td width="200">${SuccessVO.title}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td align="left">
						<fmt:parseDate var="parseDate" value="${SuccessVO.indate}" pattern="yyyy-MM-dd" />
						<fmt:formatDate var="resultdate" value="${parseDate}" pattern="yyyy-MM-dd" />
						${SuccessVO.indate}
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${ SuccessVO.readcount }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td align="left">
						<textarea cols="" rows="10" readonly="readonly" >${SuccessVO.content}</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td align="left" style=" color: white;"><img src="./images/content/${SuccessVO.image }" style="width:200px; "></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<th>댓글</th> -->
<%-- 					<td align="left" style=" color: white;">${successVO.reply } --%>
<!-- 				</tr>		 -->

			</table>
			<!--  리뷰 댓글box  -->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<table class="reply_box">
				<tr style="height:30px; border-bottom:1px solid #ddd;">
					<th style="width:15%;">댓글 작성자</th>
					<th style="width:15%; border-right:1px solid #ddd; border-left:1px solid #ddd;">작성일</th>
					<th style="width:60%;">댓글내용</th>
				</tr>
				<c:forEach items="${ replyList }" var="reply">
					<tr align="center" style="height:30px;">
						<td style="line-height:30px;">${ reply.id }</td>
						<td style="line-height:30px; border-right:1px solid #ddd; border-left:1px solid #ddd;" ><fmt:formatDate value="${ reply.writedate }" pattern="MM/dd HH:mm" /></td>
						<td style="line-height:30px;" align="left">&nbsp;${ reply.successcontent }</td>
						<td class="btn_del" style="line-height:1;">
							<c:if test="${ reply.id == loginUser.id }">
								<input type="button" value="삭제" onclick="location.href='ticket.do?command=successReplyDelete&srseq=${ reply.srseq }&sucseq=${ SuccessVO.sucseq }'">
							</c:if>
							&nbsp;
							<!-- 로그인 한 유저가 쓴 댓글만 삭제할 수 있게 버튼을표시  -->
						</td>
					</tr>
				</c:forEach>
			</table>
			<table class="new_reply_box">
			<tr>
				<th style="width:15%;">작성자</th>
				<th style="width:15%;">작성일</th>
				<th style="width:70%;">내용</th>
				<th width="">추가</th>
			</tr>
			<tr align="center">
				<td>${ loginUser.id }<input type="hidden" name="id" value="${ loginUser.id }"></td>
				<td><fmt:formatDate value="${ now }" pattern="MM/dd HH:mm"/></td>
				<td><input type="text" name="reply" size="80"></td>
				<td><input type="submit" value="답글 작성" onclick="return success_chk();"></td>
			</tr>
		</table>
			<div class="clear"></div>
			<div class="rev_btn_box btn_box" style="float: left">
				<input type="button" value="메인으로" class="cancel" onClick="location.href='ticket.do?command=index'">
			</div>
			
			<!--  리뷰 수정box -->
			<div id="buttons" class="rev_btn_box btn_box" style="float: right">
				<input type="button" value="목록보기" class="submit" onClick="location.href='ticket.do?command=successList'">
				<c:if test="${ successVO.id == loginUser.id }">
					<input type="button" value="수정하기" class="review_update_btn" onclick="go_supd('${ successVO.sucseq}')">
				</c:if>&nbsp; <!-- 로그인 한 유저가 쓴 글만 수정할  수수 있게 버튼을표시  -->
			</div>

		</form>
	</div>
</div>
<%@ include file="../footer.jsp"%>