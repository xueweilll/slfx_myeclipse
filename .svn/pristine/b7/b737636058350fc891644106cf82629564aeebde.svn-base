<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<%@include file="../header.jsp"%>
    <title>My JSP 'schedule.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
		$(function(){
		$("#schedule_list").datagrid({
			title:'待办事件',
			iconCls:'icon icon-dutyers',
			width:'auto',
			height:'auto',
			pageSize:20,
			nowrap:false,
			striped:true,
			border:true,
			collapsible:false,
			fit:true,
			//url:'schedule/bind.html',
			remoteSort:false,
			idField:'scheduleId',
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			frozenColumns:[[{field:'ck',checkbox:true}]],
			toolbar:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){add()}
			},{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){distory()}
			}]
		});
		
		var p = $("#schedule_list").datagrid('getPager');
		$(p).pagination({
			pageList:[5,10,15,20],
			beforePageText:'第',
			afterPageText:'页        共{pages}页',
			displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
		})
	});
	
	function add(){
		showDialogWH("添加待办事件", "schedule/scheduleInfo.html?id=0","450px","300px");
	}	
	
	function distory(){
		var row = $("#schedule_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "schedule/distory.html",
					data : {
						entityId : row.scheduleId
					},
					success : function() {
						$.messager.progress('close');
						$("#schedule_list").datagrid("reload");
					}
				});
			}
		});
	}
	</script>
  
  <body>
    <table id="schedule_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th data-options="field:'title',width:120">标题</th>
				<th data-options="field:'content',width:320">内容</th>
 				<th data-options="field:'initiator',width:120">发起人</th> 
				<th data-options="field:'initiatetime',width:120">发起时间</th>
 				<th data-options="field:'scheduler',width:120">待办人</th> 
				<th data-options="field:'requiredtime',width:120">待办时间</th>
				<th data-options="field:'completetime',width:120">完成时间</th>
			</tr>
		</thead>
	</table>
  </body>
</html>
