<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../include/admin/header.jsp"%>
<%@ include file="../../include/admin/sub_menu.jsp"%>
<article>
<h1>공연등록</h1>  
<form name="frm" method="post" >
<table id="list">
		<tr> <%--<th>공연분류</th><td colspan="5">
		<select name="category">
    		<c:forEach items="${categoryList}" var="category" varStatus="status">
      			<option value="${status.count}">${category}</option>
   			</c:forEach>
  		</select></td>--%>
 
	<tr><th>공연명</th> <td width="343" colspan="5">
	       <input type="text" name="name" size="47" maxlength="100" > </td></tr>
	<tr><th>상세설명</th><td colspan="5">
	      <textarea name="content" rows="8" cols="70" ></textarea></td></tr>

    <tr height="200">
    	<th>공연이미지</th>
	    <td width="343" colspan="5" style="vertical-align:top;">
	   		<input type="hidden" name="image" id="image" >
	   		<div id="filename"></div>
		</td>
	</tr>
      
</table>
<input class="btn4" type="button" value="등록" onClick="go_save()">           
<input class="btn5" type="button" value="취소" onClick="go_mov()"><br/>
<div id="msg" ></div>
</form> 

<div style="position:relative; top:-70px; ">
		<form style="margin-left:250px;" name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
					<input type="file" name="fileimage"><input type="button" id="myButton" value="추가">
		</form>
	</div>

</article>
<%@ include file="../../include/admin/footer.jsp"%>






