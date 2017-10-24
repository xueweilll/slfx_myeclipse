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
<title>My JSP 'logger.jsp' starting page</title>

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
			"contents" : null,
			"createtime" : null,
			"endtime" : null,
			"levels" : null
		};
		$("#logger_list").datagrid({
			title : '系统日志列表',
			iconCls : 'icon icon-icon8',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'systemLogger/systemLoggerBind.html',
			remoteSort : false,
			idField : 'powerId',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			/* <th data-options="field:'levels',width:100">日志级别</th>
			<th data-options="field:'contents',width:400">日志内容</th>
			<th data-options="field:'createtime',width:150">录入时间</th> */
			columns : [ [ {
				field : 'levels',
				title : '日志级别',
				width : fixWidth(0.2)
			}, {
				field : 'contents',
				title : '日志内容',
				width : fixWidth(0.6)
			}, {
				field : 'createtime',
				title : '录入时间',
				width : fixWidth(0.2)
			} ] ]
		});
		$("#loggerLever").combobox({
			url : 'paramers/loggerLever.html',
			valueField : 'id',
			textField : 'text',
			editable : false,
			required : true,
			value : '8'
		});
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2, '', {
			text : '清空',
			handler : function(target) {
				console.info(target.id);
				$("#" + target.id).datetimebox('setValue', '');
			}
		});
		$("#editTime").datetimebox({
			editable : false,
			buttons : buttons,
			onChange : function() {
				$("#endTime").datetimebox('enableValidation')
			}
		});
		$("#endTime").datetimebox({
			editable : false,
			buttons : buttons
		});
		$("#search").bind("click", function() {
			var isValid = $("#sys").form('validate');
			if (!isValid) {
				$.messager.alert('错误！', '开始时间大于结束时间', 'error')
				return false;
			}
			var date1;
			var date2;
			if ($("#editTime").datebox("getValue") != "") {
				date1 = $("#editTime").datebox("getValue");
			} else {
				date1 = null;
			}
			if ($("#endTime").datebox("getValue") != "") {
				date2 = $("#endTime").datebox("getValue");
			} else {
				date2 = null;
			}
			var levels
			if ($("#loggerLever").combobox("getValue") != 8) {
				levels = $("#loggerLever").datebox("getValue");
			} else {
				levels = null;
			}

			var data = {
				"contents" : $("#loggerDsp").textbox("getValue"),
				"createtime" : date1,
				"endtime" : date2,
				"levels" : levels
			};
			$("#logger_list").datagrid("load", {
				jsonStr : JSON.stringify(data)
			});
			$.messager.progress("close");
		});

		var p = $("#logger_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

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
		<!-- <thead>
			<tr>
				<th data-options="field:'levels',width:100">日志级别</th>
				<th data-options="field:'contents',width:400">日志内容</th>
				<th data-options="field:'createtime',width:150">录入时间</th>
			</tr>
		</thead> -->

	</table>
	<div id="tb">
		<form id="sys" method="post" class="cz_div" style="margin-bottom:0px">
			日志级别:<input id="loggerLever" class="easyui-combobox"
				style="width: 110px" data-options="required:true" /> &nbsp;&nbsp;
			日志内容:<input id="loggerDsp" class="easyui-textbox"
				style="width: 110px"> &nbsp;&nbsp; 录入时间区间:<input
				id="editTime" class="easyui-datetimebox" style="width: 155px">&nbsp;&nbsp;~&nbsp;&nbsp;<input
				id="endTime" class="easyui-datetimebox" style="width: 155px"
				validType='compareDate[editTime]'> &nbsp;&nbsp; <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" id="search">查询</a>
		</form>
	</div>
</body>
</html>
