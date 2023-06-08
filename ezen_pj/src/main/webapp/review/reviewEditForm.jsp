<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">후기 게시판</h2>
		<h3>고객님들의 티켓팅 후기를 상세히 볼 수 있는 게시판 입니다.</h3>
		<form name="formm" method="post" class="review_form"  enctype="multipart/form-data">
			<input type="hidden" name="rseq" value="${ reviewVO.rseq }">
			<input type="hidden" name="oldimage" value="${ reviewVO.image }">
			<table class="review_update_table review_view_table"> 
				<tr>
					<th>작성자</th>
					<td>${ reviewVO.id }
						<input type="hidden" name="id" value="${ loginUser.id }">
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="title" size="20" value="${ reviewVO.title }">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="15" cols="70" name="content">${ reviewVO.content }</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<img src="./images/content/${ reviewVO.image }" width="200">
						<input type="file" name="image" />
					</td>
				</tr>
			</table>
			<div id="buttons" class="rev_btn_box btn_box" style="float:right;">
				<input type="submit" value="수정" onclick="review_update()">
				<input type="submit" value="삭제" onclick="review_delete(${ reviewVO.rseq })">
				<input type="reset" value="다시작성">
				<input type="button" value="돌아가기" onclick="location.href='ticket.do?command=reviewView&rseq=${ reviewVO.rseq }'">
			</div>
		</form>
	</div>
</div>



<%@ include file="../footer.jsp"%>