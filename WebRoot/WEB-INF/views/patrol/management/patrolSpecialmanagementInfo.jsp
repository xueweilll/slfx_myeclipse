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

<title>管理处审阅弹窗(应急)</title>

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
	<div align="center">
		<p>
			<img alt="" src="images/zlogo.png"> <span id="titles"
				align="center" style="font-size: 25;">汇&nbsp;&nbsp;总&nbsp;&nbsp;单</span>
		</p>
	</div>
	<input type="hidden" id="type" value="${type}">
	<input type="hidden" id="isid" value="${issue.getId()}">
	<form id="ff" method="post" style="margin: 10px; text-align: center;">
		<div  style="float: left;" align="left">
			<div style="float: left;"><label style="width: 70px;text-align: center;">编号:</label>
			<input class="easyui-textbox"  data-options="width:540,disabled:true" required="true"  value="${issue.getCode()}">
			</div>
		</div>
		<%-- <div  style="float: left;margin-bottom:10px;" align="left">
			<div style="float: left;"><label style="width: 70px;text-align: center;">签发人:</label>
			<input class="easyui-textbox"  data-options="width:230,disabled:true" required="true"  value="${issue.getCreater()}">
			</div>
			<div style="float:right;"><label style="width: 70px;text-align: center;">签发时间:</label>
			<input class="easyui-textbox"  data-options="width:230,disabled:true" required="true"  value="${issue.getCreatetime()}">
			</div>
		</div> --%>
		<div  style="float: left;margin-bottom:10px;" align="left">
			<div style="float: left;"><label style="width: 70px;text-align: center;">签发内容:</label>
			<input class="easyui-textbox"  data-options="multiline:true,width:540,height:40,disabled:true" required="true"  value="${issue.getContent()}">
			</div>
		</div>
		<div  style="float: left;margin-bottom:10px;" align="left">
			<div style="float: left;"><label style="width: 70px;text-align: center;">备注:</label>
			<input class="easyui-textbox"  data-options="multiline:true,width:540,height:40,disabled:true" required="true"  value="${issue.getRemark()}">
			</div>
		</div>
		<div align="center">
			<table id="departmentPatrol" cellspacing="0" cellpadding="0"></table>
		</div>
		</br>
		<div id="btn">
			<a id="btnAdd" href="javascript:void(0)" onclick="add()"
				class="easyui-linkbutton" data-options="iconCls:'icon_add'">审阅</a>&nbsp;&nbsp;
			<!-- <a id="btnAdd1"
				href="javascript:void(0)" onclick="add(1)" class="easyui-linkbutton"
				data-options="iconCls:'icon_commit'">提交</a>&nbsp;&nbsp; -->
			<a id="btnCancel" href="javascript:void(0)" onclick="closed()"
				class="easyui-linkbutton" data-options="iconCls:'icon_delete'">关闭</a>
		</div>
	</form>
	<script type="text/javascript">
		function add() {
			$.messager.progress();
			$.ajax({
				type : "POST",
				url : "patrolSpecialmanagement/save.html",
				dataType : "text",
				data : {
					"isid" : $("#isid").val()
				},
				success : function(msg) {
					data = eval('(' + msg + ')');
					$.messager.progress('close');
					if (data.result) {
						reflush();
						$.messager.alert("操作提示", data.msg, "info");
					} else {
						$.messager.alert("操作提示", data.msg, "error");
					}
					$('#dialog').window('close');
				}
			});
		}
		function closed() {
			$('#dialog').window('close');
		}
		function reflush() {
			document.getElementById('patrolSpecialmanagement.htmlifm').contentWindow.$('#psprojectreport_list')
					.datagrid('reload');
			document.getElementById('patrolSpecialmanagement.htmlifm').contentWindow.$('#psprojectreport_list')
			.datagrid('unselectAll');
		}
		$(function() {
			var typedata = $("#type").val();
			if (typedata == 1) {
				$("#titles").html("审&nbsp;&nbsp;阅&nbsp;&nbsp;单");
			} else {
				$("#btnAdd").hide();
				$("#titles").html("明&nbsp;&nbsp;细&nbsp;&nbsp;单");
			}
			var jsonstr = '${jsonMap}';
			console.info(jsonstr);
			$('#departmentPatrol').treegrid({
				data : JSON.parse(jsonstr),
				idField : 'id',
				treeField : 'name',
				singleSelect : true,
				width : '605',
				height : '300',
				columns : [ [
				{
					title : '名称',
					field : 'name',
					width : 280
				}, {
					title : '问题记录',
					field : 'content',
					width : 280
				} ] ]
			});
		});
	</script>
</body>
</html>
