<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:if test="items.size()!=0">
<div id="divCartItemsRemoved" class="del_merch">
			
			<div class="del_title">
				您已删除以下商品，如果想重新购买，请点击“恢复”
			</div>
			<table class=tabl_del id=del_table>
				<tbody>
	
					<s:iterator value="items" var="item">
					<tr>
						<td width="58" class=buy_td_6>
							&nbsp;
						</td>
						<td width="365" class=t2>
							<a href="#">${item.pro.product_name }</a>
						</td>
						<td width="106" class=buy_td_5>
							￥${item.pro.fixed_price}
						</td>
						<td width="134" class=buy_td_4>
							<span>￥${item.pro.dang_price}</span>
						</td>
						<td width="56" class=buy_td_1>
							<a href="/T-GWAP/cart/recover?id=${item.pro.id }">恢复</a>
						</td>
						<td width="16" class=objhide>
							&nbsp;
						</td>
					</tr>
					</s:iterator>


					<tr class=td_add_bord>
						<td colspan=8>
							&nbsp;
						</td>
					</tr>
				


				</tbody>
			</table>
			
		</div>
	</s:if>