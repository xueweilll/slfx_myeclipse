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
<title>巡检确认</title>
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
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(1, 0, {
			text: '清空',
			handler: function(target){
				$(target).datetimebox('setValue','');
				$(target).datetimebox('hidePanel');
			}
		});
		$('#begin').datetimebox({
			editable:false,
			width:160,
			buttons:buttons,
			onChange:function(){
				$("#end").datetimebox('enableValidation');
			}
		});
		$('#end').datetimebox({
			editable:false,
			width:160,
			buttons:buttons,
			validType:['compareDate[begin]']
		});
		$("#patrolconfirmlist").datagrid({
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'patrolconfirm/patrolconfirmlist.html',
			queryParams : {
				all : 0
			},
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			columns:[[
				{field:'code',title:'巡检计划表编号',width:'150'},
				{field:'stationname',title:'枢纽名称',width:'150'},
				{field:'patroltime',title:'实际巡检时间',width:'150'},
				{field:'creater',title:'制单人',width:'150'},
				{field:'createtime',title:'制单时间',width:'150'},
				{field:'state',title:'状态',width:'150'}
            ]],
            toolbar:'#tb',
			onDblClickRow:function(index,row){
				showDialogWH("确认巡检记录","patrolconfirm/patrolokdbl.html?patrolid="
						+ row.patrolid, 800, 600);
			}
		});
		var p = $("#patrolconfirmlist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});	
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				$('#r_code').textbox('setValue', '');
				$('#rd_code').textbox('setValue', '');
				if (index == "0") {
					$("#confirm").show();
				} else {
					$("#confirm").hide();
				}
				$("#patrolconfirmlist").datagrid("unselectAll");
				$("#patrolconfirmlist").datagrid("load", {
					all : index
				});
			}
		});
		$("#search").bind("click", function() {			
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#patrolconfirmlist").datagrid("load", {
				all : index,
				begin : $("#begin").datetimebox("getValue"),
				end : $("#end").datetimebox("getValue")
			});
		});
	});
	function ok() {
		var row = $("#patrolconfirmlist").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else {
			showDialogWH("确认巡检记录","patrolconfirm/patrolok.html?patrolid="
			+ row.patrolid, 800, 600);
		}	
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="patrolconfirmlist" cellspacing="0" cellpadding="0"
		data-options="">
		<!-- <thead>
			<tr>
				<th data-options="field:'code',width:150">巡检计划表编号</th>
				<th data-options="field:'patroltime',width:150">实际巡检时间</th>
				<th data-options="field:'creater',width:140">制单人</th>
				<th data-options="field:'createtime',width:150">制单时间</th>
				<th data-options="field:'state',width:100">状态</th>
			</tr>
		</thead> -->
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle;">
		<div id="tt" class="easyui-tabs">
			<div id="myTask" title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div id="allData" title="全部数据"
				style="padding:20px;display:none;width: 100px;"></div>			
		</div>
		<div style="" id="confirm" class="cz_div_title">
			<a  class="easyui-linkbutton" iconCls="icon-ok" plain="false" onclick="ok()">确认</a>
		</div >
		<div class="cz_div">
				实际巡检时间:&nbsp;&nbsp;<input id="begin" /> ~ <input id="end"/>
				&nbsp;&nbsp;<a class="easyui-linkbutton"
				onclick="search()" id="search" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
