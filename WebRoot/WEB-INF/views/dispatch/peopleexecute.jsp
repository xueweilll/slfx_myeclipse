<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'pathchInfo.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
</head>

<body>	
	<form id="ffemp" method="post"  enctype="multipart/form-data"
		style="margin-top: 10px; text-align: center;">
		<div>						
			<table id="execute"></table>
		</div>		
	</form>
	<script type="text/javascript">
		$(function() {
			$('#execute').datagrid({
				url:'dispatchinstructions/dispatchList.html',				
				columns:[[
					{field:'',title:'枢纽'},
			        {field:'',title:'执行人'},
			        {field:'',title:'内容'},
			        {field:'',title:'闸门执行时间'},
			        {field:'',title:'机组执行时间'},
			        {field:'',title:'开始时间'},
			        {field:'',title:'结束时间'}
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
			var p = $("#execute").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		});
	</script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
</body>
</html>
