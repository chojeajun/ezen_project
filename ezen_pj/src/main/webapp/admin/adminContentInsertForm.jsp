<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>

<div id="review_box">
	<div class="review_content">
		<h2>공연 추가</h2>
		<article>
			<form name="formm" method="post" class="review_form review_write" enctype="multipart/form-data">
				<fieldset>
					<legend></legend>
					<table id="reviewList">
						<tr>
							<th>카테고리</th>
							<td>
								<select name="category">
								    <option value="">카테고리 선택</option>
								    <option value="1">콘서트</option>
								    <option value="2">뮤지컬</option>
								    <option value="3">스포츠</option>
								    <option value="4">페스티벌</option>
								    <option value="5">전시/행사</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>
								<input type="text"size=30  name="title">
							</td>
						</tr>
						<tr>
							<th>아티스트</th>
							<td>
								<input type="text" size=30 name="artist">
							</td>
						</tr>
						<tr>
							<th>위치</th>
							<td>
								<select name="location">
								    <option value="">공연장 선택</option>
								    <option value="1">KBS부산홀</option>
								    <option value="2">잠실 실내체육관</option>
								    <option value="3">올림픽공원 올림픽홀</option>
								    <option value="4">서울 잠실종합운동장 올림픽주경기장</option>
								    <option value="5">블루스퀘어 마스터카드홀</option>
								    <option value="6">수원종합운동장 실내체육관</option>
								    <option value="7">백암아트홀</option>
								    <option value="8">대구 엑스코 오디토리움</option>
								    <option value="9">예술의전당 오페라극장</option>
								    <option value="10">잠실야구장</option>
								    <option value="11">수원월드컵경기장</option>
								    <option value="12">고척스카이돔</option>
								    <option value="13">삼성라이온즈파크</option>
								    <option value="14">샤롯데씨어터</option>
								    <option value="15">블루스퀘어 신한카드홀</option>
								    <option value="16">서울 올림픽 공원 88잔디마당</option>
								    <option value="17">더현대서울 6층</option>
								    <option value="18">인사센트럴뮤지엄</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>공연 날짜</th>
							<td>
								<input type="date" name="date" id="date">
							</td>
						</tr>
						<tr>
							<th>공연 시간</th>
							<td>
								<input type="text"size=30  name="time" placeholder="11:00 형식">
							</td>
						</tr>
						<tr>
							<th>상세 설명</th>
							<td>
								<textarea rows="5" cols="100" name="content"></textarea>
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td>
								<input type="text" name="artist"size=30 >
							</td>
						</tr>
						<tr>
							<th>티켓팅 날짜,시간</th>
							<td>
								<input type="text" size=30 name="tDateTime" placeholder="형식:20230101120000">
							</td>
						</tr>
						<tr>
							<th>이미지업로드</th>
							<td>
								<div class="filebox">
									<input type="file" id="file" name="image">
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="clear"></div>
				<div id="buttons" class="rev_btn_box btn_box" style="float: right">
					<input type="button" value="작성완료" class="submit" onclick="insert_content()">
					<input type="reset" value="다시작성" class="cancel">
					<input type="button" value=" 목록보기" class="submit" onClick="location.href='ticket.do?command=adminProductList'">
				</div>
			</form>
		</article>
	</div>
</div>

<%@ include file="footer.jsp" %>