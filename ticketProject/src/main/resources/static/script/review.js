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


//  리뷰수정 버튼 클릭  // 수정 폼으로 이동
function go_upd(rseq) {
	var url = "ticket.do?command=reviewEditForm&rseq=" + rseq
	location.href = url
}

// 리뷰 댓글수정 수정 팝업? 
function go_rep_edit(rseq, repseq) {
	console.log( "리뷰번호" + rseq);
	console.log("댓글번호" + repseq);
	var url ="ticket.do?command=reviewReplyEditForm&rseq="  + rseq;
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
	window.open(url, opt );
}


//function checkPass(boardnum, popupWinName) {
//	var url = "board.do?command=boardPassForm&num=" + boardnum;
//	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
//	window.open(url, popupWinName, opt);
//}

function reply_update() {
	if(document.formm.content.value == "") {
		alert("내용 ㄱ?");
	} else {
		document.formm.action = "ticket.do?command=reviewReplyEdit"
		document.formm.submit();
	}
}


// 작성 아이디와 로그인한 아이디가 일치하지 않는다면 수정하기 버튼 자체를 노출시키지 않음
function review_update() {
	if(document.formm.title.value == "" ) {
		alert("제목은 필수 입력 사항입니다.");
		document.formm.title.focud();
		//return false;
	}else if(document.formm.content.value == "") {
		alert("내용은 필수 입력 사항입니다.");
		document.formm.content.focus();
		//return false;
	} else {
		if(confirm("수정 ㄱ?")) {
			document.formm.action ="ticket.do?command=reviewEdit";
			document.formm.submit();
		}
	}
}


/* 리뷰 삭제  */
function review_delete(rseq) {
	console.log(document.formm.rseq);
	console.log(document.formm.rseq.value);
	if(confirm("정말 삭제하시겠습니까?")) {
		//return;
		document.formm.action ="ticket.do?command=reviewDelete&rseq=" + rseq;
		document.formm.submit();
	}
}



//function review_chk_pwd(reviewrseq, popupWinName) {
//	var url = "ticket.do?command=reviewChkForm&rseq=" + reviewrseq;
//	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
//	window.open(url, popupWinName, opt);
//}




/* review reply 리뷰댓글작성 */
function reply_chk() {
	if(document.rev_formm.reply.value == "") {
		alert("댓글 내용을 입력해주세요");
		document.rev_formm.reply.focus();
		return false;
	}
	return true;
}









