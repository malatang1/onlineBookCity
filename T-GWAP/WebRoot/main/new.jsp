<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>最新上架图书
</h2>
<div class="book_c_04">
<s:iterator value="pros">
	<!--最新上架图书A开始-->
	<div class="second_d_wai">
		<div class="img">
			<a href="/T-GWAP/productImages/${product_pic}" target='_blank'>
			<img src="/T-GWAP/productImages/${product_pic}" border=0 /> 
			</a>
		</div>
		<div class="shuming">
			<a href="#" target="_blank"><s:property value="product_name"/></a><a href="#" target="_blank"></a>
		</div>
		<div class="price">
			定价：￥<s:property value="fixed_price"/>
		</div>
		<div class="price">
			思源价：￥<s:property value="dang_price"/>
		</div>
	</div>
	<div class="book_c_xy_long"></div>
	<!--最新上架A结束-->
</s:iterator>
</div>
<div class="clear"></div>