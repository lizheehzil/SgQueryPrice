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
		
		function feedback(){
			if(websocket.readyState==websocket.OPEN){
			var data={};
			data["id"]=${msg.id};
			data["from"]=uid;
			data["to"]=${msg.from};
			if($("[name='backstatus']:checked").val()==("无货")){
				data["text"]=$("[name='backstatus']:checked").val();
			}else{
				data["text"]="￥"+$("[name='backprice']").val();
			}
			websocket.send(JSON.stringify(data));
			alert("反馈成功");
			location.href="toNotice.do";
			} 
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
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title text-center">${brand }
						${item.model }</h3>
					</div>
					<div class="panel-body">
						<div class="col-xs-1"></div>
						<div class="col-xs-5">
							<strong>货号:</strong>${item.pno }<br/><br/>
							<strong>类别:</strong>${category}<br/><br/>
							<strong>品牌:</strong>${brand }<br/><br/>
							<strong>型号:</strong>${item.model }<br/><br/>
							<strong>详细:</strong><a href="detail.do?pno=${item.pno}&brand=${brand}">查看
								</a><br/><br/>
							<strong>最低价:</strong>${item.mini_price }<br/><br/>
							<strong>指导价:</strong>${arg.guide_price }<br/><br/>
							<strong>京东价:</strong>${arg.jd_price}<br/><br/>
							<strong>其他:</strong>${arg.remark }<br/><br/>
						</div>
						<div class="col-xs-5">
						<br>
							今日报价:<input class="form-control" type="text" name="backprice"/>
							是否有货:<div class="radio">
								   	<label>
								    	<input type="radio" name="backstatus" id="optionsRadios1" 
								         	value="有货" checked> 有货
								   	</label>
								</div>
								<div class="radio">
								   <label>
								      <input type="radio" name="backstatus" id="optionsRadios2" 
								         value="无货"> 无货
								   </label>
								</div>
							<button onclick="feedback()" class="btn btn-success btn-block">反馈</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="js/jquery-1.11.3.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script src="js/file-up.js"></script>
	</body>

</html>