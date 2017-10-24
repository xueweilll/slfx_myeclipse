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
<title>固定资产列表</title>

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
		var data = {
			"id" : ($("#empId").val() == "" ? 0 : $("#empId").val()),
			"department" : {
				"id" : null
			},
			"name" : null
		};

		$("#employee_list").datagrid({
			title : '固定资产列表',
			iconCls : 'icon icon-icon5',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'assets/assetsList.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true
		});
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2, '', {
			text : '清空',
			handler : function(target) {
				console.info(target.id);
				$("#" + target.id).datetimebox('setValue', '');
			}
		});
		$("#begintime").datetimebox({
			editable : false,
			buttons : buttons,
			onChange : function() {
				$("#endtime").datetimebox('enableValidation');
			}
		});
		$("#endtime").datetimebox({
			editable : false,
			buttons : buttons
		});

		$("#search").bind("click", function() {
			var date1;
			var date2;
			if ($("#begintime").datetimebox("getValue") != "") {
				date1 = $("#begintime").datetimebox("getValue");
			} else {
				date1 = null;
			}
			if ($("#endtime").datetimebox("getValue") != "") {
				date2 = $("#endtime").datetimebox("getValue");
			} else {
				date2 = null;
			}
			var data = {
				//"id" : ($("#empId").val() == "" ? 0 : $("#empId").val()),
				"creater" : $("#user").textbox("getText"),
				"createtime" : date1,
				"edittime" : date2
			};
			$("#employee_list").datagrid("load", {
				jsonStr : JSON.stringify(data)
			});
			$("#employee_list").datagrid("unselectAll");
			$.messager.progress("close");
		});
		var p = $("#employee_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#btnAdd").bind("click", function() {
			showDialogWH("添加资产信息", "assets/assetsInfo.html?id=0", 470, 350);
		});
		$("#down").bind(
				"click",
				function() {
					var row = $("#employee_list").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					}
					var link = "assets/down.html?id=" + row.id;
					window.location.href = link;
					//$("#employee_list").datagrid("reload");
				});
		$("#btnEdit").bind(
				"click",
				function() {
					var row = $("#employee_list").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					}
				/* 	if (row.state != "保存") {
						$.messager.alert("操作提示", "已生成无法修改！", "error");
						return false;
					} else { */
						showDialogWH("修改资产信息", "assets/assetsInfo.html?id="
								+ row.id, 470, 350);
					/* } */
				});
		$("#btnDelete").bind("click", function() {
			var row = $("#employee_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			} else {
				$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
					if (data) {
						$.messager.progress();
						$.ajax({
							type : 'POST',
							url : "assets/deleteAssets.html",
							data : {
								"id" : row.id
							},
							success : function() {
								$.messager.progress('close');
								$("#employee_list").datagrid("reload");
								$("#employee_list").datagrid("unselectAll");
							}
						});
					}
				});
			}
		});
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="employee_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
			    <th data-options="field:'sname',width:120">枢纽名称</th>
				<th data-options="field:'code',width:120">资产编号</th>
				<th data-options="field:'name',width:120">物品名称</th>
				<th data-options="field:'creater',width:100">制单人</th>
				<th data-options="field:'principal',width:110">负责人</th>
				<th data-options="field:'moblie',width:120">联系电话</th>
				<th data-options="field:'createtime',width:130">创建时间</th>
				<th data-options="field:'edittime',width:130">修改时间</th>
				<th data-options="field:'state',width:100,hidden:true">状态</th>
				<th data-options="field:'memo',width:130">资产描述</th>
				<th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'url',hidden:true"></th>
				<th data-options="field:'materialType',hidden:true"></th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div class="cz_div_title">
			<a id="btnAdd" class="easyui-linkbutton"
				data-options="plain:false,iconCls:'icon-add'">新增</a> <a id="btnEdit"
				class="easyui-linkbutton"
				data-options="plain:false,iconCls:'icon-edit'">修改</a> <a
				id="btnDelete" class="easyui-linkbutton"
				data-options="plain:false,iconCls:'icon-clear'">删除</a><a id="down"
				class="easyui-linkbutton"
				data-options="plain:false,iconCls:'icon-add'">下载二维码 </a>
		</div>
		<div class="cz_div">
			<form id="specification" method="post">
				制单人:<input id="user" class="easyui-textbox" style="width: 150px" />
				&nbsp;&nbsp;创建时间区间:<input id="begintime" class="easyui-datetimebox"
					style="width: 160px" editable="false" />&nbsp;&nbsp;~&nbsp;&nbsp; <input
					id="endtime" class="easyui-datetimebox" style="width:160px"
					validType='compareDate[begintime]' /> &nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" id="search">查询</a>
			</form>
		</div>
	</div>
</body>
</html>
