<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>思源在线书库 – 全球最大的中文网上书店</title>
		<script type="text/javascript" src="../js/prototype-1.6.0.3.js">
		</script>
		<link href="../css/book.css" rel="stylesheet" type="text/css" />
		<link href="../css/second.css" rel="stylesheet" type="text/css" />
		<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="../css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script>
			function buy(id){
				$.getJSON(
					"/T-GWAP/cart/buy",
					{"id":id},
					function(date){
						$("span[id^=cartinfo]").text("");
						if(date){
							$("#cartinfo_"+id).html("<span style='color:green'><img src='../images/onSuccess.gif' style='display:inline'/>购买成功!</span>");
						}else{
							$("#cartinfo_"+id).html("<span style='color:red'>购买失败!</span>")
						}
						window.setTimeout(function(){
								$("#cartinfo_"+id).html("<img src='../images/buttom_goumai.gif'/> ");
								
							},1000);
					}
				
				);
			
			}
		</script>
	</head>
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->

		<div style="width: 962px; margin: auto;">
			<a href="#"><img src="../images/default/book_banner_081203.jpg"
					border="0" /> </a>
		</div>
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='/T-GWAP/main/main.jsp'>思源图书</a> &gt;&gt;
			<font style='color: #cc3300'><strong>小说</strong> </font>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class="second_l_border2">
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
									<div class="second_fenlei">
										&middot;全部&nbsp;(${totalnum })
									</div>
								</div>
							</li>
							<div class="clear"></div>
							<s:iterator value="cats">
								<!--2级分类开始-->
								<li>
									<div>
										<div class="second_fenlei">
											&middot;
										</div>
										<div class="second_fenlei">
											<s:if test="cid==id">
												<a style="color: red;font-size: 20px;"
													href="/T-GWAP/main/booklist?cid=${id }&&pid=${parent_id}">${name}&nbsp;(${pnum
													})</a>
											</s:if>
											<s:else>
												<a href="/T-GWAP/main/booklist?cid=${id }&&pid=${parent_id}">${name}&nbsp;(${pnum
													})</a>
											</s:else>
										</div>
									</div>
								</li>
								<div class="clear"></div>
								<!--2级分类结束-->
							</s:iterator>
						</ul>

					</div>
					<div id="left2">
						<%@include file="../common/adv.jsp"%>
					</div>
				</div>

			</div>

			<!--左栏结束-->
			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						<select onchange='' name='select_order' size='1'
							class='list_r_title_ml'>
							<option value="">
								按上架时间 降序
							</option>
						</select>
						<div id="divTopPageNavi" class="list_r_title_text3">

							<!--分页导航开始-->
							<s:if test="page>1">
								<div class='list_r_title_text3a'>
									<a name="link_page_next"
										href="booklist.action?page=${page-1}&cid=${cid }&pid=${pid }">
										<img src='../images/page_up.gif' /> </a>
								</div>
							</s:if>
							<s:else>
								<div class='list_r_title_text3a'>
									<img src='../images/page_up_gray.gif' />
								</div>
							</s:else>

							<div class='list_r_title_text3b'>
								第${page }页/共${maxpage }页
							</div>
							<s:if test="page<maxpage ">
								<div class='list_r_title_text3a'>
									<a name=link_page_next
										href="booklist.action?page=${page+1}&cid=${cid }&pid=${pid }">
										<img src='../images/page_down.gif' /> </a>
								</div>
							</s:if>
							<s:else>
								<div class='list_r_title_text3a'>
									<img src='../images/page_down_gray.gif' />
								</div>
							</s:else>
							<!--分页导航结束-->
						</div>
					</div>
					<s:iterator value="pros">
						<!--商品条目开始-->

						<div class="list_r_line"></div>
						<div class="clear"></div>

						<div class="list_r_list">
							<span class="list_r_list_book"><a name="link_prd_img"
								href='#'> <img src="../productImages/${product_pic }" /> </a> </span>
							<h2>
								<a name="link_prd_name" href='#'>${product_name }</a>
							</h2>
							<h3>
								顾客评分：100
							</h3>
							<h4 class="list_r_list_h4">
								作 者:
								<a href='#' name='作者'>${author }</a>
							</h4>
							<h4>
								出版社：
								<a href='#' name='出版社'>${publishing }</a>
							</h4>
							<h4>
								出版时间：${FormatPublishTime }
							</h4>
							<h5>
								${description }
							</h5>
							<div class="clear"></div>
							<h6>
								<span class="del">￥${fixed_price}</span>
								<span class="red">￥${dang_price}</span>
								节省：￥${fixed_price-dang_price }
							</h6>
							<span class="list_r_list_button"></span>
							<a id="cartinfo_${id}" href="javascript:;" onclick="buy(${id});">
								<img src='../images/buttom_goumai.gif' /> </a>
							<span style="color:red;"></span>
						</div>
						<div class="clear"></div>
						<!--商品条目结束-->
					</s:iterator>
					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>

				</div>
				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
