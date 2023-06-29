/**
 * 
 */

/* 신청하기 버튼 */

function hoon_go_order_detail() {
	
	sessionStorage.removeItem('selectedCseq');
	sessionStorage.removeItem('selectedCategory');
	sessionStorage.removeItem('selectedlocationNum');

	var cseq = document.frmcd.cseq.value;
	var category = document.frmcd.category.value;
	var locationNum = document.frmcd.locationNum.value;
	
	
	sessionStorage.setItem('selectedCseq', cseq);
	sessionStorage.setItem('selectedCategory', category);
	sessionStorage.setItem('selectedLocationNum', locationNum);
	
	
	return true;
	
}