<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp"%>
<%@ include file="../../include/admin/sub_menu.jsp"%>
<article>
<h1>공연 상세 보기</h1> 
<form name="frm" method="post">
<table id="list">  
    <tr><th align="center" >제목</th><td colspan="5">${contentVO.TITLE}</td></tr>
    <tr><th>상세설명</th><td colspan="5"><pre>${contentVO.CONTENT}</pre></td> </tr>
    <tr><th>공연이미지</th><td colspan="5" align="center">     
     <img src="content_images/${contentVO.IMAGE}" width="200px">    
     </td></tr>
</table>
<input class="btn"  type="button" value="수정" onClick="go_mod('${contentVO.cseq}')">
<input class="btn"  type="button" value="목록" onClick="go_mov()">           
</form>
</article>
<%@ include file="../../include/admin/footer.jsp"%>