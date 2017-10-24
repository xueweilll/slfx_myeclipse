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
<title>My JSP 'employee.jsp' starting page</title>

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
			title : '计量列表',
			iconCls : 'icon icon-icon5',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'prickle/systemPrickle.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			/* <th data-options="field:'name',width:120">计量名称</th>
			<th data-options="field:'creater',width:100">创建人</th>
			<th data-options="field:'editer',width:110">修改人</th>
			<th data-options="field:'createtime',width:130">创建时间</th>
			<th data-options="field:'edittime',width:130">修改时间</th>
			<th data-options="field:'id',hidden:true"></th> */
			columns:[[				    			        
				        {field:'name',title:'计量名称',width:fixWidth(0.2)},
				        {field:'creater',title:'创建人',width:fixWidth(0.2)},
				        {field:'editer',title:'修改人',width:fixWidth(0.2)},
				        {field:'createtime',title:'添加时间',width:fixWidth(0.2)},
				        {field:'edittime',title:'修改时间',width:fixWidth(0.2)},
				        {field:'id',hidden:"true"}
				    ]]
		});
/* 		$("#user").combobox({
			url : 'operateLogger/getUsername.html',
			textField : 'name',
			valueField : 'id',
			value : $("#user").val(),
			editable : false
		}); */
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
			$("#employee_list").datagrid('unselectAll');
			var date1;
			var date2;
			if ($("#begintime").datebox("getValue") != "") {
				date1 = $("#begintime").datebox("getValue");
			} else {
				date1 = null;
			}
			if ($("#endtime").datebox("getValue") != "") {
				date2 = $("#endtime").datebox("getValue");
			} else {
				date2 = null;
			}
			var data = {
				/* "createrid" : $("#user").textbox("getValue"), */
				"createtime" : date1,
				"edittime" : date2
			};
			$("#employee_list").datagrid("load", {
				jsonStr : JSON.stringify(data)
			});
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
			showDialogWH("添加计量信息", "prickle/prickleInfo.html?id=0", 440, 150);
		});
		$("#btnEdit").bind(
				"click",
				function() {
					var row = $("#employee_list").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					}
					showDialogWH("修改计量信息", "prickle/prickleInfo.html?id="
							+ row.id, 440, 150);
				});
		$("#btnDelete").bind("click", function() {
			var row = $("#employee_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
				if (data) {
					$.messager.progress();
					$.ajax({
						type : 'POST',
						url : "prickle/deletePrickle.html",
						data : {
							"id" : row.id
						},
						success : function() {
							$.messager.progress('close');
							$("#employee_list").datagrid("unselectAll");
							$("#employee_list").datagrid("reload");
						}
					});
				}
			});
		});
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="employee_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<!-- <thead>
			<tr>
				<th data-options="field:'name',width:120">计量名称</th>
				<th data-options="field:'creater',width:100">创建人</th>
				<th data-options="field:'editer',width:110">修改人</th>
				<th data-options="field:'createtime',width:130">创建时间</th>
				<th data-options="field:'edittime',width:130">修改时间</th>
				<th data-options="field:'id',hidden:true"></th>
			</tr>
		</thead> -->
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle;">
		<div class="cz_div_title">
			<a id="btnAdd" class="easyui-linkbutton"
				data-options="plain:false,iconCls:'icon-add'">新增</a> <a id="btnEdit"
				class="easyui-linkbutton"
				data-options="plain:false,iconCls:'icon-edit'">修改</a> <a
				id="btnDelete" class="easyui-linkbutton"
				data-options="plain:false,iconCls:'icon-clear'">删除</a>
		</div>
		<div class="cz_div">
			<form id="specification" method="post">
				<%-- 			创建人:<input id="user" class="easyui-combobox" style="width: 150px"
				value="${employee.getDepartment().getId()}"/> &nbsp;&nbsp;  --%>
				添加时间区间:<input id="begintime" class="easyui-datetimebox"
					style="width: 160px" editable="false" />&nbsp;&nbsp;~&nbsp;&nbsp;<input
					id="endtime" class="easyui-datetimebox" style="width:160px"
					validType='compareDate[begintime]' /> &nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" id="search">查询</a>
			</form>
		</div>
	</div>
</body>
</html>
