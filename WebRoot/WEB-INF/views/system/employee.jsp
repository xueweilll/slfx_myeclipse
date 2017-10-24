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
<%@include file="../header.jsp"%>
<title>My JSP 'employee.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(function() {
		var data = {
			"id" : ($("#empId").val() == "" ? 0 : $("#empId").val()),
			"department" : {
				"id" : null
			},
			"name" : null
		};
		$("#employee_list").datagrid({
			title : '人员列表',
			iconCls : 'icon icon-icon5',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'employee/employeeListBind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			columns:[[{field:'dname',title:'部门名称',width:fixWidth(0.15)},
				       /*   {field:'sid',title:'枢纽',width:fixWidth(0.1)}, */
			         {field:'name',title:'人员名称',width:fixWidth(0.15)},
			         {field:'birthday',title:'生日',width:fixWidth(0.1),hidden:true},
			         {field:'position',title:'职务',width:fixWidth(0.1)},
			         {field:'sex',title:'性别',width:fixWidth(0.06)},
			         {field:'age',title:'年龄',width:fixWidth(0.05),hidden:true},
			         {field:'telephone',title:'电话',width:fixWidth(0.12)},
			         {field:'phone',title:'手机',width:fixWidth(0.12)},
			         {field:'createtime',title:'创建时间',width:fixWidth(0.15)},
			         {field:'edittime',title:'修改时间',width:fixWidth(0.15)}]],
	         onLoadSuccess:function(data){
				 var rows = data.rows;
				 var merges = [];
				 var dname=rows[0].dname;
				 var k=0;
				 for(var i=0; i<rows.length; i++){
					if(dname == ""){
						dname=rows[i].dname;
						continue;
					}
				  	if(rows[i].dname == dname){
				  		k++;
				  		dname=rows[i].dname;
				  	}else {
				  		if(k > 1){
				  			console.info(i);
					  		merges.push({index: i - k,rowspan: k  });
					  		dname=rows[i].dname;
					  		k=1;
				  		}else {					  			
				  			dname= rows[i].dname;
				  		}
				  	}
				  	if(i == rows.length -1){
				  		if(k > 1){
				  			merges.push({index: i - k + 1,rowspan: k  });
				  		}
				  	}
				 }
				 for(var i=0; i<merges.length; i++){
	                 $(this).datagrid('mergeCells',{
	                     index: merges[i].index,
	                     field: 'dname',
	                     rowspan: merges[i].rowspan
	                 });
	             }
			}
		});
		$("#orgId").combotree({
			url : 'department/departmentsBind.html'
			
				
			
		});
		$("#search").bind("click", function() {
			$("#employee_list").datagrid('unselectAll');
			var data = {
				"id" : ($("#empId").val() == "" ? 0 : $("#empId").val()),
				"department" : {
					"id" : $("#orgId").textbox("getValue")
				},
				"name" : $("#Name").textbox("getValue")
			};
			$("#employee_list").datagrid(
				"load", {
					jsonStr : JSON.stringify(data)
				}
			);
			$.messager.progress("close");
		});
		var p = $("#employee_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#btnAdd").bind("click", function() {
			showDialogWH("添加人员信息", "employee/employeeInfo.html?id=0",510,500);
		});
		$("#btnEdit").bind("click", function() {
			var row = $("#employee_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("编辑人员信息", "employee/employeeInfo.html?id=" + row.id,510,500);
		});
		$("#btnDelete").bind("click", function() {
			var row = $("#employee_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
				if (data) {
					$.messager.progress();
					$.ajax({
						type : 'POST',
						url : "employee/delete.html",
						data : {
							"id" : row.id
						},
						success : function() {
							$.messager.progress('close');
							$("#employee_list").datagrid("unselectAll");
							$("#employee_list").datagrid("reload");
						}
					});
				}
			})
		})
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="employee_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<!-- <th data-options="field:'dname',width:120">部门名称</th>
				<th data-options="field:'sid',width:110">枢纽</th>
				<th data-options="field:'name',width:100">人员名称</th>
				<th data-options="field:'birthday',width:100">生日</th>
				<th data-options="field:'position',width:100">职务</th>
				<th data-options="field:'sex',width:50">性别</th>
				<th data-options="field:'age',width:50">年龄</th>
				<th data-options="field:'telephone',width:120">电话</th>
				<th data-options="field:'phone',width:120">手机</th>
				<th data-options="field:'createtime',width:130">添加时间</th>
				<th data-options="field:'edittime',width:130">修改时间</th> -->
			</tr>
		</thead>
	</table>
	<div id="tb" 
		>
	 <div class="cz_div_title" >	
		<a id="btnAdd" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-add'">新增</a> <a id="btnEdit"
			class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-edit'">修改</a> <a
			id="btnDelete" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-clear'">删除</a>
	 </div>	
	 <div class="cz_div" >			
			<form id="department" method="post">
			部门名称:<input id="orgId" class="easyui-combobox" style="width: 150px"
				value="${employee.getDepartment().getId()}" editable="false"/> &nbsp;&nbsp; 人员名称:<input
				id="Name" class="easyui-textbox" style="width: 110px">
			&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" id="search">查询</a>
		</form>
 
    </div>
    
	

		</div>

</body>
</html>
