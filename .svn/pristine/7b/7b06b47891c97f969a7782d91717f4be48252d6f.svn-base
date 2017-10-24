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
<%@include file="../../header.jsp"%>
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
			<td class="lcbt" colspan="9">-日常巡检-</td>
		</tr>
		<tr>
			<td></td>
			<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('执行人员实施（日常）','patrolplan.html','icon icon-icon38')">巡检人员</a></td>
			<td style=" width:60px;"><span class="lctzx"></span></td>
			<td style=" width:120px; height:80px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('执行部门汇总上报（日常）','patrolreceipt.html','icon icon-icon38')">执行部门</a></td>
			<td style=" width:60px;"><span class="lctzx"></span></td>
			<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('工程科汇总上报（日常）','departmentPatrol.html','icon icon-icon38')">工程科</a></td>
			<td style=" width:60px;"><span class="lctzx"></span></td>
			<td style=" width:120px;"><a href="javascript:void(0)" class="lctbk" onclick="addTab('管理处审阅（日常）','patrolmanagement.html','icon icon-icon38')" >管理处</a></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td style=" height:30px; text-align:center"><span class="lctzxbs"></td>
			<td></td>
			<td style=" height:30px; text-align:center"><span class="lctzxb"></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td style=" width:120px; height:80px"><a href="javascript:void(0)" class="lctbk" onclick="addTab('执行部门自行解决','resolve.html','icon icon-icon38')">自行解决</a></td>
			<td></td>
			<td style=" width:120px; height:80px"><a href="javascript:void(0)" class="lctbk" onclick="addTab('工程科安排解决','patrolReport.html','icon icon-icon38')">安排解决</a></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td class="lcbt" colspan="9">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
