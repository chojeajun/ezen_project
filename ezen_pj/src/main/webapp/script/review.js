//function review_up() {
//	var result = confirm("등록하시겠습니까?");
//	
//	if(result == true) {
//		
//		alert("확인누름");
//		document.formm.action = "ticket.do?command=reviewWrite";
//	} else {
//		alert("취소누름");
//		document.formm.action = "ticket.do?command=reviewList";
//	}
//	
//}
function review_write() {
	var reviewForm = document.formm
	if (reviewForm.title.value == "") {
		alert("제목을 입력해주세요")
		reviewForm.title.focus()
	} else if (reviewForm.content.value == "") {
		alert("내용을 입력해주세요")
		reviewForm.content.focus()
	}  else {
		reviewForm.action = "ticket.do?command=reviewWrite";
		reviewForm.submit();
	}

}