<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link href="../css/book_head090107.css" rel="stylesheet" type="text/css" />
<div class="head_container">
	<div class="head_welcome">
		<div class="head_welcome_right">
			<span class="head_welcome_text"> </span>
			<span class="head_welcome_text"> <span class="little_n">
					| <a href="#" name="mydangdang" class="head_black12a">我的书库</a> | <a
					href="#" name="helpcenter" class="head_black12a" target="_blank">帮助</a>
					| </span> </span>
			<div class="cart gray4012">
				<a href="/T-GWAP/cart/cart_list">购物车</a>
			</div>
		</div>
		<span class="head_toutext" id="logininfo">
		<s:if test="#session.user!=null">
		<b><s:property value="#session.user.nickname"/>您好，欢迎光临思源在线书库</b>
		[&nbsp;<a href="../main/loginout" class="b">登出</a>&nbsp;]
		</s:if>
		<s:else>
		[&nbsp;<a href="../user/login_form.jsp" class="b">登录</a>|<a
			href="../user/register_form.jsp" class="b">注册</a>&nbsp;]
		</s:else>
		
		</span>
	</div>
	<div class="head_head_list">
		<div class="head_head_list_left">
			<span class="head_logo"><a  href="../main/main.jsp" name="backtobook"><img style="width:240px;heigth:100px"
						src="../images/logo.png" /> </a> </span>
		</div>
		<div class="head_head_list_right">

			<div class="head_head_list_right_b">
			</div>
		</div>
	</div>
	<div class="head_search_div">
	</div>
	<div class="head_search_bg"></div>
</div>
