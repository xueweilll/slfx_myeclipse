<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<%@include file="../header.jsp"%>
<title>My JSP 'dispatchinfo.jsp' starting page</title>

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
<div>
		<p>
			<img alt="" src="images/zlogo.png" style="vertical-align: middle;"> <span align="center"
				style="font-size: 25;vertical-align: middle;">执&nbsp;&nbsp;行&nbsp;&nbsp;单</span>
		</p>
	</div>
	<input id="id" type="hidden" value="${dispatch.getId()}" />
	<input id="state" type="hidden" value="${state}" />
	<input id="departmentid" type="hidden" value="${departmentid}" />
	<form id="ffemp" method="post"
		style="margin-top: 1px;padding:10px;">
		<table cellpadding="0" cellspacing="1" border="0">
			<tr>
				<td><label>执行单编号:</label></td>
				<td><input id="code" class="easyui-textbox"
					data-options="disabled:true" value="${dispatch.getCode()}"></td>
			</tr>
			<tr>
				<td><label>执行时间:</label></td>
				<td><input id="PromoteTime" class="easyui-textbox"
					data-options="disabled:true" value="${promotetime}"></td>
			</tr>
			<tr>
				<td><label>执行发起人:</label></td>
				<td><input id="Promoter" class="easyui-textbox"
					data-options="disabled:true" value="${promoter}" /></td>
			</tr>
			<tr>
				<td><label>执行备注：</label>
				<td><input id="MEMO" class="easyui-textbox"
					data-options="disabled:true" value="${dispatch.getMemo()}"></td>
			</tr>
		</table></br>
		<!-- <div>
			<table id="instruction_list" cellspacing="0" cellpadding="0" >
				<thead>
					<tr>
						<th data-options="field:'instruction',width:70">闸门操作</th>
						<th data-options="field:'gateOperateTime',width:135">闸门操作时间</th>
						<th data-options="field:'uinstruction',width:70">机组操作</th>
						<th data-options="field:'unitOperateTime',width:132">机组操作时间</th>
					</tr>
				</thead>
			</table>
		</div> -->
		</br>
		<div>
			<table id="station_list" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th data-options="field:'sid',width:100,height:50">枢纽</th>
						<th data-options="field:'controlwater',width:100,height:50">控制水位</th>
						<th data-options="field:'truewater',width:100,height:50">实际水位</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="btn" style="text-align: center; margin-top:10px;">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">下发</a>
		</div>
		
	</form>
	<script type="text/javascript">
		$(function() {
			
			if ($("#state").val() == "1") {
				//document.getElementById("sub").style.display = "none";
				$("#sub").hide();
			}

			$("#sub").bind("click", function() {
				$.messager.confirm('确认对话框', '您确定要下发吗？', function(r){
					if (r){
						$.ajax({
							type : "POST",
							url : "dispatchIssuedList/send.html",
							dataType : "text",
							data : {
								"id" : $("#id").val(),
								"departmentid":$("#departmentid").val()
							},
							success : function() {								
								reflush();
								$('#dialog').window('close');
							}
						});
					}
				});
			});
			/* $("#instruction_list").datagrid({
				title : '调度指令',
				url : 'dispatchIssuedList/ddzl.html',
				queryParams : {
					id : $("#id").val()
				},
				border : true,
				singleSelect : true,
				rownumbers : true,				
				width : '450px',
				height : '200px',
				idField : 'id',
				nowrap : false,
				striped : true,
				rownumbers : true,
				checkOnSelect : false,
				collapsible : false,
				
			}); */
			$("#station_list").datagrid({
				/* url : 'dispatchIssuedList/ddmx.html', */
				url:'dispatchinstructions/findStationsByid.html',
				queryParams : {
					id : $("#id").val(),
					departmentid:$("#departmentid").val(),
					pd:"self"
				},
				title : '执行明细',
				rownumbers : true,
				width : 'auto',
				height : '200px',
				closed : false,
				singleSelect : true,
				striped : true,
				collapsible : false,
				checkOnSelect : false,
				modal : true
			});
		});
		function reflush() {
			document.getElementById('dispatchIssuedList.htmlifm').contentWindow
					.$('#selfdispatch').datagrid('reload');
		}
	</script>
</body>
</html>
