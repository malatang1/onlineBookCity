<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript">
	$(renew);
	function renew(){
		$.getJSON(
			"/T-GWAP/main/findsaled",
			function(data){
				var $h3=$("#NewProduct_1_o_t>h3");
				$h3.prevAll().remove();
				var no=1;
				$.each(data,function(index,product){
				var $pro=$("<h4>"+no+". "+product.product_name+"</h4>");
				no++;
				$pro.hover(function(a){
					$("#d2").remove();
					var $div=$("<div id='d2' style='position: absolute;top:"+a.pageY+"; left:"+a.pageX+";border:solid'><img src=../productImages/"+product.product_pic+"></div>");
					$("body").append($div);
					$("#d2").children("img").width("160");
					$("#d2").children("img").height("220");
				},function(){
					$("#d2").remove();
				});
				$pro.mousemove(function(a){
					$("#d2").css("left",a.pageX+5);
					$("#d2").css("top",a.pageY+5);
				});
				$h3.before($pro);
				});
			
			}
		);
		setTimeout("renew()",5000);
	}
</script>
<div class="book_right">
	<div class="book_r_border2" id="__XinShuReMai">
		<div class="book_r_b2_1x" id="new_bang">
			
				<img src="../images/xssj_banner.gif">
			
			<div id="NewProduct_1_o_t" onmouseover="">
				<h3 class="second">
					<span class="dot_r"> </span><a href="#" target="_blank">更多&gt;&gt;</a>
				</h3>
			</div>
			
		</div>
	</div>
</div>
