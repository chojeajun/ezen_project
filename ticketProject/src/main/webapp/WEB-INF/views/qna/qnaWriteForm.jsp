<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="review_img_box"></div>
<div id="review_box">
	<div class="review_content">
<h2> 1:1 고객 게시판 </h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>    
<form name="formm" method="post" 	action="qnaWrite">
    <fieldset><legend>Board Info</legend>
    	<label>Title</label><input type="text" name="subject"  size="60" ><br>
    	
    	<label>Secret mode</label>
    		<input name="passCheck" type="checkbox" value="secret" onclick="enabled()">
    			&nbsp;비밀글로 하기&nbsp;&nbsp;
    		<input name="pass" type="password" size="15" disabled="disabled"><br>
    	
    	<label>Content</label><textarea rows="8" cols="65" name="content" ></textarea><br>
    </fieldset>   
    <div class="clear"></div>
    <div id="buttons" style="float:right">
	    <input type="submit"  value="글쓰기"     class="submit"> 
    	<input type="reset"   value="취소"     class="cancel">
    	<input type="button"  value="목록보기"  class="submit"  onclick="qnaList'">
    </div>
    </form>
    </div>
    </div>

<%@ include file="../footer.jsp" %>