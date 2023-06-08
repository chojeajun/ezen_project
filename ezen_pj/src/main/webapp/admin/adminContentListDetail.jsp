<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>
    
<%--     			<c:set var="now" value="<%=new java.util.Date()%>" /> --%>
    
<style type="text/css">

.review_view_table th {width:10%; border:1px solid #444; text-align: center;}
.review_view_table td {border: 1px solid red; padding:5px;}

</style>
    <div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">관리자 상품 상세보기</h2>
		<form name="rev_formm" method="post" class="review_form" action="ticket.do">
			<table class="review_view_table" style="width:100%;">
				<tr>
					<th>번호</th>
					<td>${ContentVO.cseq }</td>
				</tr>
				<tr>
					<th>타이틀</th>
					<td>${ContentVO.title}</td>
				</tr>
				<tr>
					<th>아티스트</th>
					<td>${ ContentVO.artist }</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td><img src="${ ContentVO.image }" style="width:300px; height:400px;"></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>${ ContentVO.category }</td>
				</tr>
				<c:forEach items="locationList" var="LocationVO">
				<tr>
					<th>공연장소</th>
					<td>${ LocationVO.locationName }</td>
				</tr>
				</c:forEach>
				<tr>
					<th>내용</th>
					<td align="left">
						<textarea style="width:100%;" cols="" rows="5" readonly="readonly" >${ContentVO.content}</textarea>
					</td>
				</tr>
				<tr>
					<th>관람등급</th>
					<td>${ ContentVO.age }</td>
				</tr>
				<tr>
					<th>베스트</th>
					<td>${ ContentVO.bestyn }</td>
				</tr>
				<tr>
					<th>티켓팅 시간</th>
					<td>${ ContentVO.tDateTime }</td>
				</tr>
			</table>
			
			<div class="clear"></div>
			<div class="rev_btn_box btn_box" style="float: left">
				<input type="button" value="메인으로" class="cancel" onClick="location.href='ticket.do?command=index'">
			</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp" %>