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
<title>My JSP 'sdexecute.jsp' starting page</title>

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
				$('#r_code').textbox('setValue', '');
				$('#rd_code').textbox('setValue', '');
				$("#starttime").datetimebox("setValue",'');
				$("#endtimes").datetimebox("setValue",'');
				if (index == "0") {
					$("#look").show();
					$("#execute").show();
					$("#complete").show();
					$("#btnList").show();
				} else {
					$("#look").hide();
					$("#execute").hide();
					$("#complete").hide();
					$("#btnList").hide();
				}
				$("#rdexelist").datagrid("unselectAll");
				$("#rdexelist").datagrid("load", {
					type : "0",
					typeDate : index
				});
			}
		});

		$("#rdexelist").datagrid(
				{

					iconCls : 'icon icon-icon34',
					width : 'auto',
					height : 'auto',
					pageSize : 20,
					nowrap : false,
					striped : true,
					border : true,
					collapsible : false,
					fit : true,
					url : 'rdexecute/rdexelist.html',
					queryParams : {
						type : "0",
						typeDate : "0"
					},
					remoteSort : false,
					idField : 'id',
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					onDblClickRow : function(index, row) {
						if (row.state == "完成" || row.state == "已实施未完成") {
							showDialogWH("查看具体实施单信息",
									"rdexecute/viewrdexecute.html?rdid="
											+ row.id + "&sid=" + row.stationid
											+ "&rdstationid=" + row.rdsid
											+ "&edit=" + 2, 580, 600);
						} else {
							$.messager
									.alert("操作提示", "该指令未实施，无法查看实施信息！", "info");
							return false;
						}
					}
				});

		$("#search").bind("click", function() {
			var selectedTab = $('#tt').tabs('getSelected');
			var title = selectedTab.panel('options').title;
			var tabId = selectedTab.panel('options').id;
		
			if (tabId == "allData") {
				$("#rdexelist").datagrid("load", {
					type : "0",
					typeDate : "1",
					r_code : $("#r_code").textbox("getValue"),
					rd_code : $("#rd_code").textbox("getValue"),
					starttime: $("#starttime").datetimebox("getValue"),
					endtime: $("#endtimes").datetimebox("getValue")
				});
			} else if (tabId == "myTask") {
				$("#rdexelist").datagrid("load", {
					type : "0",
					typeDate : "0",
					r_code : $("#r_code").textbox("getValue"),
					rd_code : $("#rd_code").textbox("getValue"),
					starttime: $("#starttime").datetimebox("getValue"),
					endtime: $("#endtimes").datetimebox("getValue")
				});
			}
			$("#rdexelist").datagrid("unselectAll");
		});

		var p = $("#rdexelist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		$("#look").bind(
				"click",
				function() {
					var row = $("#rdexelist").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					}
					if(row.state == "未查看"){
						$.ajax({
							type : "POST",
							url : "rdexecute/look.html",
							dataType : "text",
							data : {
								"rdsid" : row.rdsid
							},
							success : function() {
								$("#rdexelist").datagrid("reload");
							}
						});
					}
					showDialogWH("查看执行单信息",
							"receiptDispatch/receiptDispatchInfo.html?id="
									+ row.id + "&isDisplay=1"+"&departmentid="+row.departmentid, 740, 600);
				});

		$("#execute").bind(
				"click",
				function() {
					var row = $("#rdexelist").datagrid("getSelected");
				
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					} else if (row.state == "未查看") {
						alert("请先查看分解单！");
						return;
					} else if (row.state == "完成") {
						alert("已完成");
						return;
					} else if (row.state == "已查看未实施") {
						showDialogWH("添加具体实施单",
								"rdexecute/addrdexecute.html?rdid=" + row.id
										+ "&sid=" + row.stationid, 580, 600);
					} else if (row.state == "已实施未完成") {
						//alert(row.rdsid); //对应dis_rd_execute表的rdstationid字段
						showDialogWH("编辑具体实施单",
								"rdexecute/addrdexecute.html?rdid=" + row.id
										+ "&sid=" + row.stationid
										+ "&rdstationid=" + row.rdsid
										+ "&edit=" + 2, 580, 600);
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
				$("#endtimes").datetimebox('enableValidation')
			}
			
		});
		$("#endtimes").datetimebox({
			editable:false,
		    buttons:buttons
		});
		$("#complete").bind("click", function() {
			var row = $("#rdexelist").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			} else if (row.state == "未查看") {
				$.messager.alert("操作提示", "该分解单未查看，不能进行此操作！", "error");
				return false;
			} else if (row.state == "已查看未实施") {
				$.messager.alert("操作提示", "该指令未实施，不能进行此操作！", "error");
				return false;
			}
			$.ajax({
				type : "POST",
				url : "rdexecute/complete.html",
				dataType : "text",
				data : {
					"rdsid" : row.rdsid,
					"rdid" : row.id,
					"rid" : row.receiptid
				},
				success : function() {
					$.messager.alert("操作提示", "操作成功！！！");
					$("#rdexelist").datagrid("reload");
				}
			});
		});

	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="rdexelist" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<!-- <th data-options="field:'id',width:200">id</th> -->
				<!-- 	<th hidden data-options="field:'stationid',width:80">枢纽信息ID</th> -->
				<th hidden data-options="field:'rdcode',width:130">分解单编号</th>
				<th  hidden data-options="field:'receiptcode',width:130">调度令编号</th>
				<th data-options="field:'stationname',width:140">枢纽名称</th>
				<th data-options="field:'launcher',width:100">调度令发起人</th>
				<th data-options="field:'launchTime',width:150"hidden="true" >调度令发起时间</th>
				<th data-options="field:'endtime',width:150" hidden="true">调度令结束时间</th>
				<!-- <th data-options="field:'instruction',width:100">指令内容</th> -->
				<th data-options="field:'receiptetime',width:150">调度令接收时间</th>
				<th data-options="field:'creater',width:100">分解单制单人</th>
				<th data-options="field:'createtime',width:150">分解单制单时间</th>
				<th data-options="field:'state',width:100">处理状态</th>
				<th data-options="field:'departmentid',hidden:true"></th> 
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
			<a id="look" class="easyui-linkbutton" style=""
				data-options="plain:false,iconCls:'icon-search'">查看</a> <a
				id="execute" class="easyui-linkbutton" style=""
				data-options="plain:false,iconCls:'icon-add'">实施</a>
		</div>
		<div class="cz_div">
		<div style="display:none;">
			分解单编号:&nbsp;<input id="rd_code" class="easyui-textbox"
				style="width: 120px">&nbsp;&nbsp; 调度令编号:&nbsp;<input
				id="r_code" class="easyui-textbox" style="width: 120px">
			&nbsp;&nbsp;
			</div>
			<div>
			分解单制单时间: <input id="starttime" type="text" class="easyui-datetimebox" data-options="width:160">
			 ~<input id="endtimes" type="text" class="easyui-datetimebox"
				data-options="width:160,validType:'compareDate[starttime]'">
			
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				id="search" iconCls="icon-search">查询</a>
				</div>
			<!-- <a
				id="complete" class="easyui-linkbutton" style="display: none;"
				data-options="plain:false,iconCls:'icon-clear'">完成</a> -->
		</div>
	</div>
</body>
</html>
