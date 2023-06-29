/* 탑버튼 */

$(function() {
    $('#btn_top').on('click',function() {
		$('html, body').stop().animate({scrollTop:0},600);
	});
});

function contentSearch(){
	
	var key = document.log_frm.key.value;
	document.log_frm.action = "ticket.do?command=contentSearch&key=" + key;
	document.log_frm.submit();
	
}