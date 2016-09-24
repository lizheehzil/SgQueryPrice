<%@ page language="java" import="java.util.*,com.sg.dao.Impl.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path %>/css/commom.css" rel="stylesheet" />
<link href="<%=path %>/css/list.css" rel="stylesheet" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="<%=path %>/js/jquery-1.11.3.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="<%=path %>/js/bootstrap.min.js"></script>
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
						<li><a href="listAllU_disk.do">U盘</a></li>
			</ul>
			</li>
			<li>
							<a href="toNotice.do">消息&nbsp;<span class="notice"></span></a>
						</li>
						<li><a href="<%=path%>/toLoginLogList.do">登录日志</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> User <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logout.do">注销</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	 </nav>
		<!-- 主界面 -->
		<div class="container">
				<div class="row">

					<div class="panel panel-primary">
						<!-- Default panel contents -->
						<div class="panel-heading text-center">员工：${sessionScope.worker.name} </div>
							<form action="<%=path %>/deleteLoginLog.shtml" method="post">
							<table class="table">
								<!--表头-->
								<tr>
								<th><input id="checkbox" type="checkbox" onclick="checkBox('ids',this.checked)")></th>
									<th>登录人员</th>
									<th>登录时间</th>
									<!-- <th>在线时间</th> -->
								</tr>
								
								<c:if test="${!empty pagination}">
									<c:forEach items="${pagination.list }" var="entry">
								<tr>
									<td><input name="ids" type="checkbox" value="${entry.id }"></td>
									<td>${!empty entry.workername?entry.workername:''}</td>
									<td><fmt:formatDate value="${entry.logintime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<!-- <td>4h36min</td> -->
								</tr>
								</c:forEach>
								</c:if>
								
								<tr>
								<td colspan="2"><c:forEach items="${pagination.pageView}" var="page">
							${page }
							</c:forEach></td>
								<td  style="text-align: center;"><input type="submit" value="删除"></td></tr>
				  			</table>
				  			</form>
							
						</div>
					</div>

				</div>


	</div>
	<!-- /主界面 -->
	<script type="text/javascript">
		function checkBox(name, checked) {
		//全选开始
		$("input[name='" + name + "']").attr("checked", checked);
	}
	</script>
	</body>
</html>