<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>维修申请列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="../header.jsp"%>
	
	<script type="text/javascript">
	$(function() {

		$("#mt_list").datagrid({
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'maintenance/pageBind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){
				showDialogWH("查看维修申请","maintenance/maintenanceInfo.html?id="+ row.id+"&type=1",730, 550);
			},
			rowStyler: function (index, row, css) {
	        	if (row.state == "1") {  
		            return 'font-weight:bold;color:red;';  
		        }  
		    }
		});

		$("#search").bind("click", function() {
			$("#mt_list").datagrid("load", {
				code : $("#code").textbox("getValue"),
				projectname : $("#projectnames").textbox("getText"),
				units : $("#units").textbox("getValue"),
				begintime:$("#starttime").datetimebox("getValue"),
				endtime:$("#endtime").datetimebox("getValue")
			});
		});

		var p = $("#mt_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		
		$("#add").bind("click",function() {
			showDialogWH("新增维修申请", "maintenance/maintenanceInfo.html?id=0", 730, 550);
		});
		
		$("#edit").bind("click",function() {
			var row = $("#mt_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}else if(row.state !="0" && row.state !="1") {
				$.messager.alert("操作提示", "该申请已提交，不能编辑！", "error");
				return false;
			}else {
				showDialogWH("编辑维修申请","maintenance/maintenanceInfo.html?id=" + row.id,730, 550);
			}
		});

		$("#delete").bind("click", function() {
			var row = $("#mt_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			if (row.stateValue!="0") {
				$.messager.alert("操作提示", "该申请已提交，不能删除！", "error");
				return false;
			}
			$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

				if (data) {
					$.messager.progress();
					$.ajax({
						type : 'POST',
						url : "maintenance/delete.html",
						data : {
							id : row.id
						},
						success : function() {
							$.messager.progress('close');
							$("#mt_list").datagrid("unselectAll");
							$("#mt_list").datagrid("reload");
						}
					});
				}
			});
		});

	});
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
	<table id="mt_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'code',width:150">申请编号</th>
				<th data-options="field:'departmentname',width:150">部门</th>
				<th data-options="field:'applyname',width:140">申请人</th>
				<th data-options="field:'applydate',width:150">申请时间</th>
				<th data-options="field:'projectname',width:140">工程名称</th>
				<th data-options="field:'constructionunits',width:150">施工单位</th>
				<th data-options="field:'statename',width:100">状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle">
		<div class="cz_div_title">
			<a id="add" href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="false">新增</a> <a id="edit"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="false">编辑</a> <a id="delete"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="false">删除</a>
		</div>
		<div class="cz_div">
			编号:&nbsp;&nbsp;<input id="code" class="easyui-textbox" style="width: 120px" />&nbsp;&nbsp; 
			工程名称:&nbsp;&nbsp;<input id="projectnames" class="easyui-textbox" style="width: 120px" />&nbsp;&nbsp; 
			施工单位:&nbsp;&nbsp;<input id="units" class="easyui-textbox" style="width: 120px" />&nbsp;&nbsp; 
			申请时间:&nbsp;&nbsp;<input id="starttime" type="text" data-options="width:160" class="easyui-datetimebox" /> ~ 
							  <input id="endtime" type="text" class="easyui-datetimebox" data-options="width:160,validType:['compareDate[starttime]']" />&nbsp;&nbsp; 
		    <a href="javascript:void(0)" class="easyui-linkbutton"  id="search" iconCls="icon-search">查询</a>
		</div>
	</div>
  </body>
</html>
