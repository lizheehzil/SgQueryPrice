<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
response.sendRedirect("back.login");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

