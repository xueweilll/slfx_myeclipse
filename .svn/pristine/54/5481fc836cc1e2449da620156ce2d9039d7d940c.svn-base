<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../../header.jsp"%>
<title>执行人实施</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function() {
		$("#patrolImplement_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : "patrolImplement/queryData.html",
			remoteSort : false,
			rownumbers : true,
			pagination : true,
			singleSelect:true,
			toolbar : '#tb',
			queryParams : {
				classes:"2"
			},
/* 			onDblClickRow:function(rowIndex,rowData){
				searchInfo(rowData);
			}, */
			columns:[[
			          {field:'CODE',title:'签发单编号',width:160},    
			          {field:'NAME',title:'枢纽名称',width:200},    
			          {field:'PARTTIME1',title:'巡检时间',width:160},    
			          {field:'USERNAME',title:'制单人',width:100},
			          {field:'CREATETIME1',title:'制单时间',width:160},
			          {field:"STATE",hidden:true}
			      ]]
		}); 
		var p = $("#patrolImplement_list").datagrid('getPager');
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
	function selectbig(){
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		$("#patrolImplement_list").datagrid('load',{
			startTime: $("#starttime").datetimebox("getValue"),
			endTime: $("#endtime").datetimebox("getValue"),
			classes:"2"
		});
	}
	function add() {
		showDialogWH("添加实施单信息", "patrolImplement/getDemo.html?classes=2",650,600);
	}

	function edit() {
		var row = $("#patrolImplement_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "warning");
			return false;
		}
		if (row.STATE!=0) {
			$.messager.alert("操作提示", "表单已提交，不能修改！", "warning");
			return false;
		}
		showDialogWH("编辑实施单信息", "patrolImplement/patrolImplementInfo.html?id=" + row.id,650,600);
	}
	function searchInfo(row){
		showDialogWH("查看明细", "patrolImplement/patrolImplementInfo.html?id=" + row.id,650,600);
	}
	function distory(){
		var row = $("#patrolImplement_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "warning");
			return false;
		}
		if (row.stateValue!="0") {
			$.messager.alert("操作提示", "表单已提交，不能删除！", "warning");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "patrolImplement/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#patrolImplement_list").datagrid("unselectAll");
						$("#patrolImplement_list").datagrid("reload");
					}
				});
			}
		});
	}
	var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
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
	<div  data-options="region:'center'">
		<table id="patrolImplement_list" cellspacing="0" cellpadding="0"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<div class="cz_div">
			<a id="adds"   href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon_add" plain="false">添加</a>
<!-- 			<a id="edits"  href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()" iconCls="icon_edit" plain="false">编辑</a>
			<a id="distory"  href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon_delete" plain="false">删除</a> -->&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			巡检时间区间: <input id="starttime" type="text" data-options="width:160,editable:false,buttons: buttons">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
