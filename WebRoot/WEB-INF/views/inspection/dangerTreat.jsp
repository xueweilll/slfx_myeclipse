<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>隐患处理</title>
<script type="text/javascript">
/**
 * 
 */
$(function(){
	$("#dangerTreat_list").datagrid({
		title:'隐患列表',
		iconCls:'icon icon-dangerTreat',
		width:'auto',
		height:'auto',
		pageSize:20,
		nowrap:false,
		striped:true,
		border:true,
		collapsible:false,
		fit:true,
		url:'dangerTreat/bind',
		remoteSort:false,
		idField:'dtId',
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
	
	var p = $("#dangerTreat_list").datagrid('getPager');
	$(p).pagination({
		pageList:[5,10,15,20],
		beforePageText:'第',
		afterPageText:'页        共{pages}页',
		displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
	})
});

function add(){
	showDialog("添加模板信息", "dangerTreat/dangerTreatInfo?id=0");
}

function edit(){
	var row = $("#dangerTreat_list").datagrid("getSelected");
	if(row==null){
		$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		return false;
	}
	showDialog("编辑模板信息", "dangerTreat/dangerTreatInfo?id="+row.dtId);
}

function distory(){
	var row = $("#dangerTreat_list").datagrid("getSelected");
	if(row==null){
		$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		return false;
	}
	$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
		
		if (data) {
			$.messager.progress();
			$.ajax({
				type : 'POST',
				url : "dangerTreat/distory",
				data : {
					entityId : row.dtId
				},
				success : function() {
					$.messager.progress('close');
					$("#dangerTreat_list").datagrid("reload");
				}
			});
		}
	});
}
</script>
</head>
<body>
<table id="dangerTreat_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th data-options="field:'dangerTit',width:200">隐患名称</th>
				<th data-options="field:'empName',width:150">处理人</th>
				<th data-options="field:'startTime',width:150">起始时间</th>
				<th data-options="field:'endTime',width:150">结束时间</th>
				<th data-options="field:'remark',width:250">备注</th>
			</tr>
		</thead>
	</table>
</body>
</html>