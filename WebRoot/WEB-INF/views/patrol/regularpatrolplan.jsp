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
<title></title>

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
		
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				 $('#endtime').datetimebox('setValue', '');
				$('#starttime').datetimebox('setValue', ''); 
				if (index == "0") {
					//$("#receipt").show();
					//$("#patrol").show();
					$("#btnList").show();
				} else {
					//$("#receipt").hide();
					//$("#patrol").hide();
					$("#btnList").hide();
				}
				$("#patrolplanlist").datagrid("unselectAll");
				$("#patrolplanlist").datagrid("load", {
					type : "0",
					typeDate : index
				});
			}
		});
		
		var buttons=$.extend([],$.fn.datetimebox.defaults.buttons);
		buttons.splice(2,'',{
			text:'清除',
		    handler:function(target){
		    	$("#"+target.id).datetimebox('setValue','');
		    	$(target).datebox('hidePanel'); 
		    }
		});
		$("#starttime").datetimebox({
			editable:false,
			
			buttons:buttons,
			onChange:function(){
				$("#endtime").datetimebox('enableValidation')
			}
			
		});
		$("#endtime").datetimebox({
			editable:false,
		    buttons:buttons
		});
		
		
		$("#patrolplanlist").datagrid({
			/* title : '巡检接收列表', */
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'regularpatrolplan/patrolplanlist.html',
			queryParams : {
				type : "0",
				typeDate : "0"
			},
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true ,
			onDblClickRow:function(index,row){
				showDialogWH("查看签发信息",
						"regularpatrolplan/regularpatrolplaninfoheld.html?id=" + row.id+"&look=1", "430", "380");
				$("#patrolplanlist").datagrid("reload");
			} 
		});
				
		$("#searchbig").bind("click", function() {
			$("#patrolplanlist").datagrid('unselectAll');
			var selectedTab = $('#tt').tabs('getSelected');
			var title = selectedTab.panel('options').title;
			var tabId = selectedTab.panel('options').id;
			if (tabId == "allData") {
				$("#patrolplanlist").datagrid("load", {
					type : "0",
					typeDate : "1",
					//sender : $("#sender").textbox("getValue"),
					starttime:$("#starttime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue"),
				});
			} else if (tabId == "myTask") {
				$("#patrolplanlist").datagrid("load", {
					type : "0",
					typeDate : "0",
					//sender : $("#sender").textbox("getValue"),
					starttime:$("#starttime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue"),
				});
			}

		});

		var p = $("#patrolplanlist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		
		//增加
		$("#add").bind("click",function() {
			showDialogWH("添加签发信息", "regularpatrolplan/regularpatrolplaninfo.html?id=0", 450, 400);
		});
		//修改
		$("#edit").bind("click",function() {
			var row = $("#patrolplanlist").datagrid("getSelected");
			
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			/* }else if(row.stateValue!="0") {
				$.messager.alert("操作提示", "该巡检计划已提交，不能编辑！", "error");
				return false; */
			}else {
				showDialogWH("编辑签发信息","regularpatrolplan/regularpatrolplaninfo.html?id=" + row.id,450,400);
			}

		});
    
		$("#delete").bind("click", function() {
			var row = $("#patrolplanlist").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			 
			$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

				if (data) {
					$.messager.progress();
					$.ajax({
						type : 'POST',
						url : "regularpatrolplan/delete.html",
						dataType:"text",
						data : {
							"id" : row.id
						},
					
			success : function() {
						$.messager.progress('close');
						$("#patrolplanlist").datagrid("unselectAll");
						parent.addGrid($("#patrolplanlist").datagrid('reload'));
						$('#dialog').window('close');
						}
					});
				}
			});
		});

		
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="patrolplanlist" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:150,hidden:true">id</th>
				<th data-options="field:'creater',width:150,hidden: true">签发id</th>
				<th data-options="field:'code',width:150">签发单编号</th>
				<th data-options="field:'username',width:150">签发人</th>
				<th data-options="field:'createtime',width:150" >签发时间</th>
				<th data-options="field:'classes',width:140">类别</th>
				<th data-options="field:'content',width:300">签发内容</th>
				<!-- <th data-options="field:'remark',width:140">备注</th> -->
				<th data-options="field:'qf',width:100">签发状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle;">
		<div id="tt" class="easyui-tabs" style="">
			<div id="myTask" title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div id="allData" title="全部数据"
				style="padding:20px;display:none;width: 100px;"></div>
			
		</div>
		<div class="cz_div_title" id="btnList">
			<a id="add" href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="false">新增</a> <a id="edit"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="false">编辑</a> <a id="delete"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="false">删除</a>
		</div>
		<div class="cz_div">
			<!-- 签发人:&nbsp;&nbsp;<input id="sender" class="easyui-textbox"
				style="width: 120px" /> -->
             &nbsp;&nbsp; 签发时间:&nbsp;&nbsp;<input
				id="starttime" type="text" data-options="width:160"
				class="easyui-datetimebox" /> ~ <input id="endtime" type="text"
				class="easyui-datetimebox"
				data-options="width:160,validType:['compareDate[starttime]']" />
             &nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="searchbig()" id="searchbig" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
