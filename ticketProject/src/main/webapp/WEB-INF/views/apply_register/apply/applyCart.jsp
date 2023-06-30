<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Go Cart or Not</title>
<style type="text/css">
.xans-product-basketadd { position:fixed; top:50%; left:50%; z-index:1000; width:450px; margin:-210px 0 0 -226px; }
.xans-product-basketadd h1 { height:35px; padding:0 35px 0 19px; color:#fff; font-size:14px; line-height:35px; background:#495164; }
.xans-product-basketadd .content { padding:160px 20px 65px; border-right:1px solid #757575; border-left:1px solid #757575; text-align:center; background:#fff url("http://img.echosting.cafe24.com/skin/base_ko_KR/product/bg_add_basket.gif") no-repeat 50% 68px; }
.xans-product-basketadd .content p { font-weight:bold; color:#2e2e2e; }
.xans-product-basketadd .btnArea { padding:9px 0; border:1px solid #757575; border-top:1px solid #d7d5d5; text-align:center; background:#fbfafa; cursor: pointer;}
.xans-product-basketadd .btnArea img:first-child { margin:0 1px 0 0; }
</style>



</head>
<body>
<form name="frm" action="ticket.do" method="post" class="form">

<div module="Product_BasketAdd" class="xans-product-basketadd">
    <!--@css(/css/module/product/basketAdd.css)-->
    <h1>장바구니 담기</h1>
    <div class="content">
        <p>${message}</p>
    </div>
    <div class="btnArea center">
        <img src="http://img.echosting.cafe24.com/skin/base_ko_KR/product/btn_go_basket.gif" alt="장바구니 이동" onclick="opener.location.href='ticket.do?command=cartList';self.close()"/>
        <img src="http://img.echosting.cafe24.com/skin/base_ko_KR/product/btn_continue_shopping.gif" alt="쇼핑계속하기" onclick="self.close()"/>
    </div>
</div>

</form>
</body>
</html>


