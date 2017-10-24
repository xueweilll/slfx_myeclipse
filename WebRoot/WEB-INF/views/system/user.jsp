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
		$("#user_list").datagrid({
			title : '用户列表',
			iconCls : 'icon icon-icon7',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'user/usersBind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			columns:[[{field:'name',title:'用户名称',width:fixWidth(0.1)},
			         {field:'aname',title:'角色名称',width:fixWidth(0.1)},
			         {field:'ename',title:'员工名称',width:fixWidth(0.1)},
			         {field:'email',title:'用户邮箱',width:fixWidth(0.2)},
			         {field:'age',title:'年龄',width:fixWidth(0.1)},
			         {field:'sex',title:'性别',width:fixWidth(0.1)},
			         {field:'createtime',title:'创建时间',width:fixWidth(0.15)},
			         {field:'edittime',title:'修改时间',width:fixWidth(0.15)}]]
		});
		var p = $("#user_list").datagrid('getPager');
		//console.info(p);
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	function selectUser(){
		$("#user_list").datagrid('unselectAll');
		var data=$("#actor").combobox("getValue");
		$("#user_list").datagrid('load',{
			actorid: (data=="0" ? null:data),
			name: $("#user").textbox("getValue")
		});
		$("#user_list").datagrid("unselectAll");
		$("#user_list").datagrid("reload");
	}

	function add() {
		showDialogWH("添加用户信息", "user/userInfo.html?id=0",500,350);
	}

	function edit() {
		var row = $("#user_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWH("编辑用户信息", "user/userInfo.html?id=" + row.id,500,350);
	}

	function distory() {
		var row = $("#user_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "user/delete.html",
					data : {
						userid : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#user_list").datagrid("unselectAll");
						$("#user_list").datagrid("reload");
					}
				});
			}
		});
	}
	function reloadpwd() {
		var row = $("#user_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("密码重置提示", "您确定要执行密码重置吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "user/saveReloadpwd.html",
					data : {
						userid : row.id
					},
					success : function() {
						$.messager.progress('close');
						$.messager.alert("操作提示", "密码重置成功！<br/>新密码为：12345",
								"info");
					}
				});
			}
		});
	}
	var buttons = $.extend([], $.fn.combobox.defaults.buttons);
	buttons.splice(0,1,{
		text: '清空',
		handler: function(target){
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	});
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="user_list" cellspacing="0" cellpadding="0">
		<!-- <thead>
			<tr>
				<th data-options="field:'name'">用户名称</th>
				<th data-options="field:'aname'">角色名称</th>
				<th data-options="field:'ename'">员工名称</th>
				<th data-options="field:'email'">用户邮箱</th>
				<th data-options="field:'age'">年龄</th>
				<th data-options="field:'sex'">性别</th>
				<th data-options="field:'createtime'">创建时间</th>
				<th data-options="field:'edittime'">修改时间</th>
			</tr>
		</thead> -->
	</table>
		<div id="tb" >
			<div class="cz_div_title">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon_add" plain="flase">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()" iconCls="icon_edit" plain="flase">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon_delete" plain="flase">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadpwd()" iconCls="icon-reload" plain="flase">重置用户密码</a>
		</div>
		<div class="cz_div"  >
			角色名称: 
			<select class="easyui-combobox" id="actor" panelHeight="auto" style="width:100px" data-options="
				url:'user/actorsBind.html',
				valueField:'id',
			    textField:'name',
			    editable:false,
			    value:'0'
			">
			</select>
			用户名称:
			<input class="easyui-textbox" id="user" style="width:100px" name="user"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectUser()" iconCls="icon-search">查询</a>
		</div>
	
	</div>
</body>
</html>
