<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/**
 * 
 */
$(function(){
	$("#scheduling_list").datagrid({
		title:'排班列表',
		iconCls:'icon icon-scheduling',
		width:'auto',
		height:'auto',
		//pageSize:20,
		nowrap:false,
		striped:true,
		border:true,
		collapsible:false,
		fit:true,
		url:'scheduling/bind',
		remoteSort:false,
		idField:'id',
		singleSelect:true,
		//pagination:true,
		rownumbers:true
	});
	
	/*var p = $("#scheduling_list").datagrid('getPager');
	$(p).pagination({
		pageList:[5,10,15,20],
		beforePageText:'第',
		afterPageText:'页        共{pages}页',
		displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
	})*/
});
</script>
</head>
<body class="easyui-layout" id="cc">
	<table id="scheduling_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th data-options="field:'empName',width:100">员工名称</th>
				<%-- ${thhtml } --%>
				<th data-options="field:'a',width:150">星期一</th>
				<th data-options="field:'b',width:150">星期二</th>
				<th data-options="field:'c',width:150">星期三</th>
				<th data-options="field:'d',width:150">星期四</th>
				<th data-options="field:'e',width:150">星期五</th>
				<th data-options="field:'f',width:150">星期六</th>
				<th data-options="field:'g',width:150">星期日</th>
			</tr>
		</thead>
	</table>
</body>
</html>