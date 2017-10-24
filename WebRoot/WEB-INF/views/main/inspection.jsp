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
<title>出入库统计</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function addTab(title,url,icon) {
	window.parent.addTab(title, url, icon);
}
</script>
<style>
#map {
	padding: 0;
	margin: 0;
	height: 100%;
}

.panel-body {
	border: none;
}
</style>
</head>

<body class="easyui-layout"
	style="padding:0px;margin:0px;border:0px!important;">
	<table style=" width:100%; height:800px;">
		<tr>
			<td class="lcbt" colspan="7">&nbsp;</td>
		</tr>
		<tr>
			<!-- <td></td>
			<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('工程巡检流程图','engineerPatrol.html','icon icon-icon38')">工程巡检流程</a></td>
			<td style=" width:60px;"><span ></span></td>
			<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('水政巡检流程图','waterPatrol.html','icon icon-icon38')">水政巡检流程</a></td>
			<td style=" width:60px;"><span ></span></td>
			<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('设备维修流程图','maintenancePatrol.html','icon icon-icon38')">设备维修流程</a></td>
			<td></td> -->
		</tr>
		<tr>
			<td class="lcbt" colspan="7">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
