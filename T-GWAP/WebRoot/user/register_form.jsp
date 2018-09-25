<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 思源线上书城</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript">
	function isOK(){
		var $msg=$("span[id$='info']");
		var $input=$("input[class$='input']");
		if($msg.text()=="okokokokok"||$msg.text()=="okokokok"){
			return true;
		}else{
			$input.blur();
			alert("请先正确填写完所有信息!");
			return false;
		}
	}
	$(function(){
		//提交验证
		$("#btnClientRegister").click(function(){
			return isOK();
		});
		//表单验证
		//邮箱验证
		$("#txtEmail").blur(function(){
			var email=$(this).val();
			var $msg=$("#email_info");
			$msg.text("");
			var reg=/^\w+@\w+(\.[a-zA-Z]{2,3})+$/;
			if(email==""){
				$msg.text("邮箱不能为空!").css("color","red");
				return;
			}
			if(reg.test(email)){
				//邮箱唯一验证
				$.post(
					"/T-GWAP/user/checkemail",
					{"user.email":email},
					function(data){
						if(data){
							$msg.text("ok").css("color","green");
						}else{
							$msg.text("邮箱已占用").css("color","red");
						}
					}
				);	
			}else{
				$msg.text("邮箱格式不正确").css("color","red");
			}
		});
		//昵称验证
		$("#txtNickName").blur(function(){
			var nickName=$(this).val();
			var $msg=$("#name_info");
			$msg.text("");
			var reg=/^[a-z0-9\u4E00-\u9FA5\uF900-\uFA2D]{4,20}$/;
			var chinesereg=/[\u4E00-\u9FA5\uF900-\uFA2D]/;
			if(nickName==""){
				$msg.text("昵称不能为空!").css("color","red");
				return;
			}
			for(var i=0;i<nickName.length;i++){
			nickName=nickName.replace(chinesereg,"aa");
			}
			if(reg.test(nickName)){
				$msg.text("ok").css("color","green");
			}else{
				$msg.text("昵称长度4－20个字符").css("color","red");
			}
		});
		//密码验证
		$("#txtPassword").blur(function(){
			var password=$(this).val();
			var $msg=$("#password_info");
			$msg.text("");
			var reg=/^\w{6,20}$/;
			if(password==""){
				$msg.text("密码不能为空!").css("color","red");
				return;
			}
			if(reg.test(password)){
				$msg.text("ok").css("color","green");
			}else{
				$msg.text("密码长度6－20个字符").css("color","red");
			}
		});
		//密码确认验证
		$("#txtRepeatPass").blur(function(){
			var password1=$(this).val();
			var password=$("#txtPassword").val();
			var $msg=$("#password1_info");
			$msg.text("");
			if(password==""){
				$msg.text("还未填写密码").css("color","red");
				return;
			}
			if(password==password1){
				$msg.text("ok").css("color","green");
			}else{
				$msg.text("两次输入密码不一致").css("color","red");
			}
		});
		$("#txtVerifyCode").blur(function(){
		    var code = $(this).val();
		    var $msg=$("#number_info");
		    $msg.text("");
		    if(code == ""){
		    	$msg.text("验证码不能为空!").css("color","red");
		    	return;
		    }
			$.post(
				"/T-GWAP/user/checkcode.action",
				{"code":code},
				function(data){//data是服务器action返回的ok值
					if(data){
						$msg.text("ok").css("color","green");
					}else{
						$msg.text("验证码错误!").css("color","red");
					}
				}
			);
		});
	});
	//点图片换一张
	$(function(){
		$("#imgVcode").click(function(){renewCode();});
	});
	function renewCode(){
		$("#imgVcode").attr("src","/T-GWAP/user/imageCode.action?r="+new Date().getTime());
	}
</script>
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			注册步骤:
			<span class="red_bold">1.填写信息</span> > 2.验证邮箱 > 3.注册成功
		</div>
		<div class="fill_message">
			<form name="ctl00" method="post" action="/T-GWAP/user/register.action" id="f">
				<h2>
					以下均为必填项
				</h2>
				<table class="tab_login" >
					<tr>
						<td valign="top" class="w1">
							请填写您的Email地址：
						</td>
						<td>
							<input name="user.email" type="text" id="txtEmail" class="text_input"/>
							<div class="text_left" id="emailValidMsg">
								<span id="email_info" style="color:red"></span>
								<p>
									请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置在思源书城的昵称：
						</td>
						<td>
							<input name="user.nickname" type="text" id="txtNickName" class="text_input" />
							<div class="text_left" id="nickNameValidMsg">
									<span id="name_info" style="color:red"></span>
									<p>您的昵称可以由小写英文字母、中文、数字组成，</p>
									<p>长度4－20个字符，一个汉字为两个字符。</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置密码：
						</td>
						<td>
							<input name="user.password" type="password" id="txtPassword"
								class="text_input" />
							<div class="text_left" id="passwordValidMsg">
								<span id="password_info" style="color:red"></span>
								<p>
									您的密码可以由大小写英文字母、数字组成，长度6－20位。
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							再次输入您设置的密码：
						</td>
						<td>
							<input name="password1" type="password" id="txtRepeatPass"
								class="text_input"/>
							<div class="text_left" id="repeatPassValidMsg">
							<span id="password1_info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							验证码：
						</td>
						<td>
							<img class="yzm_img" id='imgVcode' src="/T-GWAP/user/imageCode.action" />
							<a href="javascript:;" onclick="renewCode();">看不清楚？换个图片</a>
							<input name="code" type="text" id="txtVerifyCode"
								class="yzm_input"/>
							<div class="text_left t1">
								<p class="t1">
									<span id="number_info" style="color:red"></span>
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>
								</p>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<input id="btnClientRegister" class="button_1" name="submit"  type="submit" value="注 册"/>
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

