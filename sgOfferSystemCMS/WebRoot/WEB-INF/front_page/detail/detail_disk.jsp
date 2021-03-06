<%@page import="com.sg.model.*"%>
<%@ page language="java" import="java.util.*,com.sg.dao.Impl.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath =request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = request.getScheme() + "://"
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
		<title>详情页</title>
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
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="js/jquery-1.11.3.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<style type="text/css">
		.notice{
			color:#18BC9C;
		}
		</style>
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
		/* 	console.log("WebSocket:收到一条消息",data);
			var textCss=data.from==-1?"sfmsg_text":"fmsg_text";
			$("#content").append("<div class='fmsg'><label class='name'>"+data.fromName+"&nbsp;"+data.date+"</label><div class='"+textCss+"'>"+data.text+"</div></div>");
			scrollToBottom(); */
		};
		websocket.onerror = function(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		websocket.onclose = function(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		};
		function order(){
			var se=confirm("请确认预订!");
			if (se==true)
			  {
				order1();
			  }
		}
			function order1(){
			var pno=${arg.pno};
			var data={};
			data["from"]=from;
			data["fromName"]=fromName;
			data["to"]=3213123;
			data["title"]=pno;
			data["type"]="1"
			websocket.send(JSON.stringify(data));
		}
			function jdPrice(){
				var term ="${brand}"+"${arg.model}";
				var a="http://search.jd.com/Search?keyword="+term+"&enc=utf-8&spm=1.1.6"
				window.open(a);
			}
		</script> 
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

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">

						<li>
							<a href="getNotpads.shtml">笔记本</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">整机装配<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="getAllCPU.shtml">CPU</a>
								</li>
								<li>
									<a href="getAllMemories.shtml">内存</a>
								</li>
								<li>
									<a href="getAllDisk.shtml">硬盘</a>
								</li>
								<li>
									<a href="getAllBoard.shtml">主板</a>
								</li>
								<li>
									<a href="getAllGraphics.shtml">显卡</a>
								</li>
								<li>
									<a href="getAllPower.shtml">电源</a>
								</li>
								<li>
									<a href="getAllCasee.shtml">机箱</a>
								</li>
								<li>
									<a href="getAllRadiator.shtml">散热</a>
								</li>
								<li>
									<a href="getAllMonitor.shtml">显示器</a>
								</li>
								<li>
									<a href="getAllKeymouse.shtml">键鼠</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">配件<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="getAllMemories.shtml">内存条</a>
								</li>
								<li>
									<a href="getAllDisk.shtml">硬盘</a>
								</li>
								<li>
									<a href="getAllUdisk.shtml">U盘</a>
								</li>
						
								</ul>
						</li>
						<li>
							<a href="toNotice.shtml">消息&nbsp;<span class="notice"></span></a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								${sessionScope.worker.name }，已登入
								<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="logout.shtml">注销</a>
								</li>
							</ul>
						</li>

					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		
		<!-- 笔记本详情 -->
		<div class="container">
			<div class="row">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title text-center">${brand}
${arg.model }</h3>
					</div>
					<div class="panel-body">
						<strong>货号:&nbsp;</strong> ${arg.pno }<br/><br/>
						<strong>类别:&nbsp;</strong>${category}<br/><br/>
						<strong>品牌:&nbsp;</strong>${brand}<br/><br/>
						<strong>型号:&nbsp;</strong>${arg.model }<br/><br/>
						<strong>容量:&nbsp;</strong>${arg.capacity }<br/><br/>
						<strong>接口类型:&nbsp;</strong>${arg.interface_type_id}<br/><br/>
						<strong>内部价:&nbsp;</strong>${arg.inner_price}<br/><br/>
						<strong>最低价:&nbsp;</strong>${arg.mini_price }<br/><br/>
						<strong>媒体价:&nbsp;</strong>${arg.media_price }<br/><br/>
						<strong>京东价(<a href="javascript:jdPrice();">点击查价</a>):&nbsp;</strong>${arg.jd_price}<br/><br/>
						<strong>创建日期:&nbsp;</strong>${arg.create_time }<br/><br/>
						<strong>更新日期:&nbsp;</strong>${arg.update_time }<br/><br/>
						<strong>其他:&nbsp;</strong>${arg.remark }<br/><br/>
					</div>
				</div>
				<a onclick="order()" class="btn btn-success btn-block" role="button">预约</a>
			</div>
		</div>

		</div>
		<br/>
		<br/>
		<div class="footer">
			<br />
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-4">
						<h4><strong>硕果数码:&nbsp;</strong></h4>
						<br/>
						<p>硕果数码专注为所有在校大学生提供优质的数码产品。<br/> 服务到极致是我们的目标，完善的售后体系是我们的后盾！
						</p>

					</div>
					<div class="col-xs-12 col-sm-5">
						<h4><strong>联系方式:&nbsp;</strong></h4>
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
	</body>
</html>