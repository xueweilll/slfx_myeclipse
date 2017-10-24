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
    <title>My JSP 'dispatchRecode.jsp' starting page</title>
    
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
		$("#logger_list").datagrid({
			title : '角色列表',
			iconCls : 'icon icon-power',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'logger/bind.html',
			remoteSort : false,
			idField : 'powerId',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ]
		});

		var p = $("#logger_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		})
	});

	function add() {
		showDialog("添加角色信息", "logger/loggerInfo.html?id=0");
	}

	function edit() {
		var row = $("#logger_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialog("编辑角色信息", "logger/loggerInfo.html?id=" + row.powerId);
	}

	function power() {
		var row = $("#logger_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialog("权限", "logger/loggerAllot.html?id=" + row.powerId);
	}

	function distory() {
		var row = $("#logger_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "logger/distory.html",
					data : {
						entityId : row.powerId
					},
					success : function() {
						$.messager.progress('close');
						$("#logger_list").datagrid("reload");
					}
				});
			}
		});
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="logger_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'status',width:100">调度状态</th>
				<th data-options="field:'ddperson',width:100">调度发起人</th>
				<th data-options="field:'ddtime',width:150">调度发起时间</th>
				<th data-options="field:'jsperson',width:100">调度接受人</th>
				<th data-options="field:'jstime',width:150">调度接受时间</th>
				<th data-options="field:'ddDsp',width:200">调度描述</th>
				<th data-options="field:'operate',width:100">操作</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
			<tr>
				<td></td>
				<td>已处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a></td>
			</tr>
			<tr>
				<td></td>
				<td>处理中</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a></td>
			</tr>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
			<tr>
				<td></td>
				<td>未处理</td>
				<td>陈XX</td>
				<td>2015-10-27 13:28</td>
				<td>李XX</td>
				<td>2015-10-27 13:29</td>
				<td></td>
				<td><a href="javascript:void(0)">明细</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">处理</a></td>
			</tr>
		</tbody>
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle;">
		调度发起人:<input id="name" class="easyui-combobox" style="width: 110px">
		&nbsp;&nbsp; 调度发起时间:<input id="passport" class="easyui-datebox"
			style="width: 110px"> &nbsp;&nbsp; <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" id="search">查询</a>
	</div>
</body>
</html>
