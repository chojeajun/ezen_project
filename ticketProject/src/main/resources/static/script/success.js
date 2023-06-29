/**
 * 
 */

/* success reply 리뷰댓글작성 */
function success_chk() {
	if(document.rev_formm.reply.value == "") {
		alert("댓글 내용을 입력해주세요");
		document.rev_formm.reply.focus();
		return false;
	}
	return true;
}
 
 // 수정 버튼 클릭  // 수정 폼으로 이동
function go_supd(sucseq) {
	var url = "ticket.do?command=successEditForm&sucseq=" + sucseq
	location.href = url
}
 
 // 작성 아이디와 로그인한 아이디가 일치하지 않는다면 수정하기 버튼 자체를 노출시키지 않음
function success_update() {
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
			document.formm.action ="ticket.do?command=successEdit";
			document.formm.submit();
		}
	}
}

/* 리뷰 삭제  */
function success_delete(sucseq) {
	console.log(document.formm.sucseq);
	console.log(document.formm.sucseq.value);
	if(confirm("정말 삭제하시겠습니까?")) {
		//return;
		document.formm.action ="ticket.do?command=successDelete&sucseq=" + sucseq;
		document.formm.submit();
	}
}

function success_write() {
	var reviewForm = document.formm
	if (reviewForm.title.value == "") {
		alert("제목을 입력해주세요")
		reviewForm.title.focus()
	} else if (reviewForm.content.value == "") {
		alert("내용을 입력해주세요")
		reviewForm.content.focus()
	}  else {
		reviewForm.action = "ticket.do?command=successWrite";
		reviewForm.submit();
	}

}