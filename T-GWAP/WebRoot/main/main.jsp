<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>思源图书 – 全球最大的中文网上书店</title>
		<link href="../css/book.css" rel="stylesheet" type="text/css" />
		<link href="../css/second.css" rel="stylesheet" type="text/css" />
		<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script>
			$(function(){
				$("#new").text("加载中...");
				$("#new").load("/T-GWAP/main/findnew",function(){
					$("#hot").text("加载中...");
					$("#hot").load("/T-GWAP/main/findhot",zoom);
				});
				$("#left").text("加载中...");
				$("#left").load("/T-GWAP/main/findcategory");
				$("#recommend").text("加载中...");
				$("#recommend").load("/T-GWAP/main/findrecommend",zoom1);
			});
			function zoom(){
				$("div.img a").hover(function(a){
					$("#d1").remove();
					var $div=$("<div id='d1' style='position: absolute;top:"+a.pageY+";left:"+a.pageX+";border:solid'><img src="+$(this).attr("href")+"></div>");
					$("body").append($div);
					$("#d1").children("img").width("160");
					$("#d1").children("img").height("220");
				},function(){
					$("#d1").remove();
				});
				$("div.img a").mousemove(function(a){
					$("#d1").css("left",a.pageX+5);
					$("#d1").css("top",a.pageY+5);
				});
			}
			function zoom1(){
				$("div.second_c_02_b1_1 a").hover(function(a){
					$("#d3").remove();
					var $div=$("<div id='d3' style='position: absolute;top:"+a.pageY+"; left:"+a.pageX+";border:solid'><img src="+$(this).attr("href")+"></div>");
					$("body").append($div);
					$("#d3").children("img").width("160");
					$("#d3").children("img").height("220");
				},function(){
					$("#d3").remove();
				});
				$("div.second_c_02_b1_1 a").mousemove(function(a){
					$("#d3").css("left",a.pageX+5);
					$("#d3").css("top",a.pageY+5);
				});
			}
		</script>		
	</head>
	<body>
		&nbsp;
		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->
		<div style="width: 962px; margin: auto;">
			<a href="#" target="_blank"><img
					src="../images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
			</div>
			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--推荐图书开始-->
				<div class="second_c_border1" id="recommend">
				</div>

				<!--推荐图书结束-->

				<!--热销图书开始-->
				<div class="book_c_border2" id="hot">
				</div>
				<!--热销图书结束-->

				<!--最新上架图书开始-->
				<div class="book_c_border2" id="new">
				</div>

				<!--最新上架图书结束-->

				<div class="clear">
				</div>
			</div>
			<!--中栏结束-->



			<!--右栏开始-->
			<%@include file="../main/saled.jsp"%>
			<!--右栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
