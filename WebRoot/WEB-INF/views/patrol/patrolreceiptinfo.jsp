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
<title>My JSP 'patrolplaninfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="id" type="hidden" value="${patrolPlan.getId()}"/>
	<input id="detailsid" type="hidden" value="${detailsid}"/>
	<form id="patrolplaninfo" method="post"
		style="margin: 10px; text-align: center;">
		<div>
			<label for="code">巡检计划单号:</label> <input id="code" disabled="disabled"
				class="easyui-textbox" name="code" style="width: 320px;"
				data-options="required:true" value="${patrolPlan.getCode()}"/>
		</div>
		<div>
			<label for="excepttime">巡检预计时间:</label> <input id="excepttime" style="width:320px;"
				class="easyui-datetimebox" name="excepttime" disabled="disabled"
				data-options="required:true,editable:false" value="${excepttime}"/>
			</td>
		</div>
		<div>
			<label for="sender">发令人:</label> <input id="sender" disabled="disabled"
				style="width:320px;" data-options="required:true" value="${patrolPlan.getSender()}"/>
		</div>
		<div>
			<label for="memo">备注:</label> <input id="memo" class="easyui-textbox"
				name="memo" style="width:320px;height:100px;" disabled="disabled"
				data-options="multiline:true" data-options="required:true" value="${patrolPlan.getMemo()}"/>
		</div>
		</br>
		<div style="width: 445px;height: 220px;">
			<table id="stations" ></table>
		</div>
		</br>
		<div id="btn" style="text-align: center">
			<a
				id="cancle" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">关闭</a>
		</div>
	</form>

	<script type="text/javascript">
		var editIndex1 = undefined;
		var patrolPlanID = document.getElementById("id").value;
		//var executeid = $("#executeid").val() == "" ? "0" : $("#executeid").val();
		$(function() {
			$("#sender").combobox({
				url : 'document/getUsername.html',
				valueField : 'id',
				textField : 'ename',
				editable : false,
				value : $("#sender").val(),
				required : true
			});
			
			
			$('#stations').datagrid({
				url:'patrolplan/findStationBypatrolPlanID.html?patrolPlanID='+patrolPlanID,
				columns : [ [ {
					field : 'station',
					title : '枢纽名称',
					width : 150,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'post',
							url : 'patrolplan/findStations.html',
							required : true,
							editable : false
						}
					}
				}]],
				width : 'auto',
				height : 'auto',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				toolbar : [ {
					text : '计划枢纽信息',
					iconCls : 'icon-search'
				}]
			});
			
			$("#clear").bind("click", function() {
				$("#patrolplaninfo").form("clear");
			});
			$("#cancle").bind("click", function() {
				$("#dialog").dialog("close");
			});
		});

	</script>

</body>
</html>
