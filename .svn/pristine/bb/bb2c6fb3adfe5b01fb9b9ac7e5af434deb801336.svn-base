<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理处查看弹窗</title>

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
<style>
.datagrid-row-selected {
    background: #ffffff none repeat scroll 0 0;
    color: #000000;
}
</style>
	<div align="center">		
		<p><img alt="" src="images/zlogo.png">
		<span id="titles" align="center" style="font-size: 25;">汇&nbsp;&nbsp;总&nbsp;&nbsp;单</span></p>
	</div>
	<form id="ff" method="post"
		style="margin: 10px; text-align: center;">
		<div align="center"><table id="departmentPatrol" cellspacing="0" cellpadding="0"></table></div>
	</form>
	<script type="text/javascript">
		$(function(){
			var jsonstr = '${jsonStr}';
			$('#departmentPatrol').treegrid({    
			    data:JSON.parse(jsonstr),    
			    idField:'id',    
			    treeField:'name',
			    singleSelect : false,
			    width : '605',
				height : '350',
			    columns:[[
			        {title:'名称',field:'name',width:180},   
			        {title:'',field:'content',width:380}  
			    ]]    
			});
		});
		
	</script>
</body>
</html>
