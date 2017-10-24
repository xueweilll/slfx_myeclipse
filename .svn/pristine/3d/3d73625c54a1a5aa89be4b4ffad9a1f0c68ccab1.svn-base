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

<title>My JSP 'employeeInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<body class="easyui-layout" id="cc">
	<input type="hidden" id="starttime" value="${starttime}">
	<input type="hidden" id="endtime" value="${endtime}">
	<input type="hidden" id="uid" value="${uid}">
	<table id="realtimereportinfo_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
	</table>
	<script type="text/javascript">
		$(function() {
            /*  var starttime=${starttime};
             var endtime=${endtime};
             var uid=${uid}; */
             $("#realtimereportinfo_list").datagrid({
     			iconCls : 'icon icon-icon10',
     			width : 'auto',
     			height : 'auto',
     			nowrap : false,
     			striped : true,
     			border : true,
     			collapsible : false,
     			fit : true,
     			url : 'realTimeUnitReport/findUnitDetials.html',
     			queryParams: {
     				starttime:$("#starttime").val(),
     				endtime:$("#endtime").val(),
     				uid:$("#uid").val()
     			},
     			remoteSort : false,
     			singleSelect : true,
     			rownumbers : true,
     			columns:[[
							{field:'starttime',title:'开始时间',width:180},
							{field:'endtime',title:'结束时间',width:180},
							{field:'kjtime',title:'开机总时间',width:100},
							{field:'totalflow',title:'开机总流量',width:100}
						]]
     		});
		});
	</script>
</body>
</html>
