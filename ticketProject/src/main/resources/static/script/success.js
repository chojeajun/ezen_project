/**
 * 
 */

/* success reply 리뷰댓글작성 */
//function success_chk(sucseq) {
//	if(document.rev_formm.reply.value == "") {
//		alert("댓글 내용을 입력해주세요");
//		document.rev_formm.reply.focus();
//		return false;
//	}
//	var reply = document.rev_formm.reply.value;
//	document.rev_formm.action = "replyInsert?reply=" + reply + "&sucseq=" + sucseq;
//	document.rev_formm.submit();
//}

function success_chk() {
	if(document.rev_formm.reply.value == "") {
		alert("댓글 내용을 입력해주세요");
		return false;
	}
	return true;
}







 
 // 수정 버튼 클릭  // 수정 폼으로 이동
function go_supd(sucseq) {
	var url = "successEditForm&sucseq=" + sucseq
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
			document.formm.action ="successEdit";
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
		document.formm.action ="successDelete&sucseq=" + sucseq;
		document.formm.submit();
	}
}


/* success_board 이미지 업로드 */
function selectimg(){
	var opt = "toolbar=no,menubar=no,resizable=no,width=450,height=200";
	window.open( 's_selectimg' , 'selectimg',  opt);
}

function selectedimage(){
	document.frm.submit();
}







function success_write() {
	var successForm = document.formm
	if (successForm.title.value == "") {
		alert("제목을 입력해주세요")
		successForm.title.focus()
	} else if (successForm.content.value == "") {
		alert("내용을 입력해주세요")
		successForm.content.focus()
	}  else {
		successForm.action = "successWrite";
		successForm.submit();
	}

}