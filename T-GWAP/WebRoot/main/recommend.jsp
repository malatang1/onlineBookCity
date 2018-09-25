<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h2>
	编辑推荐
</h2>
<div id="__bianjituijian/danpin">
	
	<div class="second_c_02">
	<s:iterator value="pros">
		<div class="second_c_02_b1">
			<div class="second_c_02_b1_1">
				<a href='/T-GWAP/productImages/${product_pic}' target='_blank'><img src="/T-GWAP/productImages/${product_pic}" width="70" border="0" /> </a>
			</div>
			<div class="second_c_02_b1_2">
				<h3>
					<a href='#' target='_blank' title='书名'>${product_name }</a>
				</h3>
				<h4>
					作者：${author } 著
					<br />
					出版社：${publishing }&nbsp;&nbsp;&nbsp;&nbsp;出版时间：${FormatPublishTime }
				</h4>
				<h5>
					简介:${description }
				</h5>
				<h6>
					定价：￥${fixed_price }&nbsp;&nbsp;思源价：￥${dang_price }
				</h6>
				<div class="line_xx"></div>
			</div>
		</div>
		</s:iterator>
	</div>
	
</div>
