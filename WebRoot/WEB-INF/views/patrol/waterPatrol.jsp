<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript">
function addTab(title,url,icon) {
	window.parent.addTab(title, url, icon);
}
</script>
<body class="easyui-layout"
	style="padding:0px;margin:0px;border:0px!important;">
	<table style=" width:100%; height:800px;">

		<tr>
			<td class="lcbt" colspan="9">-水政巡检-</td>
		</tr>
		<tr>
			<td></td>
			<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk">水政巡检</a></td>
			<td style=" width:60px;"><span class="lctzx"></span></td>
			<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk">巡检人员</a></td>
			<td style=" width:60px;"><span class="lctzx"></span></td>
			<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('巡检上报','szpatrol.html','icon icon-icon38')">枢纽负责人</a></td>
			<td style=" width:60px;"><span class="lctzx"></span></td>
			<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('巡检审核','szpatrolApproval.html','icon icon-icon38')">水政科</a></td>
			<td></td>
		</tr>
		<tr>
			<td class="lcbt" colspan="9">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
