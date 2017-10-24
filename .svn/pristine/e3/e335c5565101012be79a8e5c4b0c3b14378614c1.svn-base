<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>巡检计划</title>
<script type="text/javascript">
/**
 * 
 */
$(function(){
	$("#plan_list").datagrid({
		title:'计划列表',
		iconCls:'icon icon-plan',
		width:'auto',
		height:'auto',
		pageSize:20,
		nowrap:false,
		striped:true,
		border:true,
		collapsible:false,
		fit:true,
		url:'inspectionPlan/bind',
		remoteSort:false,
		idField:'workPlanId',
		singleSelect:true,
		pagination:true,
		rownumbers:true,
		frozenColumns:[[{field:'ck',checkbox:true}]],
		toolbar:[{
			text:'添加',
			iconCls:'icon-add',
			handler:function(){add()}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){distory()}
		},{
			text:'编辑',
			iconCls:'icon-edit',
			handler:function(){edit()}
		}]
	});
	
	var p = $("#plan_list").datagrid('getPager');
	$(p).pagination({
		pageList:[5,10,15,20],
		beforePageText:'第',
		afterPageText:'页        共{pages}页',
		displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
	})
});

function add(){
	showDialog("添加计划信息", "inspectionPlan/inspectionPlanInfo?id=0");
}

function edit(){
	var row = $("#plan_list").datagrid("getSelected");
	if(row==null){
		$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		return false;
	}
	showDialog("编辑计划信息", "inspectionPlan/inspectionPlanInfo?id="+row.workPlanId);
}

function distory(){
	var row = $("#plan_list").datagrid("getSelected");
	if(row==null){
		$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		return false;
	}
	$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
		
		if (data) {
			$.messager.progress();
			$.ajax({
				type : 'POST',
				url : "inspectionPlan/distory",
				data : {
					entityId : row.workPlanId
				},
				success : function() {
					$.messager.progress('close');
					$("#plan_list").datagrid("reload");
				}
			});
		}
	});
}
</script>
</head>
<body class="easyui-layout" id="cc">
	<table id="plan_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th data-options="field:'employeeName',width:100">员工名称</th>
				<th data-options="field:'inspectionlineName',width:200">线路名称</th>
				<th data-options="field:'startTime',width:150">起始时间</th>
				<th data-options="field:'endTime',width:150">结束时间</th>
				<th data-options="field:'cycle',width:50">周期(天)</th>
				<th data-options="field:'remark',width:250">备注</th>
			</tr>
		</thead>
	</table>
</body>
</html>