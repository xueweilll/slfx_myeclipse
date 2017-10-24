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
    <title>My JSP 'execute.jsp' starting page</title>
    
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
		$("#execute_list").datagrid({
			title : '调度执行列表',
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'execute/executelist.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true
		});
		
		var p = $("#execute_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	
	function rowformater(value, row, index) {
		return "<a href='files/download.html?uploadroute=" + myEncoder(row.uploadroute)
				+ "' target='_blank'>查看</a>";
	}

</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="executelist" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:160">id</th>
				<th data-options="field:'code',width:100">调度单编号</th>
				<th data-options="field:'promoter',width:100">调度发起人</th>
				<th data-options="field:'promotetime',width:150">调度发起时间</th>
				<th data-options="field:'instruction',width:100">指令内容</th>
				<th data-options="field:'gateoperatetime',width:150">闸门预计操作时间</th>
				<th data-options="field:'unitoperatetime',width:200">机组预计操作时间</th>
				<th data-options="field:'creater',width:100">制单人</th>
				<th data-options="field:'createtime',width:100">制单时间</th>
				<th data-options="field:'state',width:100">处理状态</th> <!-- 自主调度 单个枢纽的状态 -->
				<th data-options="field:'userid',width:100">执行人</th>
				<th data-options="field:'executetime',width:100">闸门执行时间</th>
				<th data-options="field:'unitexecutetime',width:100">机组执行时间</th>
				<th data-options="field:'uploadroute',width:80,formatter:  rowformater">操作</th>
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
