<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div id="contentDetail">
	<c:forEach items="${ content }" var="contentVO">

		<div class="title">
			<h2>${ contentVO.TITLE }</h2>
		</div>
		<div class="image">
			<img src="${ contentVO.IMAGE }" style="margin-right: 80px; margin-left: 10px;" /> 
			<img src="${ contentVO.IMAGE }" style="margin-right: 80px;" />
			<img src="${ contentVO.IMAGE }" />
		</div>
		<div class="content_contents">
			<form name="frmcd">
				<!-- action="ticket.do" method="post" -->
				<!-- <input type="hidden" name="command" value="applyContentSelect" />  -->
				<input type="hidden" value="${ contentVO.CSEQ }" name="cseq" /> <input
					type="hidden" value="${ contentVO.CATEGORY }" name="category" /> <input
					type="hidden" value="${ contentVO.LOCATIONNUM }" name="locationNum" />
				<table>
					<tr style="line-height: 50px;">
						<th style="width: 10%;">아티스트</th>
						<th style="width: 45%;">내용</th>
						<th style="width: 12%;">공연 날짜</th>
						<th style="width: 9%;">공연 시간</th>
						<th style="width: 12%;">제한 연령</th>
						<th style="width: 12%;">티켓팅 날짜</th>
					</tr>
					<tr>

						<td><br />
						<br />${ contentVO.ARTIST }</td>
						<td
							style="height: 100px; line-height: 20px; font-size: 15px; font-weight: bold; text-align: left; border: 1px solid black;">${ contentVO.CONTENT }</td>

						<c:forEach items="${ list }" var="content">
							<%-- <td><br/><br/>${ content.CONTENTDATE }</td> --%>
							<%-- <td><br/><br/>${ content.CONTENTTIME }</td> --%>
						</c:forEach>
						<td><br />
						<br />${ contentVO.AGE }</td>
						<td><br />
						<br />
						<fmt:parseDate value="${ contentVO.TDATETIME }" var="tdatetime" pattern="yyyyMMddHHmmss" /> 
						<fmt:formatDate	value="${ tdatetime }" pattern="yyyy-MM-dd HH:mm" /></td>
					</tr>
				</table>
			</form>
		</div>

		<div id="buttons_contentDetail">
			<a
				href="ticket.do?command=applyContentSelect&cseq=${ contentVO.CSEQ }&category=${ contentVO.CATEGORY }&locationNum=${ contentVO.LOCATIONNUM }">
				<input type="submit" value="신청하기"
				onclick="return hoon_go_order_detail();" />
			</a> <input type="button" value="홈으로"
				onclick="location.href='redirect:/'" />
		</div>

	</c:forEach>
</div>

<%@ include file="../footer.jsp"%>