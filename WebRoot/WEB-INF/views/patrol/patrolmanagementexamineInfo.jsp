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

<title>My JSP 'patrolManagementInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<table id="list" cellspacing="0" cellpadding="0" data-options="">
		<thead>
			<tr>
				<th data-options="field:'stationname',width:120">枢纽名称</th>
				<th data-options="field:'usernames',width:150">类型</th>
				<th data-options="field:'memo',width:350">问题</th>
				<th data-options="field:'memo',width:350">工程科意见</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		$(function(){
			$("#list").datagrid({
				title : '列表',
				width : 'auto',
				height : 'auto',
				pageSize : 20,
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				//url : 'exertionrecord/detailsBind.html',
				queryParams: {
					id:$("#id").val(),
					type:$("#type").val()
				},
				remoteSort : false,
				singleSelect : true,
				fitColumns:true
			});
		});
	</script>
</body>
</html>
