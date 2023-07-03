<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
		<h2>후기 게시판</h2>
		<h3>후기를 작성할 수 있는 게시판 입니다.</h3>
		<article>
			<form name="formm" method="post" class="review_form review_write" enctype="multipart/form-data">
				<fieldset>
					<legend></legend>
					<table id="reviewList">
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="title">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea rows="10" cols="" name="content"></textarea>
							</td>
						</tr>
						<tr>
							<th>이미지업로드</th>
							<td>
								<div class="filebox">
									<label for="file" style="width: 100%; text-align: left; padding-left: 20px;">첨부파일</label>
									<input type="file" id="file" name="image" style="display: none;">
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="clear"></div>
				<div id="buttons" class="rev_btn_box btn_box" style="float: right">
					<input type="button" value="작성완료" class="submit" onclick="review_write()">
					<input type="reset" value="다시작성" class="cancel">
					<input type="button" value=" 목록보기" class="submit" onClick="location.href='ticket.do?command=reviewList'">
				</div>
			</form>
		</article>
	</div>
</div>

<%@ include file="../footer.jsp"%>
