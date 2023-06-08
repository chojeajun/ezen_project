<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>
    
<script src="./script/admin.js"></script>
<style type="text/css">
.review_view_table {font-weight:700; font-size: 16px;}
.review_view_table th {width:10%; border:1px solid #444; text-align: center;}
.review_view_table td {border: 1px solid red; padding:10px;}
.area_box {padding-left:3px; border-bottom: 1px solid #999;}

</style>
    <div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">관리자 상품 상세보기</h2>
		<form name="pro_update_frm" method="post" class="review_form" action="ticket.do">
		<input type="hidden" name="cseq" value="${ ContentVO.cseq }">
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
					<tr>
						<th>공연장소</th>
					<c:forEach items="${ locationList }" var="LocVO" end="0">
						<td>${ LocVO.locationName }</td>
					</c:forEach>
					<c:forEach items="${ locationList }" var="LocVO">
						<tr>
							<th>좌석</th>
							<td><div class="area_box">${ LocVO.area }</div> <div>가격 : ${ LocVO.price }</div></td>
						</tr>
					</c:forEach>
					</tr>
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
				<c:forEach items="${ timeList }" var="timeVO">
					<tr>
						<th>공연 일자</th>
						<td>${ timeVO.contentDate }</td>
					</tr>
					<tr>
						<th>공연 시각</th>
						<td>${ timeVO.contentTime }</td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="clear"></div>
			<div class="rev_btn_box btn_box" style="float: left">
				<input type="button" value="메인으로" class="cancel" onClick="location.href='ticket.do?command=index'">
			</div>
			<div class="rev_btn_box btn_box" style="float: right">
				<input type="button" value="수정완료" class="update" onclick="go_pro_upd('${ ContentVO.cseq}')">
				
			</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp" %>