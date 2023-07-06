<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
	if(window.name == "update") {
		window.opener.location.href = "reviewUpdateForm?rseq=${rseq}";
	}
	else if( window.name == "delete" ){
		var a = confirm("정말로 삭제하시겠습니까?");
		if(a) window.opener.location.href = "reviewDelete?rseq=${rseq}";
	}
	self.close();
</script>


</body>
</html>