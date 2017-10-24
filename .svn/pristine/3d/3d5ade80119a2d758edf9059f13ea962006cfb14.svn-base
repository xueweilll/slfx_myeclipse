<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>巡检模板</title>
<script type="text/javascript">
/**
 * 
 */
$(function(){
	$("#temp_list").datagrid({
		title:'模板列表',
		iconCls:'icon icon-temp',
		width:'auto',
		height:'auto',
		pageSize:20,
		nowrap:false,
		striped:true,
		border:true,
		collapsible:false,
		fit:true,
		url:'inspectionTemp/bind',
		remoteSort:false,
		idField:'inspectionLineId',
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
		},{
			text:'点集',
			iconCls:'icon icon-station',
			handler:function(){station()}
		}]
	});
	
	var p = $("#temp_list").datagrid('getPager');
	$(p).pagination({
		pageList:[5,10,15,20],
		beforePageText:'第',
		afterPageText:'页        共{pages}页',
		displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
	})
});

function station(){
	var row = $("#temp_list").datagrid("getSelected");
	//alert(row.termarkId+"|"+row.markTit);
	if(row==null){
		$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		return false;
	}
	//window.open('inspectionTemp/station?id='+row.inspectionLineId,'_self');
	showDialogWHH("模板点集信息", "inspectionTemp/station?id="+row.inspectionLineId,1000,600);
}

function add(){
	showDialog("添加模板信息", "inspectionTemp/inspectionlineInfo?id=0");
}

function edit(){
	var row = $("#temp_list").datagrid("getSelected");
	if(row==null){
		$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		return false;
	}
	showDialog("编辑模板信息", "inspectionTemp/inspectionlineInfo?id="+row.inspectionLineId);
}

function distory(){
	var row = $("#temp_list").datagrid("getSelected");
	if(row==null){
		$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		return false;
	}
	$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
		
		if (data) {
			$.messager.progress();
			$.ajax({
				type : 'POST',
				url : "inspectionTemp/distory",
				data : {
					entityId : row.inspectionLineId
				},
				success : function() {
					$.messager.progress('close');
					$("#temp_list").datagrid("reload");
				}
			});
		}
	});
}
</script>
</head>
<body class="easyui-layout" id="cc">
	<table id="temp_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th data-options="field:'inspectionLineName',width:200">线路名称</th>
				<th data-options="field:'description',width:350">线路描述</th>
				<th data-options="field:'createTime',width:150">创建时间</th>
				<th data-options="field:'editTime',width:150">修改时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>