<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function() {
		$("#types").combobox({
			url:'paramers/MaterialTypeAll.html',
			valueField : 'id',
			textField : 'text',
			editable : false
		});
		
		$("#inStorage_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'storageTable/storageTable.html',
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			columns:[[
			          {field:'mtype',title:'物资类型',width:120},
			          {field:'mname',title:'物品名称',width:120},    
			          {field:'sname',title:'规格',width:140},    
			          {field:'pname',title:'计量单位',width:140},    
			          {field:'instorage',title:'入库数量',width:140},    
			          {field:'outstorage',title:'出库数量',width:140},     
			          {field:'checkoutstorage',title:'外借数量',width:200},
			          {field:'breturn',title:'外借归还',width:120},
			          {field:'scrapstorage',title:'报废数量',width:200},
			          {field:'storage',title:'库存数量',width:200}     
			      ]]
		}); 
		
		var p = $("#inStorage_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		
	});
	function selectbig(){
		//alert($("#name").textbox("getValue"));
		$("#inStorage_list").datagrid('load',{
			type:$("#types").combobox("getValue"),
			name:$("#name").textbox("getValue")
		});
	}
	function searchInfo(){
		var row = $("#inStorage_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWH("查看入库明细", "inStorage/inStorageInfo.html?id=" + row.id+"&type=0",700,550);
	}
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="inStorage_list" cellspacing="0" cellpadding="0"></table>
	<div id="tb" style="padding:5px;height:auto">
		<div class="cz_div">
			物资类型:&nbsp;<input id="types" class="easyui-combobox"	style="width: 120px"/>&nbsp;&nbsp;	
			物品名称:&nbsp;<input id="name" class="easyui-textbox"  style="width: 120px"/>	&nbsp;&nbsp;		
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectbig()" iconCls="icon-search" id="search">查询</a>
		</div>
	</div>
</body>
</html>
