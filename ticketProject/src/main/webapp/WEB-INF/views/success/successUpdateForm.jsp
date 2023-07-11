<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2 class="review_title">후기 게시판</h2>
		<h3>고객님들의 티켓팅 후기를 상세히 볼 수 있는 게시판 입니다.</h3>
		<form name="formm" method="post" class="review_form" action="successUpdate">
			<input type="hidden" name="sucseq" value="${ dto.sucseq }">
			<table class="review_update_table review_view_table"> 
				<tr>
					<th>작성자</th>
					<td>${loginUser.ID }
						<input type="hidden" name="id" value="${ loginUser.ID }">
					</td>
				</tr>
				<tr>
					<th>비번</th>
					<td>
						<input type="password" name="pwd" size="12">* 필수 (게시물 수정 삭제시 필요합니다.)
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="title" size="20" value="${ dto.title }">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="15" cols="70" name="content">${ dto.content }</textarea>
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
					<c:choose>
						<c:when test="${empty dto.imgfilename}">
							<img src="/upload/noname.jpg" height="80" width="80"><br></c:when>
						<c:otherwise>
							<img src="/upload/${dto.imgfilename}" height="80" width="80"><br></c:otherwise>
					</c:choose>
					<div id="image" style="float:left"></div><input type="hidden" name="imgfilename" >
					<input type="button" value="파일선택"  onClick="selectimg();" >
					<img src="" id="previewimg" width="150" style="display:none" /><br>파일을 수정하고자 할때만 선택하세요
					<input type="hidden" name="oldfilename" value="${dto.imgfilename}"></td>	</tr>
			</table><br>
			<div id="buttons" class="rev_btn_box btn_box" style="float:right;">
				<input type="submit" value="수정">
				<input type="button" value="돌아가기" onclick="location.href='successView?sucseq=${ dto.sucseq }'">
			</div>
			<br>${message}
		</form>
	</div>
</div>



<%@ include file="../footer.jsp"%>