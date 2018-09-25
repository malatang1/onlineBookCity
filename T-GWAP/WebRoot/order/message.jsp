<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div class="fill_message">

			<table class="tab_login">
				<tr>
					<td valign="top" class="w1" style="text-align: left">
						<b>序号</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>商品名称</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>商品单价</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>商品数量</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>小计</b>
					</td>
				</tr>

				<!-- 订单开始 -->
				<s:iterator value="items" var="item" status="stat">
					<tr>
						<td valign="top">
							${stat.index+1 }
						</td>
						<td valign="top">
							${item.pro.product_name }
						</td>
						<td valign="top">
							￥${item.pro.dang_price}
						</td>
						<td valign="top">
							${item.qty}
						</td>
						<td valign="top">
							￥${item.pro.dang_price*item.qty}
						</td>
					</tr>
					</s:iterator>
				<!-- 订单结束 -->
				<tr>
					<td valign="top" class="w1" style="text-align: left" colspan="5">
						<b>总价￥${cost}</b>
					</td>
				</tr>
			</table>
			<br />
			<br />
			<br />
			<div class="login_in">
				<a href="../cart/cart_list.jsp"><input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="取消" /></a>
			
				<a href="address_form.jsp"><input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="下一步" /></a>
		
			</div>

		</div>