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

<title>固定资产二维码列表</title>
<%@include file="../header.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<form id="ffemp" method="post" style="margin: 10px; text-align: left">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label for="code" style="display: inline-block;width:80px">资产编号：</label>
				</td>
				<td><input id="code" class="easyui-textbox"
					style="width:300px;" name="code" data-options="disabled:true" 
					value="${assets.getCode()}" /></td>
			</tr>
             <tr>
				<td><label>枢纽名称:</label></td>
				<td><input id="sid" name="sid" style="width:300px;"class="easyui-textbox" disabled="disabled" 
					value="${sname}" /></td>
			</tr>
			<tr>
				<td><label for="name"
					style="display: inline-block; width: 80px"> 物品名称：</label></td>
				<td><input id="name" style="width:300px;" name="name"
					class="easyui-textbox" data-options="required : true,validType:'judgeNull'"  disabled="disabled"
					value="${assets.getName()}" /></td>
			</tr>
			<tr>
				<td><label for="principal"
					style="display: inline-block;width:80px">负责人：</label></td>
				<td><input id="principal" style="width:300px;" name="sname"
					class="easyui-textbox" value="${assets.getPrincipal()}" disabled="disabled" /></td>
			</tr>
			<tr>
				<td><label for="moblie"
					style="display:inline-block; width:100px">联系电话:</label></td>
				<td><input id="moblie" style="width:300px;" name="moblie"
					class="easyui-textbox"
					data-options="validType:['mobile','length[1,11]']" disabled="disabled"
					value="${assets.getMoblie()}" /></td>
			</tr>
			<tr>
				<td><label for="memo" style="display:inline-block; width:100px">资产描述：</label></td>
				<td><input id="memo" style="width:300px;"
					name="memo" class="easyui-textbox"
					data-options="multiline:true,height:120" disabled="disabled"
					style="height:80px" value="${assets.getMemo()}" /></td>
			</tr>
		</table>
		</br>
	</form>
</body>
</html>
