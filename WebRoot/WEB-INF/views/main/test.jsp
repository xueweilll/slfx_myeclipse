<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head >
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>江苏华电戚墅堰发电有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/basic.js"></script>
<script type="text/javascript">
function test(){
	$.post("timeData/valveDetails.html",function(data){
		console.info(data);
	});
}
function test1(){
	$.post("timeData/unitDetails.html",function(data){
		console.info(data);
	});
}
function test2(){
	$.post("timeData/waterDetails.html",function(data){
		console.info(data);
	});
}
</script>
</head>
<body style="height: 100%">
	<input type="button"  value="闸门" style="width: 100px;" onclick="test()" /><br/>
	<input type="button"  value="机组" style="width: 100px;" onclick="test1()" /><br/>
	<input type="button"  value="水情" style="width: 100px;" onclick="test2()" /><br/>
</body>
</html>
