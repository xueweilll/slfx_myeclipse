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

<title>调度接受单列表</title>
<%@include file="../header.jsp"%>

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
		var actor=${actor};
		if(actor==0){
			$("#compulsoryDistory").show();
		}
		$("#receipt_list").datagrid({
			title : '调度令列表',
			iconCls : 'icon icon-icon10',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'areareceipt/pageBind.html',
			queryParams: {
				type:$("#type").val(),
				isAduit:"0",
				typeDate:"0"
			},
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){				
				showDialogWH("查看调度令信息","areareceipt/receiptInfo.html?id=" + row.id +"&state=1&type=" + $("#type").val(), 420, 480);
			}
		});
		
		$("#begintime").datetimebox({
			onChange : function() {
				$('#endtime').datetimebox('enableValidation');
			}
		});
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2,'', {
			text: '清空',
			handler: function(target){
				$(target).datetimebox('setValue','');
				$(target).datebox('hidePanel');  
			}
		})
		$('#begintime').datetimebox({
			editable:false,
			width:160,
			buttons:buttons
		})
		$('#endtime').datetimebox({
			editable:false,
			width:160,
			buttons:buttons
		})
		
		
		var p = $("#receipt_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		$("#search").bind("click", function() {
			$("#receipt_list").datagrid('unselectAll');
			$("#receipt_list").datagrid('load', {
				isAduit:0,
				type:$("#type").val(),
				receipter:$("#receipter").textbox("getValue"),
				code:$("#code").textbox("getValue"),
				/* name:$("#name").textbox("getValue"), */
				begintime:$("#begintime").datetimebox("getValue"),
				endtime:$("#endtime").datetimebox("getValue"),
				typeDate:0
			});
			$("#receipt_list").datagrid("unselectAll");
		});

		$("#btnAdd").bind("click", function() {
			showDialogWH("添加调度令信息", "areareceipt/receiptInfo.html?id=0&state=0&type=" + $("#type").val(), 420, 480);
		});
		
		//提交
		$("#btnCommit").bind("click",function(){
			var row = $("#receipt_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			$.ajax({
				type : 'POST',
				url : "areareceipt/commit.html",
				data : {
					"id" : row.id
				},
				success : function(msg) {
					data = eval('(' + msg + ')');
					$.messager.progress("close");
					if (data.result) {
						$.messager.alert("操作提示", "提交成功！");
		
						reflush();
					} else {
						$.messager.alert("操作提示", data.msg, "error");
					}
				}
			});
		});

		$("#btnModify").bind("click",function() {
			var row = $("#receipt_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			if(row.state != "0"){
				$.messager.alert("操作提示", "该调度指令已提交，不能进行修改！", "error");
				return false;
			}
			showDialogWH("编辑调度令信息","areareceipt/receiptInfo.html?id=" + row.id +"&state=0&type=" + $("#type").val(), 420, 480);
		});

		$("#btnDelete").bind("click", function() {
			var row = $("#receipt_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			if(row.state != "0"){
				$.messager.alert("操作提示", "新建状态的接收单才能被删除！", "error");
				return false;
			}
			$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
				
				if (data) {
					$.messager.progress();
					$.ajax({
						type : 'POST',
						url : "areareceipt/delete.html",
						data : {
							"id" : row.id
						},
						success : function(msg) {
							data = eval('(' + msg + ')');
							$.messager.progress("close");
							if (data.result) {
								$.messager.alert("操作提示", "删除成功！");
								$("#receipt_list").datagrid('unselectAll');
								reflush();
							} else {
								$.messager.alert("操作提示", data.msg, "error");
							}
						}
					});
				}
			});
		});
	});

	function compulsoryDistory(){
		var row = $("#receipt_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "areareceipt/compulsorydelete.html",
					data : {
						"id" : row.id
					},
					success : function(msg) {
						data = eval('(' + msg + ')');
						$.messager.progress("close");
						if (data.result) {
							$.messager.alert("操作提示", "删除成功！");
							reflush();
						} else {
							$.messager.alert("操作提示", data.msg, "error");
						}
						$("#receipt_list").datagrid("unselectAll");
					}
				});
			}
		});
	}
	
	function reflush() {
		$("#receipt_list").datagrid('reload');
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<input id="type" type="hidden" value="${type}" />
	<table id="receipt_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'Code',width:150">调度令编号</th>
				<th data-options="field:'DispatchType',width:150">调度令类型</th>
				<th data-options="field:'Way',width:100">调度令方式</th>
				<th data-options="field:'Launcher',width:100">调度令发起人</th>
<!-- 			<th data-options="field:'LaunchTime',width:130">发起时间</th>
				<th data-options="field:'EndTime',width:130">结束时间</th> -->
				<th data-options="field:'Receipter',width:130">调度令接收人</th>
				<th data-options="field:'ReceipteTime',width:130">调度令接收时间</th>
				<th data-options="field:'stateName',width:130">调度令状态</th>
			</tr>
		</thead>
	</table>

	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle;line-height:30px;">
		<div  class="cz_div" style="border-bottom:1px solid #ccc">
		<a id="btnAdd" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-add'">新增</a> <a id="btnModify"
			class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-edit'">修改</a> <a
			id="btnDelete" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-clear'">删除</a> <a
			id="btnCommit" class="easyui-linkbutton" style="display:none"
			data-options="plain:true,iconCls:'icon-clear'">提交</a>
			<a id="compulsoryDistory"  href="javascript:void(0)" class="easyui-linkbutton" onclick="compulsoryDistory()" data-options="plain:false,iconCls:'icon-clear'" style="display: none;">强制删除</a>
        </div>
        <div  class="cz_div">
        <div style="display:none;">
			调度令编号:&nbsp;<input id="code" class="easyui-textbox" style="width: 120px"> &nbsp;&nbsp;
			</div> 
			调度令接收人:&nbsp;<input id="receipter" class="easyui-textbox" style="width: 120px"/>&nbsp;&nbsp;
			调度令接收时间区间 :&nbsp;<input id="begintime" style="width: 160px"/> ~ 
			<input id="endtime" class="easyui-datetimebox"	data-options="width:160,validType:['compareDate[begintime]']"/>&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" id="search">查询</a>
       </div>
	</div>
</body>
</html>
