<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="update_box">
		<h2>Edit Profile</h2>
		<form method="post" name="joinForm">
			<input type="hidden" name="command" value="memberUpdate" />
			<fieldset>
				<legend>Basic Info</legend>
				<!-- id는 수정 대상이 아니면서, submit 할 떄 전송될 대상이므로, input type="text"로 하되 readonly로 설정해서
          수정을 금지시킴  -->
				<label>User ID</label>
				<input type="text" name="id" value="${loginUser.id }" readonly>
				
				<br> <label>Password</label>
				<input type="password" name="pwd">
				
				<br> <label>Retype Password</label>
				<input type="password" name="pwdCheck">
				
				<br> <label>Name</label>
				<input type="text" name="name" value="${loginUser.name }">
				
				<br> <label>Nickname</label>
				<input type="text" name="nickname" value="${ loginUser.nickname }">
				
				<br> <label>E-Mail</label>
				<input type="text" name="email" value="${ loginUser.email }">
				
				<br> <label>Phone Number</label>
				<input type="text" name="phone" value="${ loginUser.phone }">
				
				<br> <label>생년월일</label>
				<fmt:parseDate var="birth" value="${loginUser.birth}" pattern="yyyy-MM-dd"/>
				<fmt:formatDate var="birthday" value="${birth}" pattern="yyyy-MM-dd"/>
				<input type="text" name="birth" value="${ birthday}"> <!--  string 형 으로 넘어올때   -->
<%-- 				<fmt:formatDate var="resultRegDt" value="${loginUser.birth}" pattern="yyyy-MM-dd"/> --%>
<%-- 				<input type="text" value="${ resultRegDt }"><!--  넘어온 값이 date 일 때 --> --%>
				<br>
			</fieldset>
			<fieldset>
				<legend>Optional</legend>
				<label>Zip Code</label>
				<input type="text" name="zip_num" size="10" value="${loginUser.zip_num }">
				<input type="button" value="주소 찾기" class="dup" onclick="post_zip()">
				<br> <label>Address</label>
				<input type="text" name="address1" readonly="readonly" size="50" value="${loginUser.address1}">
				<br> <label>&nbsp;</label>
				<input type="text" name="address2" size="25" value="${loginUser.address2}">
				<br>
			</fieldset>
			<div class="clear"></div>
			<div id="buttons" class="update_btn_box">
				<input type="button" value="정보수정" class="submit btn_update_confirm" onclick="go_update();">
				<input type="reset" value="취소" class="cancel">
			</div>
		</form>
	</div>
</body>
</html>


<%@ include file="../footer.jsp"%>