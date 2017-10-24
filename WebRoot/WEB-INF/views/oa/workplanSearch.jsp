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
<title>My JSP 'workplanSearch.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(function() {
		$("#workplan_list").datagrid({
			title : '工作计划列表',
			iconCls : 'icon icon-dutyers',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'workplan/pageBind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,

		});

		var p = $("#workplan_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		$("#search").bind("click", function() {
			var date1;
			var date2;
			if ($("#beigintime").datetimebox("getValue") != "") {
				date1 = $("#beigintime").datetimebox("getValue")
			} else {
				date1 = null
			}
			if ($("#endtime").datetimebox("getValue") != "") {
				date2 = $("#endtime").datetimebox("getValue")
			} else {
				date2 = null
			}
			var conditions = {
				title : $("#title").textbox("getValue"),
				remark : $("#remark").textbox("getValue"),
				beigintime : date1,
				endtime : date2
			};

			$("#workplan_list").datagrid('load', {
				//url:'station/stationList.html?conditions='+JSON.stringify(conditions)
				conditions : JSON.stringify(conditions)
			});
		});
		$("#beigintime").datetimebox({
			editable : false
		});
		$("#endTime").datetimebox({
			editable : false
		});
	});

	function reflush() {
		$("#workplan_list").datagrid('reload');
	}
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="workplan_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'title',width:250">标题</th>
				<th data-options="field:'remark',width:450">内容</th>
				<th data-options="field:'beigintime',width:150">开始时间</th>
				<th data-options="field:'endtime',width:150">结束时间</th>
				<th data-options="field:'remindtime',width:150">提醒时间</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div class="cz_div">
		标题:&nbsp;<input id="title" class="easyui-textbox" style="width: 120px">
		&nbsp;&nbsp; 内容:&nbsp;<input id="remark" class="easyui-textbox"
			style="width: 180px"> &nbsp;&nbsp; 开始时间:<input
			id="beigintime" class="easyui-datetimebox" style="width: 180px">
		&nbsp;&nbsp; ~ &nbsp;&nbsp;<input id="endtime"
			class="easyui-datetimebox" style="width: 180px"> &nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" id="search">查询</a>
       </div>
	</div>
</body>
</html>
