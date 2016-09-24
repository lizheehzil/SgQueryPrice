<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    	<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<title>硕果数码查价系统</title>
		<link rel="icon" href="img/logo.ico" type="image/png" />
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/commom.css" rel="stylesheet" />

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>
  
  <body>
    <nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        				<span class="sr-only">Toggle navigation</span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
      				</button>
					<a class="navbar-brand" href="#">硕果数码查价系统</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown">
							<a href="front.login" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								前台入口
								<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="back.login">后台入口</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<!-- <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					

					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Andy,已登入<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="#">注销</a>
								</li>
							</ul>
						</li>

					</ul>
				</div> -->
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<br/>
		<br/>
		<br/>

		<div class="container">
			<div class="row">
				<div class="col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6" style="background-color: #ecf0f1;border-radius: 10px;">
					<form class="form-horizontal" action="frontlogin.login" method="post">
						<fieldset>
							<br/>
							<legend class="text-center">用户登录</legend>
							<br />
							<div class="form-group">
								<label for="username" class="col-lg-2 control-label">用户名:</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
								</div>
							</div>
							<div class="form-group">
								<label for="userpwd" class="col-lg-2 control-label">密码:</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" name="pwd" id="pwd" placeholder="请输入密码">
									<!-- <div class="checkbox text-right">
										<label>
            							<input type="checkbox">记住密码
          							</label>
									</div> -->
								</div>
							</div>

							<div class="form-group text-center">
								<div class="row">
									<button type="reset" class="btn btn-default">重置</button>
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>

		</div>
		<br/>
		<br/>
		<br/>
		<div class="footer">
			<br />
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-4">
						<h4><strong>硕果数码:</strong></h4>
						<br/>
						<p>硕果数码专注为所有在校大学生提供优质的数码产品。<br/> 服务到极致是我们的目标，完善的售后体系是我们的后盾！
						</p>

					</div>
					<div class="col-xs-12 col-sm-5">
						<h4><strong>联系方式:</strong></h4>
						<br>
						<p>客服：028-69296968</p>
						<p>QQ：849689966</p>
						<p>微信：硕果365</p>
						<p>地址：四川省成都市双流县航空港温哥华花园2期10栋104</p>
					</div>
					<div class="col-xs-12 col-sm-3">
						<h4><strong>微信公众号：</strong></h4><br/>
						<!-- 二维码 -->
						<div class="thumbnail wechatqr">
							<img src="img/wechatQR.bmp" alt="扫描二维码">
						</div>
					</div>
				</div>

			</div>
			<div class="footer-bottom">
				<br/>
				<div class="container">
					<div class="row text-right">
						<small>Copyright © 2016 硕果数码 保留所有权利</small>
						<small><a href="#">返回顶部</a></small>
					</div>
				</div>
				<br/>
			</div>
		</div>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="js/jquery-1.11.3.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>

	</body>

</html>
