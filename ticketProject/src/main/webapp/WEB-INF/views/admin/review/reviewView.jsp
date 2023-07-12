<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp"%>
<%@ include file="../../include/admin/sub_menu.jsp"%>

<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">후기 게시판</h2>
		<form name="rev_formm" method="post" class="review_form" action="addReply">
			<input type="hidden" name="rseq" value="${ reviewVO.RSEQ }">
			<input type="hidden" name="mseq" value="${ loginUser.MSEQ }">
			<table class="review_view_table">
				<tr>
					<th>번호</th>
					<td>${reviewVO.RSEQ }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${reviewVO.ID }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td width="200">${reviewVO.TITLE}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td align="left">
						<fmt:parseDate var="parseDate" value="${reviewVO.INDATE}" pattern="yyyy-MM-dd" />
						<fmt:formatDate var="resultdate" value="${parseDate}" pattern="yyyy-MM-dd" />
						${reviewVO.INDATE}
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${ reviewVO.READCOUNT }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td align="left">
						<textarea cols="" rows="10" readonly="readonly" >${reviewVO.CONTENT}</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td align="left" style=" color: white;">
					<c:choose>
							<c:when test="${empty reviewVO.IMGFILENAME}">
								<img src="/upload/noname.jpg"  width="250">
							</c:when>
							<c:otherwise>
								<img src="/upload/${reviewVO.IMGFILENAME}" width="250">
							</c:otherwise>
					</c:choose>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<th>댓글</th> -->
<%-- 					<td align="left" style=" color: white;">${reviewVO.reply } --%>
<!-- 				</tr>		 -->
			</table>
			<!--  리뷰 댓글box  -->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<c:if test="${ replyList.size() != 0 }">
			<table class="reply_box">
				<tr style="height:30px; border-bottom:1px solid #ddd;">
					<th style="width:20%;">댓글 작성자</th>
					<th style="width:20%; border-right:1px solid #ddd; border-left:1px solid #ddd;">작성일</th>
					<th style="width:60%;">댓글내용</th>
				</tr>
				<c:forEach items="${ replyList }" var="reply">
					<tr align="center" style="height:30px;">
						<td style="line-height:30px;">${ reply.ID }</td>
						<td style="line-height:30px; border-right:1px solid #ddd; border-left:1px solid #ddd;" ><fmt:formatDate value="${ reply.WRITEDATE }" pattern="MM/dd HH:mm" /></td>
						<td style="line-height:30px;" align="left">&nbsp;${ reply.REPLYCONTENT }</td>
						<td style="line-height:30px;">
							<input type="button" value="삭제" onclick="location.href='reviewdeleteReply1?repseq=${ reply.REPSEQ }&rseq=${ reviewVO.RSEQ }'">
						</td>
					</tr>
				</c:forEach>
			</table>

			<!--  
			<table>
			<tr>
				<th style="width:20%;">작성자</th>
				<th style="width:20%;">작성일</th>
				<th style="width:60%;">내용</th>
			</tr>
			<tr align="center">
				<td>${ loginUser.ID }<input type="hidden" name="id" value="${ loginUser.ID }"></td>
				<td><fmt:formatDate value="${ now }" pattern="MM/dd HH:mm"/></td>
				<td><input type="text" name="replycontent" size="80"></td>
				<td><input type="submit" value="답글 작성" onclick="reply_chk();"></td>
			</tr>
		</table> -->
			<div class="clear"></div>
		<!-- 	<div class="rev_btn_box btn_box" style="float: left">
				<input type="button" value="메인으로" class="cancel" onClick="location.href='/'">
			</div> -->
			 
			<!--  리뷰 수정box -->
			

			</c:if>
			<div class="clear"></div>
			<div id="buttons" class="rev_btn_box btn_box" style="float: right">
				<input type="button" value="목록보기" class="submit" onClick="location.href='reviewList1?first=y'">
			</div>
		</form>
	</div>
</div>


<%@ include file="../../include/admin/footer.jsp"%>