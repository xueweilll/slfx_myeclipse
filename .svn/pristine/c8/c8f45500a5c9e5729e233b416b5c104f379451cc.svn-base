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

<title>督察列表</title>
<%@include file="../header.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	$(function() {
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2,'', {
			text: '清空',
			handler: function(target){
				$(target).datetimebox('setValue','');
				$(target).datetimebox('hidePanel');
			}
		});
		$('#begintime').datetimebox({
			editable:false,
			width:160,
			buttons:buttons,
			onChange:function(){
				$("#endtime").datetimebox('enableValidation');
			}
		});
		$('#endtime').datetimebox({
			editable:false,
			width:160,
			buttons:buttons,
			validType:['compareDate[begintime]']
		});
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				if (index == "1") {
					$("#adds").hide();
					$("#complete").hide();
					$("#btnList").hide();
				} else {					
					$("#adds").show();
					$("#complete").show();
					$("#btnList").show();
				}
				$("#tr_list").datagrid("unselectAll");
				$("#tr_list").datagrid("load", {
					type : "1",
					typeDate : index
				});
			}
		});
		$("#tr_list").datagrid({

			iconCls : 'icon icon-icon10',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'supervise/pageBind.html',
			queryParams : {
				type : "1",
				typeDate : "0"
			},
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){				
				showDialogWH("查看督察信息", "supervise/superviseLookInfo.html?rdid="
						+ row.id, 740, 520);
			}
		});

		var p = $("#tr_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		$("#search").bind("click", function() {
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex', tab);
			$("#tr_list").datagrid('unselectAll');
			$("#tr_list").datagrid('load', {
				type : "1",
				typeDate : index,
				code : $("#code").val(),
				launcher : $("#launcher").val(),
				begintime:$('#begintime').datetimebox('getValue'),
      			endtime:$('#endtime').datetimebox('getValue')
			});
		});

	});


	function supervise() {
		var row = $("#tr_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		}else{
		showDialogWH("添加督察信息", "supervise/superviseInfo.html?rdid="
				+ row.id, 740, 530);
		}
		$("#tr_list").datagrid("unselectAll");
		}

	function complete() {
		var row = $("#tr_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
		}
		if (row.state == "2") {
			$.messager.alert("操作提示", "未督察不能完成！", "error");
			return false;
		}else{
			$.messager.confirm('确认对话框', '您确定要完成吗?', function(r){
				if (r){
					$.ajax({
						type : 'POST',
						url : "supervise/complete.html",
						data : {
							"id" : row.id
						},
						success : function() {
							$.messager.progress("close");
							reflush();

						}
					});
				}
			});
		}
		$("#tr_list").datagrid("unselectAll");
	}

	function reflush() {
		$("#tr_list").datagrid('reload');
	}
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="tr_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th hidden data-options="field:'code',width:130">分解单编号</th>
				<th data-options="field:'dispatchtype',width:100">调度令类型</th>
				<th data-options="field:'launcher',width:100">调度令发起人</th>
				<th data-options="field:'launchTime',width:140">调度令发起时间</th>
				<th data-options="field:'endTime',width:140">调度令结束时间</th>
				<th data-options="field:'receipter',width:80">调度令接收人</th>
				<th data-options="field:'receipteTime',width:130">调度令接收时间</th>
				<!-- <th data-options="field:'memo',width:130">调度备注</th> -->
				<th data-options="field:'creater',width:100">调度令制单人</th>
				<th data-options="field:'createtime',width:130">调度令制单时间</th>
				<th data-options="field:'transmit',width:100">分解单转发人</th>
				<th data-options="field:'transmittime',width:130">分解单转发时间</th>
				<th data-options="field:'stateName',width:100">状态</th>

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
		<div class="cz_div_title" id="btnList" style="">
			<a id="adds" style="" href="javascript:void(0)"
				class="easyui-linkbutton" onclick="supervise()" iconCls="icon-add"
				plain="false">督察</a> <a id="complete" style=""
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="complete()" iconCls="icon-add" plain="false">完成</a>
		</div>
		<div class="cz_div">
		<div style="display:none;">
			分解单编号:&nbsp;<input id="code" class="easyui-textbox"
				style="width: 120px"> &nbsp;&nbsp; 
				</div>
				调度发起人:&nbsp;<input
				id="launcher" class="easyui-textbox" style="width: 120px">
			&nbsp;&nbsp;
			调度令发起时间:<input id="begintime"/>~<input id="endtime"/>&nbsp;&nbsp;
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" id="search">查询</a>
		</div>
	</div>
</body>
</html>
