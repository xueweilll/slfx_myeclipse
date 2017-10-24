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

<title>枢纽列表</title>
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
		$("#station_list").datagrid({
			title : '枢纽列表',
			iconCls : 'icon icon-icon16',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'station/stationList.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			 /* <th data-options="field:'Dname',width:100">部门名称</th>
				<th data-options="field:'Code',width:100" hidden="true" >枢纽编号</th>
				<th data-options="field:'Name',width:150">枢纽名称</th>
				<th data-options="field:'address',width:150">详细地址</th>
				<th data-options="field:'Lat',width:100" hidden="true">坐标x轴</th>
				<th data-options="field:'Lon',width:100" hidden="true">坐标y轴</th>
				<th data-options="field:'Levels',width:50" hidden="true">顺序</th>
				<th data-options="field:'Createtime',width:130" hidden="true" >添加时间</th>
				<th data-options="field:'Edittime',width:130" hidden="true" >修改时间</th>
				<th data-options="field:'controlwaterlevel',width:130">控制水位</th> */
			columns:[[{field:'Dname',title:'部门名称',width:fixWidth(0.1)},
				         {field:'Code',title:'枢纽编号',width:fixWidth(0.1),hidden:"true"},
				         {field:'Name',title:'枢纽名称',width:fixWidth(0.1)},
				         {field:'address',title:'详细地址',width:fixWidth(0.3)},
				         {field:'Lat',title:'坐标x轴',width:fixWidth(0.1)},
				         {field:'Lon',title:'坐标y轴',width:fixWidth(0.1)},
				         {field:'Levels',title:'顺序',width:fixWidth(0.05)},
				         {field:'Createtime',title:'添加时间',width:fixWidth(0.1)},
				         {field:'Edittime',title:'修改时间',width:fixWidth(0.1)},
				         {field:'controlwaterlevel',title:'控制水位',width:fixWidth(0.05)}]],
			onLoadSuccess:function(data){
				 var rows = data.rows;
				 var merges = [];
				 var departmentid=rows[0].departmentid;
				 var k=0;
				
				 for(var i=0; i<rows.length; i++){
					if(departmentid == ""){
						departmentid=rows[i].departmentid;
						continue;
					}
				  	if(rows[i].departmentid == departmentid){
				  		k++;
				  		departmentid=rows[i].departmentid;
				  	}else {
				  		if(k > 1){
				  			console.info(i);
					  		merges.push({index: i - k,rowspan: k  });
					  		departmentid=rows[i].departmentid;
					  		k=1;
				  		}else {
				  			departmentid= rows[i].departmentid;
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
	                     field: 'Dname',
	                     rowspan: merges[i].rowspan
	                 });
	             }
			}
		});

		var p = $("#station_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		$("#search").bind("click", function() {
			$("#station_list").datagrid('unselectAll');
			var conditions = {
				name : $("#name").textbox("getValue")
			};
			$("#station_list").datagrid('load', {
				//url:'station/stationList.html?conditions='+JSON.stringify(conditions)
				conditions : JSON.stringify(conditions)
			});
			$("#station_list").datagrid("unselectAll");
		});

		$("#btnAdd").bind("click", function() {
			showDialogWH("添加枢纽信息", "station/stationInfo.html?id=0", 800, 580);
		});

		$("#btnModify").bind(
				"click",
				function() {
					var row = $("#station_list").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					}
					showDialogWH("修改枢纽信息", "station/stationInfo.html?id="
							+ row.id, 800, 580);
				});

		$("#btnDelete").bind("click", function() {
			var row = $("#station_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			$.messager.confirm("删除提示", "您确定要执行删除吗？删除枢纽将会删除该枢纽下对应的机组、闸门、摄像头信息", function(data) {
				if (data) {
					$.messager.progress();
					$.ajax({
						type : 'POST',
						url : "station/delete.html",
						data : {
							"id" : row.id
						},
						success : function(msg) {
							data = eval('(' + msg + ')');
							$.messager.progress("close");
							if (data.result) {
								$.messager.alert("操作提示", "删除成功！");
								$("#station_list").datagrid("unselectAll");
								reflush();
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
		$("#station_list").datagrid('reload');
	}
</script>
</head>

<body>
<body class="easyui-layout" id="cc">
	<table id="station_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<!-- <thead>
			<tr>
			    <th data-options="field:'Dname',width:100">部门名称</th>
				<th data-options="field:'Code',width:100" hidden="true" >枢纽编号</th>
				<th data-options="field:'Name',width:150">枢纽名称</th>
				<th data-options="field:'address',width:150">详细地址</th>
				<th data-options="field:'Lat',width:100" hidden="true">坐标x轴</th>
				<th data-options="field:'Lon',width:100" hidden="true">坐标y轴</th>
				<th data-options="field:'Levels',width:50" hidden="true">顺序</th>
				<th data-options="field:'Createtime',width:130" hidden="true" >添加时间</th>
				<th data-options="field:'Edittime',width:130" hidden="true" >修改时间</th>
				<th data-options="field:'controlwaterlevel',width:130">控制水位</th>
		
			</tr>
		</thead> -->
	</table>

	<div id="tb">
		<div class="cz_div_title"  >
			<a id="btnAdd" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-add'">新增</a>
			<a id="btnModify" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-edit'">修改</a>
			<a id="btnDelete" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-clear'">删除</a>
	<!-- 	<a
			id="btnPrint" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-print'">导出</a>  -->
		</div>
		<div class="cz_div" style="margin-bottom:0px">
		<!-- 枢纽编号:&nbsp;<input id="code" class="easyui-textbox" style="width: 120px">
		&nbsp;&nbsp;  -->枢纽名称:&nbsp;<input id="name" class="easyui-textbox"
			style="width: 120px"> &nbsp;&nbsp; <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" id="search">查询</a>
		</div>
	</div>
</body>
</html>
