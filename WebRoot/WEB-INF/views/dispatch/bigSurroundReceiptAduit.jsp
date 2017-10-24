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

<title>接受单审批列表</title>
<%@include file="../header.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function() {
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				$("#endtime").datetimebox('setValue','');
				$("#begintime").datetimebox('setValue','');
				$("#receipter").textbox('setValue','');
				if (index == "1") {
					$(".cz_div_title").hide();
				} else {
					$(".cz_div_title").show();
				}
				$("#receipt_list").datagrid("unselectAll");
				$("#receipt_list").datagrid("load", {
					isAduit:"1",
					receipter:$("#receipter").textbox("getValue"),
					code:$("#code").textbox("getValue"),
					/* name:$("#name").textbox("getValue"), */
					begintime:$("#begintime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue"),
					typeDate:index
				});
			}
		});

		$("#receipt_list").datagrid({
			
			iconCls : 'icon icon-icon10',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			queryParams: {
				isAduit:"1",
				typeDate:"0"
			},
			fit : true,
			url : 'bigsurroundreceiptaduit/pageBind.html?type=' + $("#type").val(),
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){				
				showDialogWH("查看调度令信息","areareceipt/receiptInfo.html?id=" + row.id +"&state=1&type=" + $("#type").val(), 420, 480);
			}
		});

		$("#btnAudit").bind("click",function() {
			var row = $("#receipt_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("编辑调度令信息",
					"bigsurroundreceiptaduit/receiptAduitInfo.html?id=" + row.id, 420,
					530);
			$("#receipt_list").datagrid("unselectAll");
		});
		var buttons=$.extend([],$.fn.datetimebox.defaults.buttons);
		buttons.splice(2,'',{
			text:'清除',
		    handler:function(target){
		    	$("#"+target.id).datetimebox('setValue','')
		    	$(target).datebox('hidePanel'); 
		    }
		});
		$("#begintime").datetimebox({
			editable:false,
		    buttons:buttons,
		    onChange:function(){
		    	$("#endtime").datetimebox('enableValidation');
		    }
		});
		$("#endtime").datetimebox({
			editable:false,
		    buttons:buttons
		});
		/* $("#begintime").datetimebox({
			onChange : function() {
				$('#endtime').datetimebox('enableValidation');
			}
		}); */
	});
	
	function selectbig(){
		var tab = $('#tt').tabs('getSelected');
		var index = $('#tt').tabs('getTabIndex',tab);
		$("#receipt_list").datagrid("unselectAll");
		$("#receipt_list").datagrid('load',{
			isAduit:"1",
			receipter:$("#receipter").textbox("getValue"),
			code:$("#code").textbox("getValue"),
			begintime:$("#begintime").datetimebox("getValue"),
			endtime:$("#endtime").datetimebox("getValue"),
			typeDate:index
		});
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<input id="type" type="hidden" value="${type}" />
	<table id="receipt_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th hidden data-options="field:'Code',width:130">调度令编号</th>
				<th data-options="field:'DispatchType',width:150">调度令类型</th>
				<th data-options="field:'Way',width:100">调度令方式</th>
				<th data-options="field:'Launcher',width:100">调度令发起人</th>
<!-- 				<th data-options="field:'LaunchTime',width:130">发起时间</th>
				<th data-options="field:'EndTime',width:130">结束时间</th> -->
				<th data-options="field:'Receipter',width:130">调度令接收人</th>
				<th data-options="field:'ReceipteTime',width:130">调度令接收时间</th>
				<th data-options="field:'handler',width:130">签发人</th>
				<th data-options="field:'handletime',width:130">签发时间</th>
				<th data-options="field:'stateName',width:130">调度令状态</th>
				<!-- <th data-options="field:'Memo',width:200">备注</th> -->
			</tr>
		</thead>
	</table>

	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle;line-height: 30px">
		<div id="tt" class="easyui-tabs" style="">
		<div title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div title="全部数据" style="padding:20px;display:none;width: 100px;">
			</div>
			
		</div>
		<div class="cz_div_title" style="">
			<a id="btnAudit" class="easyui-linkbutton" data-options="plain:false,iconCls:'icon-edit'">签发</a>
			</div>
			<div class="cz_div">
			<div style="display:none;">
			调度令编号:&nbsp;<input id="code" class="easyui-textbox" style="width: 120px"> &nbsp;&nbsp;
			</div> 
			调度令接收人:&nbsp;<input id="receipter" class="easyui-textbox" style="width: 120px"/>&nbsp;&nbsp;
			调度令接收时间区间： &nbsp;<input id="begintime" style="width: 160px"/> ~ 
			<input id="endtime" class="easyui-datetimebox"	data-options="width:160,validType:['compareDate[begintime]']"/>&nbsp;&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectbig()" iconCls="icon-search" id="search">查询</a>
		</div>

	</div>
</body>
</html>
