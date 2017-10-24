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
<title>执行人员具体实施</title>

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
				$("#starttime").datetimebox('setValue','');
				$("#endtimes").datetimebox('setValue','');
				if (index == "1") {					
					$("#btnList").hide();
				} else {
					$("#btnList").show();
				}
				$("#exelist").datagrid("unselectAll");
				$("#exelist").datagrid("load", {
					type : "0",
					typeDate : index
				});
			}
		});
		$("#exelist").datagrid({

			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			/* url : 'sdexecute/exelist.html', */
			url : 'sdexecute/executeList.html',
			queryParams : {
				typeDate : "0"
			},
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){
				if (row.state == "完成" || row.state == "已实施未完成") {
					showDialogWH("查看具体实施单信息", "sdexecute/viewexecute.html?stationid="
							+ row.stationid + "&edit=" + 2, 500, 400);
				}else {
					$.messager.alert("操作提示", "该指令未实施，无法查看实施信息！", "info");
					return false;
				}
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
		var p = $("#exelist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	function searchdbw(){
		$("#exelist").datagrid('unselectAll');
		var tab = $('#tt').tabs('getSelected');
		var index = $('#tt').tabs('getTabIndex',tab);
		var cod=$("#codes").textbox("getValue");
		$("#exelist").datagrid("unselectAll");
		$("#exelist").datagrid('load',{
			code: cod,
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtimes").datetimebox("getValue"),
			typeDate:index
		});
	}
	
	function rowformater2(value, row, index) {
		var href = "";
		if (row.state == "未查看") {
			//href = "<a href='sdexecute/addsdexecute.html?state=" + row.state + "' target='_blank'>查看</a>";
			href = '<a href="javascript:chakan()">查看</a>';
		} else if (row.state == "已查看未实施") {
			//新增  （点击【保存】，状态更新为2 执行中）
			href = '<a href="javascript:addsdexecute2()">执行</a>';
		} else if (row.state == "已实施未完成") {
			//编辑 
			href = '<a href="javascript:addsdexecute2()">完成（不可再编辑）</a>';
		} else if (row.state == "完成") {
			href = '<a href="javascript:void(0)">已完成</a>';
		}
		return href;
	}

	function chakan() {
		var row = $("#exelist").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else {
			showDialogWH("查看执行单信息",
					"sdexecute/sdInstructionInfo.html?stationid="
							+ row.stationid, 480, 400);
			//showDialogWH("查看执行单信息", "dispatchIssuedList/dispatchIssuedInfo.html?id="+row.id+"&state=1",500,600);
			$("#exelist").datagrid('reload');
		}
	}

	function addsdexecute2() {
		var row = $("#exelist").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else if (row.state == "未查看") {
			alert("请先查看执行单！");
			return;
		} else if (row.state == "完成") {
			alert("已完成");
			return;
		} else if (row.state == "已查看未实施") {
			showDialogWH("添加调度执行", "sdexecute/doexecute.html?stationid="
					+ row.stationid,500, 400);
		} else if (row.state == "已实施未完成") {
			showDialogWH("编辑调度执行", "sdexecute/doexecute.html?stationid="
					+ row.stationid + "&edit=" + 2, 500, 400);
		}
	}

	function finish() {
		var row = $("#exelist").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else if (row.state == "未查看") {
			$.messager.alert("操作提示", "该指令未查看，不能进行此操作！", "error");
			return false;
		} else if (row.state == "已查看未实施") {
			$.messager.alert("操作提示", "该指令未实施，不能进行此操作！", "error");
			return false;
		} else {
			//return "sdexecute/finish.html?stationid=" + row.stationid
			$.ajax({
				type : "POST",
				url : "sdexecute/finish2.html",
				dataType : "text",
				data : {
					"id" : row.id,
					"stationid" : row.stationid
				},
				success : function(msg) {
					$.messager.progress('close');
					$("#exelist").datagrid("reload");
					$('#dialog').window('close');
				}
			});
		}
	}
	function search() {
		var row = $("#exelist").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else {
			showDialog("查看指令信息", "sdexecute/searchInfo.html?stationid="
					+ row.stationid);
		}
	}
	function reflush() {
		var tab = $('#tt').tabs('getSelected');
		var index = $('#tt').tabs('getTabIndex', tab);
		$("#exelist").datagrid('load', {
			typeDate : index
		});
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="exelist" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th hidden data-options="field:'stationid',width:80">枢纽信息ID</th>
				<th hidden data-options="field:'code',width:130">具体实施单编号</th>
				<th data-options="field:'sid',width:140">枢纽名称</th>
				<th data-options="field:'promoter',width:100">具体实施单发起人</th>
				<th data-options="field:'promotetime',width:150">具体实施单发起时间</th>
				<!-- <th data-options="field:'instruction',width:100">指令内容</th> -->
				<th data-options="field:'creater',width:100">制单人</th>
				<th data-options="field:'createtime',width:150">制单时间</th>
				<th data-options="field:'state',width:100">处理状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style=" text-align: left; vertical-align: middle;">
		<div id="tt" class="easyui-tabs" style="">
			<div title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div title="全部数据" style="padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div class="cz_div_title" id="btnList" style="">
			<a id="look" href="javascript:void(0)" class="easyui-linkbutton"
				style="" onclick="chakan()" iconCls="icon-search"
				plain="false">查看</a> <a id="execute" href="javascript:void(0)"
				class="easyui-linkbutton" style=""
				onclick="addsdexecute2()" iconCls="icon-add" plain="false">实施</a>
		</div>
		<div class="cz_div">
			<!-- <a
				id="complete" href="javascript:void(0)" class="easyui-linkbutton" style="display: none;"
				onclick="finish()" iconCls="icon-add" plain="false">完成</a>  -->
			<!-- 具体实施单编号:&nbsp;<input id="rd_code" class="easyui-textbox"
				style="width: 120px" /> &nbsp;&nbsp; --> <!-- <a id="reflush"
				href="javascript:void(0)" class="easyui-linkbutton"
				style="" onclick="reflush()" iconCls="icon-search"
				plain="false">查询</a> -->
				<div style="display:none;">
				具体实施单编号:<input id="codes" class="easyui-textbox" name="type"   
    				data-options="valueField:'id',width:'160'"/>
    				</div>
        		具体实施单发起时间: <input id="starttime" type="text" class="easyui-datetimebox" data-options="width:160">
			 ~<input id="endtimes" type="text" class="easyui-datetimebox"
				data-options="width:160,validType:'compareDate[starttime]'">
				
		      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchdbw()">查询</a>
		</div>
	</div>
</body>
</html>
