<%@ page language="java" import="java.util.*,com.sg.dao.Impl.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getServerName()+":"+request.getServerPort()+path+"/";
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
						</li>
						<li><a href="<%=path%>/toLoginLogList.do">登录日志</a></li>
							</ul>
					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								${sessionScope.worker.name }，已登入
								<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="logout.do">注销</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<div class="container">
		<div class="row">
				<div class="col-md-offset-8 col-md-4">
					<form action="search${type}.do" method="post">
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><span class=" glyphicon glyphicon-search" aria-hidden="true"></span></span>
								<input type="hidden" name="type" value="${type}"/>
								<input type="text" class="form-control" name="term" id="term" placeholder="Search">
								<span class="input-group-btn">
      								<button class="btn btn-default" type="submit">搜索</button>
    							</span>
							</div>
						</div>
					</form>

				</div>
			</div>
		
		<br /><br />
		<!-- 主界面 -->
			<form action="#" method="post">
				<!-- 商品列表 -->
				<!-- 表头 -->
				<div class="row text-center ">
					<div class="col-xs-1">
						<h4><strong>选择</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>类别</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>品牌</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>型号</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>行货进价</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>最低价</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>京东价</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>对外可见</strong></h4></div>
					<div class="col-xs-1">
						<h4><strong>备注</strong></h4></div>
					<div class="col-xs-3">
						<h4><strong>操作</strong></h4></div>
				</div>
				<!-- 单个商品行 -->
					
					<c:forEach items="${pagination.list }" var="item">
					<div class="row text-center product-item">
						<div class="col-xs-1"><input type="checkbox" name="#" class="chosen-item"/></div>
						<div class="col-xs-1">${item.category }</div>
						<div class="col-xs-1">${item.brand }</div>
						<div class="col-xs-1">${item.model }</div>
						<div class="col-xs-1">${item.standard_price }</div>
						<div class="col-xs-1">${item.mini_price }</div>
						<div class="col-xs-1">${item.jd_price }</div>
						<div class="col-xs-1">${item.is_display }</div>
						<div class="col-xs-1">${item.remark }</div>
						<div class="col-xs-2">
							<a href="detail.do?pno=${item.pno}&brand=${item.brand}">
								<p><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>&nbsp;&nbsp;查看及修改</p>
							</a>
						</div>
						<div class="col-xs-1">
							<a href="remove.do?pno=${item.pno}">
								<p><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;&nbsp;删除</p>
							</a>
						</div>
						</div>
					</c:forEach>
					<div style="clear:both;"></div> 
					<div class="pagination">
						<c:forEach items="${pagination.pageView }" var="page">
								${page}
						</c:forEach>
					</div>
					
				<!-- 批量操作按钮
				<div class="row">
					<br/><br/>
					<div class="form-group col-xs-1">
						<div class="row">
							<button type="submit" class="btn btn-primary" disabled="disabled" id="delete-all">删除所选项</button>
						</div>
					</div>
				</div> -->
			</form>
		</div>
		<!-- 主界面 -->

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="js/jquery-1.11.3.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script src="js/file-up.js"></script>
		<script>
			$(function() {
				$(".product-item:even").css("background-color", "#E8DFC4");
				
				$(".chosen-item").click(function(){
					$("#delete-all").removeAttr("disabled");
				});
			});
		</script>

	</body>

</html>