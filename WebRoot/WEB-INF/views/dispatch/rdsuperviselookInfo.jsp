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

<title>My JSP 'rdsuperviselookInfo.jsp' starting page</title>

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
	<input type="hidden" id="rdid" value="${rd.getId()}">
	<form id="ff" method="post" style="margin: 10px; text-align: center;">
		<div style="float: left;">
			<div style="float: left;">
				<label>分解单编号:</label><input class="easyui-textbox" id="code"
					data-options="disabled:true,width:230" style="width:230"
					value="${rd.getCode()}">
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令编号:</label><input id="receiptid" class="easyui-textbox"
					data-options="disabled:true,width:230"
					value="${rd.getReceipt().getCode()}" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令方式:</label><input class="easyui-textbox" id="way"
					value="${rd.getDispatchtype()=="1"?"电话":"传真"}"
					data-options="disabled:true,width:230" style="" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令发起时间:</label><input id="launchTime" class="easyui-textbox"
					value="${launchtime}" data-options="disabled:true"
					style="width:230" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令发起人:</label><input class="easyui-textbox" id="launcher"
					data-options="disabled:true" style="width:230"
					value="${rd.getReceipt().getLauncher()}">
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令结束时间:</label><input id="endTime" class="easyui-textbox"
					data-options="disabled:true" style="width:230" value="${endtime}" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令接收人:</label><input class="easyui-textbox" id="receipter"
					data-options="disabled:true" style="width:230"
					value="${rd.getNames().getReceiptName()}" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令接收时间:</label><input id="receipteTime"
					class="easyui-textbox" data-options="disabled:true"
					style="width:230" value="${receiptetime}" />
			</div>
		</div>
		<div style="float: left;margin-top:10px">
			<label>调度令备注:</label><input class="easyui-textbox" id="rmemo"
				data-options="disabled:true,multiline:true"
				style="width:580px;height: 60px;"
				value="${rd.getReceipt().getMemo()}">
		</div>
		<div style="float: left;margin-top:10px">
			<label>分解单备注:</label><input class="easyui-textbox" id="memo"
				data-options="multiline:true,disabled:true" value="${rd.getMemo()}"
				style="width:580px;height: 60px;">
		</div>
		<div style="float: left;margin-top:10px">
			<table id="sp_list" cellspacing="0" cellpadding="0"></table>
		</div>



	</form>

	<script type="text/javascript">
		$(function() {
			$('#sp_list').datagrid({
				title : '督察内容明细',
				width : '680',
				height : '200',
				nowrap : false,
				striped : true,
				collapsible : false,
				idField : 'id',
				url : 'supervise/spListBind.html',
				queryParams : {
					rdid : $("#rdid").val()
				},
				rownumbers : true,
				columns : [ [ {
					field : 'callbackpeople',
					title : '督察人',
					width : 90
				}, {
					field : 'callbacktime',
					title : '督察时间',
					width : 130
				}, {
					field : 'callbackunit',
					title : '督察单位',
					width : 90
				}, {
					field : 'operater',
					title : '操作人',
					width : 80
				}, {
					field : 'callback',
					title : '督察内容',
					width : 230
				} ] ]
			});

		});
	</script>
</body>
</html>

