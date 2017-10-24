<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>不合格项</title>
<%@include file="../../header.jsp"%>
</head>
<body class="easyui-layout" id="cc">
	<table id="disqualification_list" cellspacing="0" cellpadding="0"></table>
	<script type="text/javascript">
	$(function() {
		$("#disqualification_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : '',
			idField : 'id',
			singleSelect : false,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb' ,
			columns:[[    
			          {field:'stationName',title:'枢纽名称',width:160},    
			          {field:'name',title:'检查内容',width:200},    
			          {field:'result',title:'检查结果',width:160}
			      ]]
		}); 
		var p = $("#disqualification_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	</script>
</body>
</html>