<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>
    
<script src="./script/admin.js"></script>
<style type="text/css">
.review_view_table {font-weight:700; font-size: 16px;}
.review_view_table th {width:10%; border:1px solid #444; text-align: center;}
.review_view_table td {border: 1px solid red; padding:10px;}
.area_box {padding-left:3px; border-bottom: 1px solid #999;}
input[type="text"] {width:100%;}

</style>
    <div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">관리자 상품 상세보기</h2>
		<form name="pro_update_frm" method="post" class="review_form" action="ticket.do" enctype="multipart/form-date">
		<input type="hidden" name="cseq" value="${ ContentVO.cseq }">
		<input type="hidden" name="oldimage" value="${ ContentVO.image }">
			<table class="review_view_table" style="width:100%;">
				<tr>
					<th>번호</th>
					<td>${ContentVO.cseq }</td>
				</tr>
				<tr>
					<th>타이틀</th>
					<td><input type="text" name="title" value="${ContentVO.title}"></td>
				</tr>
				<tr>
					<th>아티스트</th>
					<td><input type="text" name="artist" value="${ContentVO.artist}"></td>
				</tr>
				<tr>
					<th>이미지</th>
					<td><img src="${ ContentVO.image }" style="width:300px; height:400px;"><input type="file" name="image"></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td><input type="text" name="category" value="${ContentVO.category}"></td>
				</tr>
					<tr>
						<th>공연장소</th>
					<c:forEach items="${ locationList }" var="LocVO" end="0">
						<td><input type="text" name="artist" value="${LocVO.locationName}"></td>
					</c:forEach>
					</tr>
				<tr>
					<th>내용</th>
					<td align="left">
						<textarea style="width:100%;" cols="" rows="5"name="content" >${ContentVO.content}</textarea>
					</td>
				</tr>
				<tr>
					<th>관람등급</th>
					<td><input type="text" name="age" value="${ContentVO.age}"></td>
				</tr>
				<tr>
					<th>베스트</th>
					<td><input type="text" name="bestyn" value="${ContentVO.bestyn}"></td>
				</tr>
				<tr>
					<th>티켓팅 시간</th>
					<td><input type="text" name="tDateTime" value="${ContentVO.tDateTime}"></td>
				</tr>
				<c:forEach items="${ timeList }" var="timeVO">
					<tr>
						<th>공연 일자</th>
						<td><input type="text" name="contentDate" value="${timeVO.contentDate}"></td>
					</tr>
					<tr>
						<th>공연 시각</th>
						<td><input type="text" name="contentTime" value="${ timeVO.contentTime}"></td>
					</tr>
				</c:forEach>
			</table>
			<div class="clear"></div>
			<div class="rev_btn_box btn_box" style="float: left">
				<input type="button" value="메인으로" class="cancel" onClick="location.href='ticket.do?command=index'">
			</div>
			<div class="rev_btn_box btn_box" style="float: right">
				<input type="submit" value="수정완료" class="update" onclick="pro_update()">
				<input type="reset" value="리셋" class="cancel">
				
			</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp" %>