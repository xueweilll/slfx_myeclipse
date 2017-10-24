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

<title>应急巡检录入</title>
<%@include file="../header.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function(){
		$("#recordlist").datagrid({
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			//url : 'egpatrolrecord/patrolplanlist.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){
				showDialogWH("录入巡检结果","egpatrolrecord/egPatrolRecordInfo.html?id="+ row.id,480, 550);
			}
		});
		
		var p = $("#patrolplanlist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		
		$("#add").bind("click",function() {
			alert("==");
			var row = $("#patrolplanlist").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("录入巡检结果", "egpatrolrecord/egPatrolRecordInfo.html?id=" + row.id, 480, 550);
		});
		
		$("#delete").bind("click",function() {
			var row = $("#patrolplanlist").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			if (row.stateValue!="0") {
				$.messager.alert("操作提示", "该巡检结果已提交，不能删除！", "error");
				return false;
			}
		});
	});
	
	function add(){
		showDialogWH("录入巡检结果", "egpatrolrecord/egPatrolRecordInfo.html?id=0", 800, 600);
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="recordlist" cellspacing="0" cellpadding="0" data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:150,hidden:true">ID</th>
				<th data-options="field:'station',width:150">枢纽名称</th>
				<th data-options="field:'excepttime',width:150">计划巡检时间</th>
				<th data-options="field:'createrusername',width:140">制单人</th>
				<th data-options="field:'createtime',width:150">制单时间</th>
				<th data-options="field:'senderusername',width:140">发令人</th>
				<th data-options="field:'sendtime',width:150">发令时间</th>
				<th data-options="field:'state',width:100">状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle">
		<div class="cz_div_title">
			<a id="add" class="easyui-linkbutton"  iconCls="icon-add" onclick="add()" plain="false">录入</a> <a id="delete"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="false">删除</a>
		</div>
		<div class="cz_div">
			计划巡检时间:&nbsp;&nbsp;<input	id="starttime" type="text" data-options="width:160"
				class="easyui-datetimebox" /> ~ <input id="endtime" type="text"	class="easyui-datetimebox"
				data-options="width:160,validType:['compareDate[starttime]']" />
			&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="search()" id="search" iconCls="icon-search">查询</a>
		</div>
	</div>	
</body>
</html>
