<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp"%>
<%@ include file="../../include/admin/sub_menu.jsp"%>

<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">성공 후기 게시판</h2>
		<h3>고객님들의 성공 후기를 상세히 볼 수 있는 게시판 입니다.</h3>
		<form name="rev_formm" method="post" class="review_form" action="replyInsert">
			<input type="hidden" name="mseq" value="${ loginUser.MSEQ }">
			<input type="hidden" name="sucseq" value="${ SuccessVO.SUCSEQ }">
			<table class="review_view_table">
				<tr>
					<th>번호</th>
					<td>${SuccessVO.SUCSEQ }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${SuccessVO.ID }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td width="200">${SuccessVO.TITLE}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td align="left">
						<fmt:parseDate var="parseDate" value="${SuccessVO.INDATE}" pattern="yyyy-MM-dd" />
						<fmt:formatDate var="resultdate" value="${parseDate}" pattern="yyyy-MM-dd" />
						${SuccessVO.INDATE}
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${ SuccessVO.READCOUNT }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td align="left">
						<textarea cols="" rows="10" readonly="readonly" >${SuccessVO.CONTENT}</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td align="left" style=" color: white;">
					<c:choose>
							<c:when test="${empty SuccessVO.IMGFILENAME}">
								<img src="/upload/noname.jpg"  width="250">
							</c:when>
							<c:otherwise>
								<img src="/upload/${SuccessVO.IMGFILENAME}" width="250">
							</c:otherwise>
					</c:choose>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<th>댓글</th> -->
<%-- 					<td align="left" style=" color: white;">${successVO.reply } --%>
<!-- 				</tr>		 -->

			</table>
			<!--  리뷰 댓글box  -->
			<%! int status = 0; %>
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<table class="reply_box">
				<tr style="height:30px; border-bottom:1px solid #ddd;">
					<th style="width:15%;">댓글 작성자</th>
					<th style="width:15%; border-right:1px solid #ddd; border-left:1px solid #ddd;">작성일</th>
					<th style="width:60%;">댓글내용</th>
					<th style="width:10%">삭제</th>
				</tr>
				<c:forEach items="${ replyList }" var="reply">
					<tr align="center" style="height:30px;">
						<c:forEach items="${ replyInsertId }" var="name" begin="<%=status %>" end ="<%=status %>">
							<td style="line-height:30px;">${ name.ID }</td>
						</c:forEach>
						<td style="line-height:30px; border-right:1px solid #ddd; border-left:1px solid #ddd;" ><fmt:formatDate value="${ reply.WRITEDATE }" pattern="MM/dd HH:mm" /></td>
						<td style="line-height:30px;" align="left">&nbsp;${ reply.SUCCESSCONTENT }</td>
						<td class="btn_del" style="line-height:1;">
							<input type="button" value="삭제" onclick="location.href='successReplyDelete1?srseq=${ reply.SRSEQ }&sucseq=${ SuccessVO.SUCSEQ }'">
							&nbsp;
							<!-- 로그인 한 유저가 쓴 댓글만 삭제할 수 있게 버튼을표시  -->
						</td>
					</tr>
					<% status = 0; %>
				</c:forEach>
			</table>
			<div class="clear"></div>
			<div id="buttons" class="rev_btn_box btn_box" style="float: right">
				<input type="button" value="목록보기" class="submit" onClick="location.href='successList1?first=y'">
			</div>

		</form>
	</div>
</div>
<%@ include file="../../include/admin/footer.jsp"%>