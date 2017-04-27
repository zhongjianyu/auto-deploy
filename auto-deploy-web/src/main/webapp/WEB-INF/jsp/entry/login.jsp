<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
<script src="js/jquery.js"></script>
<!-- <script src="verificationNumbers.js"></script> -->
<script src="js/Particleground.js"></script>
<script>
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
		//测试提交，对接程序删除即可
		$(".submit_btn").click(function() {
			location.href = "index.html";
		});
	});
</script>
</head>
<body>
	<dl class="admin_login">
		<dt>
			<strong>自动化持续集成系统</strong> <em>continuous integration ms</em>
		</dt>
		<dd class="user_icon">
			<input type="text" placeholder="账号" class="login_txtbx" />
		</dd>
		<dd class="pwd_icon">
			<input type="password" placeholder="密码" class="login_txtbx" />
		</dd>
		<dd class="val_icon" style="background-color: #5cbdaa;">
			<div class="checkcode" style="background-color: #5cbdaa;">
				<input type="text" id="J_codetext" placeholder="验证码" maxlength="5" class="login_txtbx">
				<!-- <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas> -->
				<div>fd</div>
			</div>
			<input type="button" value="验证码核验" class="ver_btn" onClick="validate();">
		</dd>
		<dd>
			<input type="button" value="立即登陆" class="submit_btn" />
		</dd>
		<dd>
			<p>© 2017-2020 auto-deploy 版权所有</p> 
			<p>闽ICP备12024298号</p>
		</dd>
	</dl>
</body>
</html>
