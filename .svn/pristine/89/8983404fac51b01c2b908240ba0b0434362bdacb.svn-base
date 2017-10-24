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
<title>My JSP 'messageInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="sdid" type="hidden"
		value="${station.getSelfDispatch().getId()}" />
		<p>
			<img alt="" src="images/zlogo.png" style="vertical-align: middle;"> <span align="center"
				style="font-size: 25;vertical-align: middle;">执&nbsp;&nbsp;行&nbsp;&nbsp;单</span>
		</p>
	<form id="addsdexecute" method="post"
		style="margin:10px; text-align: left;">
		<table>
			<tr>
				<td><label>具体实施单编号:</label></td>
				<td><input class="easyui-textbox" style="width: 320px;" value="${station.getSelfDispatch().getCode()}" /></td>
			</tr>
			<tr>
				<td><label for="sid">执行枢纽:</label></td>
				<td><input id="sid" class="easyui-textbox" style="width: 320px;" value="${station.getStation().getName()}" /></td>
			</tr>
			<tr>
				<td><label>调度发起人:</label></td>
				<td><input class="easyui-textbox"
				style="width: 320px;"
				value="${station.getSelfDispatch().getUser().getUsername()}" /></td>
			</tr>
			<tr>
				<td><label>调度发起时间:</label></td>
				<td><input class="easyui-datetimebox" style="width: 320px;" value="${promotetime}" /></td>
			</tr>
			<tr>
				<td><label>制单人:</label></td>
				<td><input class="easyui-textbox"
				style="width: 320px;"
				value="${station.getSelfDispatch().getCreateUser().getUsername()}" /></td>
			</tr>
			<tr>
				<td><label>制单时间:</label></td>
				<td><input class="easyui-datetimebox"
				style="width: 320px;" value="${createtime}" /></td>
			</tr>
		</table>
		<div
			style="width: 420px;height: 200px;margin-left: 2px;margin-top: 10px;margin-bottom: 0px;display:none">
			<table id="stations"></table>
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
			var sdid = $('#sdid').val();
			$('#stations').datagrid({
				url : 'sdexecute/findInstructionBySdid.html?sdid=' + sdid,
				columns : [ [ {
					field : 'instruction1',
					title : '闸门操作'
				}, {
					field : 'gateoperatetime',
					title : '闸门操作时间'
				
				}, {
					field : 'instruction2',
					title : '机组操作'
				}, {
					field : 'unitoperatetime',
					title : '机组操作时间'
				} ] ],
				width : '420',
				height : '200',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false
			});
			//reflush();
		});
		function reflush() {
			document.getElementById('sdexecute.htmlifm').contentWindow.$(
					'#exelist').datagrid('reload');
		}
	</script>

</body>
</html>
