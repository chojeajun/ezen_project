<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp"%>
<%@ include file="../../include/admin/sub_menu.jsp"%>
<article>
<h1>공연수정</h1>  
<form name="frm" method="post">
<input type="hidden" name="cseq" value="${contentVO.CSEQ}">
<input type="hidden" name="oldfilename" value="${contentVO.IMAGE}">
<table id="list">
  <tr><th>공연분류</th><td colspan="5">
	    <select name="category">
		      <c:forEach items="${categoryList}" var="category" varStatus="status">
			        <c:choose>
				          <c:when test="${contentVO.category==status.count}">
				            	<option value="${status.count}" selected="selected">${category}</option>
				          </c:when>
			          <c:otherwise><option value="${status.count}">${category}</option></c:otherwise>
			        </c:choose>
		      </c:forEach>
	    </select></td></tr>
  <tr><th>공연명</th><td width="343" colspan="5">
      <input type="text" name="name" size="47" value="${content.TITLE}"></td></tr>
  <tr><th>베스트공연</th><td>
      <c:choose>
        <c:when test='${contentVO.BESTYN=="Y"}'>
            <input type="checkbox" name="bestyn" value="Y" checked="checked">
        </c:when>
        <c:otherwise>
        	<input type="checkbox" name="bestyn" value="N">
        </c:otherwise>
      </c:choose></td>        
    <th>사용유무</th><td>
      <c:choose>
        <c:when test='${contentVO.useyn=="Y"}'>
              <input type="checkbox" name="useyn" value="Y" checked="checked">
        </c:when>
      <c:otherwise>
      		<input type="checkbox" name="useyn" value="N">
      	</c:otherwise>
    </c:choose></td></tr>
  <tr><th>상세설명</th><td colspan="5">
      <textarea name="content" rows="8" cols="70" >${contentVO.content}</textarea></td></tr>
  <tr><th>공연이미지</th>
  
	    <td width="343" colspan="5" style="vertical-align:top;">
      현재 이미지 : <img src="content_images/${contentVO.image}" width="200pt"><br>
      <!-- <input type="file" name="image">  -->* 주의 : 이미지를 수정할때에만 선택해주세요
	   		<input type="hidden" name="image" id="image" >
	   		<div id="filename"></div>
		</td>
      </tr>    
</table>
<input class="btn" type="button" value="수정" onClick="go_mod_save('${contentVO.cseq}')">           
<input class="btn" type="button" value="취소" onClick="go_mov()">
</form> 

<div style="position:relative;  border:1px solid black; width:500px; margin:0 auto;">
	<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
		<input type="file" name="fileimage"><input type="button" id="myButton" value="추가">
	</form>
</div>

</article>
<%@ include file="../../include/admin/footer.jsp"%>