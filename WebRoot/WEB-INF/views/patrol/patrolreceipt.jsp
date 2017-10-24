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
				$("#stations").combobox("setValue","");
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
				$("#patrolreceiptlist").datagrid("unselectAll");
				$("#patrolreceiptlist").datagrid("load", {
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
		
		
		$("#patrolreceiptlist").datagrid({
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
			url : 'patrolreceipt/patrolreceiptlist.html',
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
				showDialogWH("查看巡检计划信息",
						"patrolreceipt/patrolrecordinfo.html?id=" + row.id+"&look=1", "670", "600");
				$("#patrolreceiptlist").datagrid("reload");
			} 
		});
		
		$("#stations").combobox({
			url : 'patrolreceipt/stationList.html',
			valueField : 'id',
			textField : 'text',
			editable : false
		});
		
		$("#search").bind("click", function() {
			$("#patrolreceiptlist").datagrid('unselectAll');
			var selectedTab = $('#tt').tabs('getSelected');
			var title = selectedTab.panel('options').title;
			var tabId = selectedTab.panel('options').id;
			if (tabId == "myTask") {
				$("#patrolreceiptlist").datagrid("load", {
					type : "0",
					typeDate : "0",
					//sender : $("#sender").textbox("getValue"),
					begintime:$("#starttime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue"),
					stationid:$("#stations").textbox("getValue")
				});
			} else if (tabId == "allData") {
				$("#patrolreceiptlist").datagrid("load", {
					type : "0",
					typeDate : "1",
					//sender : $("#sender").textbox("getValue"),
					begintime:$("#starttime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue"),
					stationid:$("#stations").textbox("getValue")
				});
			}

		});

		var p = $("#patrolreceiptlist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

	/* 	$("#receipt").bind("click",	function() {
			var row = $("#patrolreceiptlist").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}else if(row.stateValue =="0") {
				showDialogWH("查看巡检计划",
						"patrolreceipt/patrolreceiptinfo.html?id=" + row.id+"&detailsid="+ row.detailsid, 500, 550);
				$("#patrolreceiptlist").datagrid("reload");
			}else {
				$.messager.alert("操作提示", "该巡检计划已经被接收！", "info");
			}
		}); */
		
	/* 	$("#patrol").bind("click",function() {
			var row = $("#patrolreceiptlist").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}else {
				var dstate = row.stateValue;
				if("0"==dstate){
					$.messager.alert("操作提示", "请先接收计划！", "info");
					return false;
				}
				showDialogWH("添加巡检记录",
						"patrolreceipt/patrolrecordinfo.html?id="+row.detailsid+"&ppid="+row.id+"&sid="+row.sid,
						"800", "600");
			}					
		}); */
		
		$("#add").bind("click",function() {
			showDialogWH("添加巡检记录","patrolreceipt/patrolrecordinfo.html?id=0","670", "600");
		});
		$("#edit").bind("click",function(){
			var row = $("#patrolreceiptlist").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}else{
			showDialogWH("编辑巡检记录","patrolreceipt/patrolrecordinfo.html?id="+row.id,"670", "600");
			$("#patrolreceiptlist").datagrid("unselectAll");
		}
			});
		 $("#delete").bind("click",function(){
				var row = $("#patrolreceiptlist").datagrid("getSelected");
				if (row == null) {
					$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
					return false;
				}else{
					$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
						
						if (data) {
							$.messager.progress();
							$.ajax({
								type : 'POST',
								url : "patrolreceipt/deletepatrolrecord.html",
								data : {
									"id" : row.id
								},
								success : function(msg) {
									data = eval('(' + msg + ')');
									$.messager.progress("close");
									if (data.result) {
										$.messager.alert("操作提示", "删除成功！");
										$("#patrolreceiptlist").datagrid("unselectAll");
										$("#patrolreceiptlist").datagrid("reload");
									} else {
										$.messager.alert("操作提示", data.msg, "error");
									}
								}
							});
						}
					});
			} 
		 });
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="patrolreceiptlist" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:280" hidden="true"></th> 
			    <th data-options="field:'degree',width:100">巡检次数</th>
				<th data-options="field:'stationname',width:150">枢纽名称</th>
				<th data-options="field:'excepttime',width:150">巡检时间</th>
				<th data-options="field:'createrusername',width:140">制单人</th>
				<th data-options="field:'createtime',width:150">制单时间</th>
				<!-- <th data-options="field:'senderusername',width:140">发令人</th> -->
				<!-- <th data-options="field:'sendtime',width:150">发令时间</th>
				<th data-options="field:'receiptername',width:140">接收人</th>
				<th data-options="field:'receipttime',width:150">接收时间</th> -->
				<th data-options="field:'state',width:100">状态</th>
			<!-- 	<th data-options="field:'memo',width:100">备注</th> -->
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
		<div class="cz_div_title" id="btnList" style="">
			<!-- <a id="receipt" href="javascript:void(0)" style="" class="easyui-linkbutton"
				iconCls="icon-ok" plain="false">接收</a> <a id="patrol"
				href="javascript:void(0)" style="" class="easyui-linkbutton"
				iconCls="icon-add" plain="false">巡检记录</a> -->
			 <a id="add" href="javascript:void(0)" style="" class="easyui-linkbutton"
				iconCls="icon-add" plain="false">录入</a>
			<a id="edit" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-edit'">编辑</a>
			<a id="delete" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-clear'">删除</a>
		</div>
		<div class="cz_div">
			枢纽名称:&nbsp;&nbsp;<input id="stations" style="width:120px"/>&nbsp;&nbsp;
			<!-- 发令人:&nbsp;&nbsp;<input id="sender" class="easyui-textbox" style="width: 120px" />&nbsp;&nbsp;  -->
			制单时间区间:&nbsp;&nbsp;<input	id="starttime" type="text" data-options="width:160"	class="easyui-datetimebox" /> ~
			 <input id="endtime" type="text"	class="easyui-datetimebox"	data-options="width:160,validType:['compareDate[starttime]']" />
			
			&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				 id="search" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
