<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>闸门列表</title>
	<%@include file="../header.jsp"%>
    
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
		$("#gate_list").datagrid({
			title : '闸门列表',
			iconCls : 'icon icon-icon10',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'gate/gateList.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			/* <th data-options="field:'Sname',width:120">枢纽名称</th>
			<th data-options="field:'Code',width:100" hidden="true">闸门编号</th>
			<th data-options="field:'Name',width:150">闸门名称</th>
			<th data-options="field:'OnOffWay',width:100">启闭方式</th>
			<th data-options="field:'Createtime',width:130" hidden="true">添加时间</th>
			<th data-options="field:'Edittime',width:130" hidden="true">修改时间</th> */
			columns:[[{field:'Sname',title:'枢纽名称',width:fixWidth(0.15)},
				         {field:'Code',title:'闸门编号',width:fixWidth(0.2),hidden:true},
				         {field:'Name',title:'闸门名称',width:fixWidth(0.2)},
				         {field:'OnOffWay',title:'启闭方式',width:fixWidth(0.15)},
				         {field:'Createtime',title:'添加时间',width:fixWidth(0.15)},
				         {field:'Edittime',title:'修改时间',width:fixWidth(0.15)}]],
			onLoadSuccess:function(data){
				 var rows = data.rows;
				 var merges = [];
				 var station=rows[0].Sname;
				 var k=0;
				
				 for(var i=0; i<rows.length; i++){
					if(station == ""){
						station=rows[i].Sname;
						continue;
					}
				  	if(rows[i].Sname == station){
				  		k++;
				  		station=rows[i].Sname;
				  	}else {
				  		if(k > 1){
				  			console.info(i);
					  		merges.push({index: i - k,rowspan: k  });
					  		station=rows[i].Sname;
					  		k=1;
				  		}else {					  			
				  			station= rows[i].Sname;
				  		}
				  	}
				  	if(i == rows.length -1){
				  		if(k > 1){
				  			merges.push({index: i - k + 1,rowspan: k  });
				  		}
				  	}
				 }
				 console.info(merges);
				 for(var i=0; i<merges.length; i++){
	                 $(this).datagrid('mergeCells',{
	                     index: merges[i].index,
	                     field: 'Sname',
	                     rowspan: merges[i].rowspan
	                 });
	             }
			}
		});

		var p = $("#gate_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		
		$("#search").bind("click",function(){
			$("#gate_list").datagrid('unselectAll');
			var conditions={
				
				name:$("#name").textbox("getValue")
			};
			
			$("#gate_list").datagrid('load',{
				//url:'gate/gateList.html?conditions='+JSON.stringify(conditions)
				conditions:JSON.stringify(conditions)
			});
		});
		
		$("#btnAdd").bind("click",function(){
			showDialogWH("添加闸门信息", "gate/gateInfo.html?id=0",400,280);
		});
		
		$("#btnModify").bind("click",function(){
			var row = $("#gate_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("修改闸门信息", "gate/gateInfo.html?id=" + row.id,400,280);
		});
		
		$("#btnDelete").bind("click",function(){
			var row = $("#gate_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "gate/delete.html",
					data : {
						"id" : row.id
					},
					success : function(msg) {
							data = eval('(' + msg + ')');
							$.messager.progress("close");
							if (data.result) {
								$.messager.alert("操作提示", "删除成功！");
								reflush();
								$("#gate_list").datagrid("unselectAll");
							} else {
								$.messager.alert("操作提示", data.msg, "error");
							}
					}
				});
			}
		});
		});
	});

	function reflush() {
		$("#gate_list").datagrid('reload');
	}
	
	
</script>
</head>


<body class="easyui-layout" id="cc">
	<table id="gate_list" cellspacing="0" cellpadding="0" data-options="toolbar:'#tb'">
		<!-- <thead>
			<tr>
				<th data-options="field:'Sname',width:120">枢纽名称</th>
				<th data-options="field:'Code',width:100" hidden="true">闸门编号</th>
				<th data-options="field:'Name',width:150">闸门名称</th>
				<th data-options="field:'OnOffWay',width:100">启闭方式</th>
				<th data-options="field:'Createtime',width:130" hidden="true">添加时间</th>
				<th data-options="field:'Edittime',width:130" hidden="true">修改时间</th>
		
			</tr>
		</thead> -->
	</table>
	
	<div id="tb">
	     <div class="cz_div_title">
			<a id="btnAdd" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-add'">新增</a>
			<a id="btnModify" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-edit'">修改</a>
			<a id="btnDelete" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-clear'">删除</a>
			<!-- <a id="btnPrint" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-print'">导出</a> -->
		</div>
		<div class="cz_div">
			<!-- 闸门编号:&nbsp;<input id="code" class="easyui-textbox" style="width: 120px">
			&nbsp;&nbsp;  -->闸门名称:&nbsp;<input id="name" class="easyui-textbox" style="width: 120px">
			
			&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" id="search">查询</a>
				
		</div>	
				</div>
</body>
</html>
