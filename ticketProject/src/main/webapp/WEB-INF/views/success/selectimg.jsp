<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/css/board.css" >
<script src="/script/board.js"></script>

</head>
<body>

<div id="wrap" align="center" style="width:100%">

	<form name="frm" action="s_fileupload"  method="post" enctype="multipart/form-data">
		<h1>파일 선택</h1>
		<input type="file" name="image" onchange="selectedimage();">
	</form> 

</div>

</body>
</html>