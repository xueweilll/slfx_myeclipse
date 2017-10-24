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
<title>My JSP 'authority.jsp' starting page</title>

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
		$("#power_list").datagrid({
			title : '角色列表',
			iconCls : 'icon icon-icon6',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'power/bind.html',
			remoteSort : false,
			idField : 'powerId',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			/* <th data-options="field:'name',width:100">角色名称</th>
			<th data-options="field:'remark',width:300">角色描述</th>
			<th data-options="field:'createtime',width:150">创建时间</th>
			<th data-options="field:'edittime',width:150">修改时间</th> */
			columns:[[{field:'name',title:'角色名称',width:fixWidth(0.2)},
				         {field:'remark',title:'角色描述',width:fixWidth(0.399)},
				         {field:'createtime',title:'创建时间',width:fixWidth(0.2)},
				         {field:'edittime',title:'修改时间',width:fixWidth(0.2)}]]
		});
		$("#search").bind("click", function() {
			$("#power_list").datagrid('unselectAll');
			var data = {

				"name" : $("#orgId").textbox("getValue"),

				"remark" : $("#Name").textbox("getValue")
			};
			$("#power_list").datagrid(
					"load",{
					jsonStr : JSON.stringify(data)
				}
			);
			$.messager.progress("close");
		});

		var p = $("#power_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});

	function add() {
		showDialogWH("添加角色信息", "power/authorityInfo.html?id=0",500,200);
	}

	function edit() {
		var row = $("#power_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		//alert(row.id);
		showDialogWH("编辑角色信息", "power/authorityInfo.html?id=" + row.id,500,200);
	}

	function power() {
		var row = $("#power_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWHH("角色操作权限设置", "power/authorityAllot.html?id=" + row.id, 880,
				450);
	}

	function distory() {
		var row = $("#power_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "power/delete.html",
					data : {
						"id" : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#power_list").datagrid("unselectAll");
						$("#power_list").datagrid("reload");
					}
				});
			}
		});
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="power_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<!-- <thead>
			<tr>
				<th data-options="field:'name',width:100">角色名称</th>
				<th data-options="field:'remark',width:300">角色描述</th>
				<th data-options="field:'createtime',width:150">创建时间</th>
				<th data-options="field:'edittime',width:150">修改时间</th>
			</tr>
		</thead> -->
	</table>
	<div id="tb">
		<div class="cz_div_title" >
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="add()" iconCls="icon-add" plain="false">添加</a> <a
				href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()"
				iconCls="icon-edit" plain="false">编辑</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="distory()" iconCls="icon-remove"
				plain="false">删除</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="power()"
				iconCls="icon icon_aduit" plain="false">权限</a>
		</div>
		<div class="cz_div">
			<form id="authority" method="post">
				角色名称:<input id="orgId" class="easyui-textbox" style="width: 110px" />
				&nbsp;&nbsp; 角色描述:<input id="Name" class="easyui-textbox"
					style="width: 120px"> &nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" id="search">查询</a>
			</form>
		</div>

	</div>
</body>
</html>
