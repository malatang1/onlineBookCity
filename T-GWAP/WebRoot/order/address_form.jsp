<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成订单 - 思源书库</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script>
			$(function(){
				$.getJSON(
					"/T-GWAP/order/getReceiveList",
					function(receives){
						var $select=$("#address");
						$.each(receives,function(index,receive){
							var $option=$("<option value='"+receive.id+"'>"+receive.receive_name+"</option>");
							$select.append($option);
						});
					}
				);
				$("#address").change(function(){
					var val=$("#address").val();
					if(val==0){
						$("#receiveName").val("");
						$("#fullAddress").val("");
						$("#postalCode").val("");
						$("#phone").val("");
						$("#mobile").val("");
					}else{
						$.getJSON(
						"/T-GWAP/order/getReceive",
						{"id":val},
						function(receive){
									$("#receiveName").val(receive.receive_name);
									$("#fullAddress").val(receive.full_address);
									$("#postalCode").val(receive.postal_code);
									$("#phone").val(receive.phone);
									$("#mobile").val(receive.mobile);
						});
					}
							
				});
	function checkName(){
	$("#receiveName_info").html("");//清空错误提示
	var receiveNameTxt = $("#receiveName").val();
	if(receiveNameTxt==""){
		$("#receiveName_info").append("收件人不能为空!");
		return false;
	}else{ 
		$("#receiveName_info").html("<img src='../images/onSuccess.gif'>");
		return true;
	}
}
function checkAddr(){
	//收件人地址检查
	$("#fullAddress_info").html("");//清空错误提示
	var fullAddressTxt = $("#fullAddress").val();
	if(fullAddressTxt==""){
		$("#fullAddress_info").html("地址不能为空!");
		return false;
	}else{
		$("#fullAddress_info").html("<img src='../images/onSuccess.gif'>");
		return true;
	}
}
function checkPostalCode(){
	$("#postalCode_info").html("");//清空错误提示
	var postalCodeTxt = $("#postalCode").val();
	var postalCodereg = /^\d{6}$/;
	if(postalCodeTxt==""){
		$("#postalCode_info").html("邮编不能为空!");
		return false;
	}else if(!postalCodereg.test(postalCodeTxt)){
		$("#postalCode_info").html("邮编格式错误!");
		return false;
	}else{
		$("#postalCode_info").html("<img src='../images/onSuccess.gif'>");
		return true;
	}
}
function checkPhone(){
	$("#mobile_info").html("");//清空错误提示
	var mobileTxt = $("#mobile").val();
	var mobilereg = /^(13|15)[0-9]{9}$/;
	if(mobileTxt==""){
		$("#mobile_info").html("手机号不能为空!");
		return false;
	}else if(!mobilereg.test(mobileTxt)){
		$("#mobile_info").html("必须为以13或15开头的11位数字!");
		return false;
	}else{
		$("#mobile_info").html("<img src='../images/onSuccess.gif'>");
		return true;
	}
}


//表单项通过检查后才允许提交
$(function(){
	$("#receiveName").blur(function(){
		checkName();
	});
	$("#fullAddress").blur(function(){
		checkAddr();
	})
	$("#postalCode").blur(function(){
		checkPostalCode();
	})
	$("#mobile").blur(function(){
		checkPhone();
	})
	$("#f").submit(function(){
		var c1 = checkEmail();
		var c2 = checkAddr();
		var c3 = checkPostalCode();
		var c4 = checkPhone();
		return c1&&c2&&c3&&c4;
	});
});
			});
		</script>	
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			生成订单骤: 1.确认订单 >
			<span class="red_bold"> 2.填写送货地址</span> > 3.订单成功
		</div>
		<div class="fill_message">
			<p>
				选择收件人：
				<select id="address">
					<option value="0">
						新增收件人
					</option>
				</select>
			</p>
			<form name="ctl00" method="get" action="/T-GWAP/order/saveorder" id="f">
				<input type="hidden" name="id" id="addressId" />

				<table class="tab_login">
					<tr>
						<td valign="top" class="w1">
							<span style="color:red">*</span>收件人姓名：
						</td>
						<td>
							<input type="text" class="text_input" name="receiveAddress.receive_name"
								id="receiveName" />
							<div class="text_left" id="nameValidMsg">
								<p>
									请填写有效的收件人姓名
									<span id="receiveName_info" style="color:red"></span>
								</p>
								
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							<span style="color:red">*</span>收件人详细地址：
						</td>
						<td>
							<input type="text" name="receiveAddress.full_address" class="text_input"
								id="fullAddress" />
							<div class="text_left" id="addressValidMsg">
								<p>
									请填写有效的收件人的详细地址
									<span id="fullAddress_info" style="color:red"></span>
								</p>
								
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							<span style="color:red">*</span>邮政编码
						</td>
						<td>
							<input type="text" class="text_input" name="receiveAddress.postal_code"
								id="postalCode" />
							<div class="text_left" id="codeValidMsg">
								<p>
									请填写有效的收件人的邮政编码
									<span id="postalCode_info" style="color:red"></span>
								</p>
								
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							电话
						</td>
						<td>
							<input type="text" class="text_input" name="receiveAddress.phone"
								id="phone" />
							<div class="text_left" id="phoneValidMsg">
								<p>
									请填写有效的收件人的电话
									<span id="phone_info" style="color:red"></span>
								</p>
								
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							<span style="color:red">*</span>手机
						</td>
						<td>
							<input type="text" class="text_input" name="receiveAddress.mobile"
								id="mobile" />
							<div class="text_left" id="mobileValidMsg">
								<p>
									请填写有效的收件人的手机
									<span id="mobile_info" style="color:red"></span>
								</p>
								
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<a href="order_info.jsp"><input id="btnClientRegister" class="button_1" name="cancle"
					type="button" value="取消" /></a>
			
				<input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="下一步" />
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

