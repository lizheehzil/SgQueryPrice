<%@page import="com.sg.model.Mother_board"%>
<%@ page language="java" import="java.util.*,com.sg.dao.Impl.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath =  request.getServerName() + ":" + request.getServerPort()
			+ path + "/";String basePath2 = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>硕果数码</title>
		<link rel="icon" href="img/logo.ico" type="image/png" />
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/commom.css" rel="stylesheet" />
<link href="css/list.css" rel="stylesheet" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
		
		var path = "www.myshuoguo.com:8090/sgOfferSystemCMS/";
		var uid=${uid eq null?-1:uid};
		if(uid==-1){
			location.href="<%=basePath2%>";
		}
		var from=uid;
		var fromName='${name}';
		
		var websocket;
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://"+path+"ws?uid="+uid);
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://"+path+"ws"+uid);
		} else {
			websocket = new SockJS("http://"+path+"ws/sockjs"+uid);
		}
		websocket.onopen = function(event) {
			console.log("WebSocket:已连接");
			console.log(event);
		};
		websocket.onmessage = function(event) {
			$('<audio id="chatAudio"><source src="notice.mp3" type="audio/mpeg"></audio>').appendTo('body');
			$('#chatAudio')[0].play();
			var data=JSON.parse(event.data);
			var mess=$(".notice").html();
			if(mess==null||mess==""){
				mess=0;
			}
			mess++;
			$(".notice").html(mess);
		};
		websocket.onerror = function(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		websocket.onclose = function(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		};
</script> 
</head>

<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">硕果数码查价系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="toindex.do">首页</a></li>
				<li><a href="listAllNotpads.do">笔记本</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">整机装配<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="listAllCPU.do">CPU</a></li>
						<li><a href="listAllMemories_bar.do">内存</a></li>
						<li><a href="listAllDisk.do">硬盘</a></li>
						<li><a href="listAllMother_board.do">主板</a></li>
						<li><a href="listAllGraphics_card.do">显卡</a></li>
						<li><a href="listAllPower.do">电源</a></li>
						<li><a href="listAllCasee.do">机箱</a></li>
						<li><a href="listAllRadiator.do">散热</a></li>
						<li><a href="listAllMonitor.do">显示器</a></li>
						<li><a href="listAllKey_mouse.do">键鼠</a></li>
					</ul></li>
				<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">配件<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="listAllMemories_bar.do">内存条</a></li>
						<li><a href="listAllDisk.do">硬盘</a></li>
						<li><a href="listAllU_disk.do">U盘</a></li></li>
			</ul>
			</li>
			<li>
							<a href="toNotice.do">消息&nbsp;<span class="notice"></span></a>
						</li><li><a href="<%=path%>/toLoginLogList.do">登录日志</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> ${sessionScope.worker.name }，已登入 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logout.do">注销</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<br />
	<br />
	<!-- 主界面 -->
	<div class="container">
		<form action="editpower.do" method="post">
			<fieldset disabled="disabled">
				<div class="row">

					<div class="panel panel-primary">
						<!-- Default panel contents -->
						<div class="panel-heading text-center">货号:${item.pno }</div>
						<!-- 商品属性 -->
						<input type="hidden" name="pno" value="${item.pno}"/>
						
						<div class="row product-detail text-center">

							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">品牌</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="brand" value="${brand}" />
								</div>
							</div>

							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">型号</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="model" value="${item.model}" />
								</div>

							</div>

							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">功率</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="power" value="${item.power}" />
								</div>
							</div>

							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">进价</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="cost_price" value="${item.cost_price}" />
								</div>
							</div>

							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">最低价</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="mini_price" value="${item.mini_price}" />
								</div>
							</div>
							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">内部价</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="inner_price" value="${item.inner_price}" />
								</div>
							</div>
							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">媒体价</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="media_price" value="${item.media_price}" />
								</div>
							</div>
							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">京东价</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="jd_price" value="${item.jd_price}" />
								</div>
							</div>
							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">备注</div>
								<div class="input-group input-box">
									<input type="text" class="form-control text-center"
										name="remark" value="${item.remark}" />
								</div>
							</div>
							<div class="col-xs-6 col-sm-4 col-md-3 product-attr">
								<div class="att-name">可见性</div>
								<div class="input-group input-box"  style="width:100%;">
									<select class="form-control text-center" name="is_display">
										<option value="1" <c:if test="${item.is_display==1}">selected="selected"</c:if>>可见</option>
										<option value="0" <c:if test="${item.is_display==0}">selected="selected"</c:if>>不可见</option>
									</select>
								</div>
							</div>
						</div>

					</div>

				</div>

				<div class="row form-btn" hidden="hidden">
					<div class="col-xs-offset-4 col-xs-4">
						<div class="form-group text-center">
							<div class="row">
								<button type="submit" class="btn btn-primary btn-block">保存</button>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</form>

		<div class="row form-btn">
			<div class="col-xs-offset-4 col-xs-4">
				<div class="form-group text-center">
					<div class="row">
						<button type="button" class="btn btn-default updatebtn btn-block">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							&nbsp;修改信息
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- /主界面 -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.3.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/file-up.js"></script>
	<script>
		$(function() {
			$(".input-box input").css("width", $(".product-attr").css("width"));

			$(window).resize(
					function() {
						$(".input-box input").css("width",
								$(".product-attr").css("width"));
					});

			$(".updatebtn").click(function() {
				$("fieldset").attr("disabled", false);
				$(".form-btn").attr("hidden", false);
				$(".input-box input").css("border", "solid 1px black");
				$(this).css("display", "none");
			});
		});
	</script>

</body>

</html>