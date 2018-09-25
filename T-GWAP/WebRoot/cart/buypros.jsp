<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript">
	function update(id){
		var val=$("#input_"+id).val();
		var reg=/^\d+$/;
		if(reg.test(val)){
		window.location="/T-GWAP/cart/update?id="+id+"&qty="+val;
		}else{
			$("#input_"+id).val("");
			alert("请输入数字");
		}
	}
</script>
<div id="div_choice" class="choice_merch">
	<s:if test="items.size()==0">
		<div id="div_no_choice">
			<div class="choice_title"></div>
			<div class="no_select">
				您还没有选购商品
			</div>
			<div style="margin-left: 420px;">
			<a href='../main/main.jsp'>开始选购商品>></a>
			</div>
		</div>
		
	</s:if>
	<s:else>
		<h2 id="cart_tips">
			您已选购以下商品
		</h2>
		<div class="choice_bord">
			<table class="tabl_buy" id="tbCartItemsNormal">
				<tr class="tabl_buy_title">
					<td class="buy_td_6">
						<span>&nbsp;</span>
					</td>
					<td>
						<span class="span_w1">商品名</span>
					</td>
					<td class="buy_td_5">
						<span class="span_w2">市场价</span>
					</td>
					<td class="buy_td_4">
						<span class="span_w3">思源价</span>
					</td>
					<td class="buy_td_1">
						<span class="span_w2">数量</span>
					</td>
					<td class="buy_td_2">
						<span>变更数量</span>
					</td>
					<td class="buy_td_1">
						<span>删除</span>
					</td>
				</tr>
				<tr class='objhide' over="no">
					<td colspan="8">
						&nbsp;
					</td>
				</tr>

				<!-- 购物列表开始 -->
				<s:iterator value="items" var="item">
					<tr class='td_no_bord'>
						<td style='display: none'>
							${item.pro.id }
						</td>
						<td class="buy_td_6">
							<span class="objhide"><img /> </span>
						</td>
						<td>
							<a href="#">${item.pro.product_name }</a>
						</td>
						<td class="buy_td_5">
							<span class="c_gray">￥${item.pro.fixed_price}</span>
						</td>
						<td class="buy_td_4">
							&nbsp;&nbsp;
							<span>￥${item.pro.dang_price}</span>
						</td>
						<td class="buy_td_1">
							&nbsp;&nbsp;${item.qty }
						</td>

						<td>
							<input id="input_${item.pro.id }" class="del_num" type="text"
								size="3" maxlength="4" />
							<a href="javascript:;" onclick="update(${item.pro.id })">变更</a>
						</td>
						<td>
							<a href="/T-GWAP/cart/delete?id=${item.pro.id }">删除</a>
						</td>
					</tr>
				</s:iterator>
				<!-- 购物列表结束 -->
			</table>
	
	<div class="choice_balance">
		<div class="select_merch">
			<a href='../main/main.jsp'> 继续选购商品>></a>
		</div>
		<div class="total_balance">
			<div class="save_total">
				您共节省：
				<span class="c_red"> ￥<span id="total_economy">${saveprice}</span>
				</span>
				<span id='total_vip_economy' class='objhide'> ( 其中享有优惠： <span
					class="c_red"> ￥<span id='span_vip_economy'>0.00</span> </span> ) </span>
				<span style="font-size: 14px">|</span>
				<span class="t_add">商品金额总计：</span>
				<span class="c_red_b"> ￥<span id='total_account'>${cost}</span>
				</span>
			</div>
			<div id="balance" class="balance">
				<a name='checkout' href='../order/order_info.jsp'> <img
						src="../images/butt_balance.gif" alt="结算" border="0" title="结算" />
				</a>
			</div>
		</div>
	</div>
	</s:else>
</div>
