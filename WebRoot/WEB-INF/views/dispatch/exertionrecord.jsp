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
<title>My JSP 'selfdispatch.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
	$(function() {
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2, '', {
			text : '清空',
			handler : function(target) {
				$(target).datetimebox('setValue', '');
				$(target).datetimebox('hidePanel');
			}
		});
		$('#launchBegintime').datetimebox({
			editable : false,
			width : 160,
			buttons : buttons,
			onChange : function() {
				$("#launchEndtime").datetimebox('enableValidation');
			}
		});
		$('#launchEndtime').datetimebox({
			editable : false,
			width : 160,
			buttons : buttons,
			validType : [ 'compareDate[launchBegintime]' ]
		});

		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				/* $('#rd_code').textbox('setText',''); */
				$('#launchBegintime').datetimebox('setValue','');
				$('#launchEndtime').datetimebox('setValue','');
				/* $('#launcher').textbox('setValue',''); */
				if(index == "0"){
					$("#lbname").text("发令人:");
					$('#selfdispatch').datagrid('hideColumn', 'promoter'); 
					$('#selfdispatch').datagrid('showColumn', 'receipter'); 
					$('#selfdispatch').datagrid('showColumn', 'transmitName'); 
					$('#selfdispatch').datagrid('showColumn', 'transmittime'); 
					$('#selfdispatch').datagrid('showColumn', 'launcher'); 
					$('#selfdispatch').datagrid('hideColumn', 'ldapproval'); 
					$('#selfdispatch').datagrid('hideColumn', 'ldapprovaltime'); 
					$('#selfdispatch').datagrid('showColumn', 'handlername'); 
					$('#selfdispatch').datagrid('showColumn', 'handletime'); 
				}else if(index == "1"){
					$("#lbname").text("发令人:");
					$('#selfdispatch').datagrid('showColumn', 'launcher'); 
					$('#selfdispatch').datagrid('showColumn', 'receipter'); 
					$('#selfdispatch').datagrid('hideColumn', 'transmitName'); 
					$('#selfdispatch').datagrid('hideColumn', 'transmittime'); 
					$('#selfdispatch').datagrid('hideColumn', 'promoter'); 
					$('#selfdispatch').datagrid('hideColumn', 'ldapproval'); 
					$('#selfdispatch').datagrid('hideColumn', 'ldapprovaltime'); 
					$('#selfdispatch').datagrid('hideColumn', 'ldapproval'); 
					$('#selfdispatch').datagrid('hideColumn', 'ldapprovaltime');
					$('#selfdispatch').datagrid('showColumn', 'handlername'); 
					$('#selfdispatch').datagrid('showColumn', 'handletime'); 
				}else {
					$("#lbname").text("调度发起人:");
					$('#selfdispatch').datagrid('hideColumn', 'receipter'); 
					$('#selfdispatch').datagrid('hideColumn', 'transmitName'); 
					$('#selfdispatch').datagrid('hideColumn', 'transmittime'); 
					$('#selfdispatch').datagrid('showColumn', 'promoter'); 
					$('#selfdispatch').datagrid('showColumn', 'ldapproval'); 
					$('#selfdispatch').datagrid('showColumn', 'ldapprovaltime'); 
					$('#selfdispatch').datagrid('hideColumn', 'launcher'); 
					$('#selfdispatch').datagrid('hideColumn', 'handlername'); 
					$('#selfdispatch').datagrid('hideColumn', 'handletime'); 
				}
				$('#selfdispatch').datagrid('load', {
					/* code : $('#rd_code').textbox('getText'), */
					launchBegintime : $('#launchBegintime').datetimebox('getValue'),
					launchEndtime : $('#launchEndtime').datetimebox('getValue'),
					/* launcher : $('#launcher').textbox('getValue'), */
					typeData:index
				});
			}
		});

		$('#selfdispatch').datagrid(
				{
					url : 'exertionrecord/pageBind.html',
					columns : [ [ 
						 {field : 'dispatchtype',title : '类型',width : '100px'}, 
			              {field : 'code',title : '分解单编号',width : '125px'}, 
			              {field : 'launchTime',title : '发起时间',width : '125px'}, 
			              {field : 'launcher',title : '发令人',width : '100px'}, 
			              {field : 'promoter',title : '调度发起人',width : '100px',hidden:true}, 
			              {field : 'ldapproval',title : '领导审批人',width : '100px',hidden:true}, 
			              {field : 'ldapprovaltime',title : '领导审批时间',width : '125px',hidden:true}, 
			              {field : 'receipter',title : '接收人',width : '100px'}, 	
			              {field : 'handlername',title : '签发人',width : '100px'}, 	
			              {field : 'handletime',title : '签发时间',width : '125px'}, 	
			              {field : 'sender',title : '部门执行人',width : '100px',hidden:true}, 
			              {field : 'sendTime',title : '部门执行时间',width : '150px',hidden:true}, 
			              {field : 'transmitName',title : '转发人',width : '100px'}, 
			              {field : 'transmittime',title : '转发时间',width : '125px'}
	                ] ],
					toolbar : '#toolbar',
					pageSize : 20,
					width : 'auto',
					height : 'auto',
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					striped : true,
					border : true,
					queryParams: {
						typeData:"0"
					},
					collapsible : false,
					fit : true,
					onDblClickRow : function(index, row) {
						var id = row.id;
						if (id != null && id != "") {
							var tab = $('#tt').tabs('getSelected');
							var index = $('#tt').tabs('getTabIndex',tab);
							showDialogWH("查看统计执行明细信息","exertionrecord/exertionrecordInfo.html?id="
											+ id+"&type="+index, 820, 580);
						}
					}

				});
		var p = $("#selfdispatch").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#print").bind("click", function() {
			//$("#department").jqprint();		
			//showDialogWH("打印","statisticsList/print.html", 600,580);
			var tab = $('#tt').tabs('getSelected');
		    var index = $('#tt').tabs('getTabIndex',tab);
			var launchBegintime = $('#launchBegintime').datetimebox('getValue');
			var launchEndtime = $('#launchEndtime').datetimebox('getValue');
			var typeDate = index;
			window.open("exertionrecord/print.html?launchBegintime="+ launchBegintime + "&launchEndtime="+launchEndtime + "&typeData="+typeDate,"blank");
		  	//window.print();
		});
		$("#export").bind("click",function(){
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			var launchBegintime = $('#launchBegintime').datetimebox('getValue');
			var launchEndtime = $('#launchEndtime').datetimebox('getValue');
			var typeDate = index;
			var link="exertionrecord/export.html?launchBegintime="+ launchBegintime + "&launchEndtime="+launchEndtime + "&typeData="+typeDate;
			window.location.href=link;
		});
		
		
	});

	function Search() {
		$('#selfdispatch').datagrid('unselectAll');
		var tab = $('#tt').tabs('getSelected');
		var index = $('#tt').tabs('getTabIndex',tab);
		$('#selfdispatch').datagrid('load', {
			/* code : $('#rd_code').textbox('getText'), */
			launchBegintime : $('#launchBegintime').datetimebox('getValue'),
			launchEndtime : $('#launchEndtime').datetimebox('getValue'),
			/* launcher : $('#launcher').textbox('getValue'), */
			typeData:index
		});
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
		<div id="toolbar">
			<div id="tt" class="easyui-tabs" style="">
				<div title="大包围调度" data-options="closable:false"
					style="overflow:auto;padding:20px;display:none;width: 100px;">
				</div>
				<div title="片区调度" style="padding:20px;display:none;width: 100px;">
				</div>
				<div title="自主调度" style="padding:20px;display:none;width: 100px;">
				</div>
			</div>
				<div class="cz_div_title" id="btnList" style="">
				<a id="print" class="easyui-linkbutton" style=""data-options="plain:false,iconCls:'icon-edit'">打印</a>
				&nbsp;&nbsp;<a id="export" class="easyui-linkbutton" style=""data-options="plain:false,iconCls:'icon-edit'">导出</a>
			</div>
			<div class="cz_div" style="min-height:30px;height:auto !important;">
				<!-- 调度单编号:&nbsp;<input id="rd_code" class="easyui-textbox"
					style="width: 120px" /> &nbsp;&nbsp;&nbsp; <label id="lbname" style="width:65px">调度发起人:</label><input
					id="launcher" class="easyui-textbox" style="width: 120px" />&nbsp;&nbsp;&nbsp; -->
				发起时间区间:&nbsp;<input id="launchBegintime" />~<input
					id="launchEndtime" />&nbsp;&nbsp;&nbsp; <a
					class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="Search()">查询</a><label style="width:120px;color:red;">（双击查看详细信息）</label>
				</td>
			</div>
		</div>
		<div data-options="region:'center'">
			<table id="selfdispatch"></table>
		</div>
	</div>
</body>
</html>
