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
/* 		$("#user").combobox({
			url : 'operateLogger/getUsername.html',
			textField : 'name',
			valueField : 'id',
			value : $("#user").val(),
			editable : false
		}); */
		$("#employee_list").datagrid({
			title : '物资列表',
			iconCls : 'icon icon-icon5',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'materials/systemMaterials.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			/* <th data-options="field:'mname',width:120">物资名称</th>
			<th data-options="field:'pname',width:120">计量名称</th>
			<th data-options="field:'sname',width:120">规格名称</th>
			<th data-options="field:'creater',width:100">制单人</th>
			<th data-options="field:'handler',width:110">处理人</th>
			<th data-options="field:'createtime',width:130">创建时间</th>
			<th data-options="field:'handlertime',width:130">处理时间</th>
			<th data-options="field:'memo',width:130">描述</th>
			<th data-options="field:'state',width:100">状态</th>
			<th data-options="field:'id',hidden:true"></th> */
			columns : [ [ 
{
	field : 'type',
	title : '物资类别',
	width : fixWidth(0.1)
},             
			 {
				field : 'mname',
				title : '物资名称',
				width : fixWidth(0.1)
			}, {
				field : 'pname',
				title : '计量名称',
				width : fixWidth(0.1)
			}, {
				field : 'sname',
				title : '规格名称',
				width : fixWidth(0.1)
			}, {
				field : 'creater',
				title : '制单人',
				width : fixWidth(0.05)
			}, {
				field : 'handler',
				title : '处理人',
				width : fixWidth(0.05)
			}, {
				field : 'createtime',
				title : '创建时间',
				width : fixWidth(0.1)
			}, {
				field : 'handlertime',
				title : '处理时间',
				width : fixWidth(0.1)
			}, {
				field : 'memo',
				title : '描述',
				width : fixWidth(0.2)
			}, {
				field : 'state',
				title : '状态',
				width : fixWidth(0.1)
			}, {
				field : 'id',
				hidden : true
			} ] ]
		});
		$("#pname").combobox({
			url : 'materials/getUsername.html',
			textField : 'name',
			valueField : 'id',
			value : $("#pname").val(),
			editable : false
		});
		$("#sname").combobox({
			url : 'materials/getUsername.html',
			textField : 'name',
			valueField : 'id',
			value : $("#sname").val(),
			editable : false
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
				$("#endtime").datetimebox('enableValidation')
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
				/* "creater" : $("#user").textbox("getValue"), */
				"createtime" : date1,
				"handlertime" : date2
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
		$("#btnAdd").bind(
				"click",
				function() {
					showDialogWH("添加物资信息", "materials/materialsInfo.html?id=0",
							470, 400);
				});
		$("#btnEdit").bind(
				"click",
				function() {
					var row = $("#employee_list").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					}
					showDialogWH("修改物资信息", "materials/materialsInfo.html?id="
							+ row.id, 470, 400);
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
						url : "materials/deleteMaterials.html",
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
			})
		})
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="employee_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<!-- <thead>
			<tr>
			    <th data-options="field:'mname',width:120">物资名称</th>
			    <th data-options="field:'pname',width:120">计量名称</th>
				<th data-options="field:'sname',width:120">规格名称</th>
				<th data-options="field:'creater',width:100">制单人</th>
				<th data-options="field:'handler',width:110">处理人</th>
				<th data-options="field:'createtime',width:130">创建时间</th>
				<th data-options="field:'handlertime',width:130">处理时间</th>
				<th data-options="field:'memo',width:130">描述</th>
				<th data-options="field:'state',width:100">状态</th>
				<th data-options="field:'id',hidden:true"></th>
			</tr>
		</thead> -->
	</table>
	<div id="tb">
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
				创建时间区间:<input id="begintime" class="easyui-datetimebox"
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
