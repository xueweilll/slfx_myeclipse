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
    <title>代办事项执行情况</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body class="easyui-layout"  >
    <div class="easyui-layout" style="width:100%;height:100%;">
    	<div><input type="hidden" value="${id}" id="id2"/></div>
        <div data-options="region:'center',title:''">
        	<table id="todolistpeople2"></table>
        </div>
    </div>
    <script>
		$(function(){
			var todoid=document.getElementById("id2").value;		
			$('#todolistpeople2').datagrid({
				url:'todolist/todolistSearch.html?todoid='+todoid,
				columns:[[	       			        
			        {field:'title',title:'标题',width:100},
			        {field:'contents',title:'内容',width:300},
			        {field:'dodate',title:'待办时间',width:150},
			        {field:'state',title:'状态',width:100},
			        {field:'username',title:'代办人',width:100},
			        {field:'ispc',title:'PC消息提醒',width:100},
			        {field:'isphonemess',title:'手机短信提醒',width:100},
			        {field:'state',title:'状态',width:100},
			        {field:'viewdate',title:'查看时间',width:150},
			        {field:'feedbackdate',title:'反馈时间',width:150}
			    ]],
			    toolbar:'#toolbar',
			    pageSize : 20,
			    width : 'auto',
				height : 'auto',
				singleSelect : true,
				pagination : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true
			})
			var p = $("#todolistpeople2").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})		
		
	</script>
  </body>
</html>
