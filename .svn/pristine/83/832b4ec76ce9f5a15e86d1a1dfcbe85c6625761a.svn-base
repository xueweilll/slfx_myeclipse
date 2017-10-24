<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>水利信息化综合管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/menuI.js"></script>
<script type="text/javascript" src="js/message.js"></script>
<script type="text/javascript" src="js/basic.js"></script>
<link rel="stylesheet" type="text/css" href="css/additional.css">
</head>

<body class="easyui-layout">
	<input type="hidden" id="sys" value="${sys}" />
	<input type="hidden" id="userid" value="${loginUser.userid}" />
	<div data-options="region:'north',split:false"
		style="height:68px; background-image:url('images/head_bg.png');background-repeat:repeat-x;overflow:hidden;">
		<img id="log" style=" height:68px; width:371px;float:left;" />
		<div style="text-align: right;  width:317px;float:right;overflow:hidden;height:68px; line-hight:68px; 	display: inline-block;   background-image:url('images/head_right_bg.png')">
			<a href="javascript:void(0)" onclick="returnback()"class="returnback"></a> 
			<a href="javascript:void(0)"onclick="editpwd()" class="xgmm5"></a>		
			<a  class="menu_message" onclick="showmessageCenter()" herf="javascript:void(0)">
				<span id="messagecount"style="color:#fc982d;padding-left:3px;font-weight:bold">0</span>
			</a>
		</div>
	</div>
	<style>
</style>
	<div style="height:34px;"
		data-options="region:'south',split:true,collapsible:false"
		class="bqxx">
		<span class="bqxx_span span_left02">当前用户：${loginUser.username}</span>
		<span class="bqxx_span span_left01">登录时间：<%=new java.util.Date()%></span></span>
		<span class="bqxx_span span_right">常州市城市防洪工程管理处信息化管理系统 v1.0</span>
	</div>
	<!-- <div
		data-options="region:'east',iconCls:'icon-reload',title:'East',split:true"
		style="width: 100px;"></div> -->
	<div
		data-options="region:'west',iconCls:'icon icon-menu',title:'菜单',split:true"
		style="width: 200px;" id="west">
		<div id='wnav' class="easyui-accordion" fit="true" border="false">
		</div>

	</div>
	<div data-options="region:'center',iconCls:'icon-home'"
		style=" background: #eee;">
		<div id="tabs" class="easyui-tabs" fit="true" border="false"></div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<div id="dialog"></div>
	<div id="window"></div>
	<div id="messagedialog">
		<table id="messageCenter_list" cellspacing="0" cellpadding="0">
			</div>
</body>
</html>
