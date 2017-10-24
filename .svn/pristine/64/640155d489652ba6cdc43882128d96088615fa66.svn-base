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
<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

	$(function() {
		$("#deployment_list").datagrid({
			title : '流程配置',
			iconCls : 'icon icon-icon7',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'workFlow/bind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			/* <th data-options="field:'name',width:120">流程名称</th>
			<th data-options="field:'type',width:160">流程类型</th>
			<th data-options="field:'time',width:160">部署时间</th>
			<th data-options="field:'version',width:160">版本号</th> */
			columns:[[{field:'name',title:'流程名称',width:fixWidth(0.25)},
				         {field:'type',title:'流程类型',width:fixWidth(0.25)},
				         {field:'time',title:'部署时间',width:fixWidth(0.25)},
				         {field:'version',title:'版本号',width:fixWidth(0.249)}]]
		});

		var p = $("#deployment_list").datagrid('getPager');
		//console.info(p);
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#starttime").datetimebox({
			onChange : function() {
				$('#endtime').datetimebox('enableValidation');
			}
		});
	});
	/* function selectMessages() {
		$("#deployment_list").datagrid('load', {
			starttime : $("#starttime").datetimebox("getValue"),
			endtime : $("#endtime").datetimebox("getValue")
		});
	} */
	function add() {
		showDialogWH("添加流程信息", "workFlow/workFlowInfo.html",450,220);
	}
	function distory() {
		var row = $("#deployment_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "workFlow/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#deployment_list").datagrid("unselectAll");
						$("#deployment_list").datagrid("reload");
					}
				});
			}
		});
	}
	function addTaskUser(){
		var row = $("#deployment_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWH("任务管理人配置", "workFlow/workFlowTaskUserInfo.html?id="+row.pid,530,400);
	}
	function selectView(){
		var row = $("#deployment_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		window.open("workFlow/imageInfo.html?id="+row.pid);
		//showDialogWH("查看流程图", "workFlow/imageInfo.html?id="+row.pid,500,500);
	}
	function deleteDeployment(){
		$.messager.prompt('提示信息', '请输入要删除的流程ID', function(r){
			if (r){
				$.post("workFlow/deleteDeployment.html",{"id":r},function(msg){
					data = eval('(' + msg + ')');
					if(!data.result){$.messager.alert("操作提示",data.msg,"error");}
				});
			}
		});
	}
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="deployment_list" cellspacing="0" cellpadding="0">
		<!-- <thead>
			<tr>
				<th data-options="field:'name',width:120">流程名称</th>
				<th data-options="field:'type',width:160">流程类型</th>
				<th data-options="field:'time',width:160">部署时间</th>
				<th data-options="field:'version',width:160">版本号</th>
			</tr>
		</thead> -->
	</table>
		<div id="tb" >
		<!-- <div style="margin-bottom:5px">
			开始时间: <input id="starttime" type="text" data-options="width:160">
			结束时间: <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectMessages()" iconCls="icon-search">查询</a>
		</div> -->
		<div class="cz_div"  >
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addTaskUser()" iconCls="icon-add" plain="false">配置任务办理人</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon-add" plain="false">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon-remove" plain="false">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectView()" iconCls="icon-add" plain="false">查看流程图</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="deleteDeployment()" iconCls="icon-add" plain="false">删除流程</a>
		</div>
	</div>
</body>
</html>
