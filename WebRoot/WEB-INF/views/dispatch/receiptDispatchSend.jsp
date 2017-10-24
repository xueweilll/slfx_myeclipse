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

<title>片区调度下发</title>
<%@include file="../header.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function() {
		$('#tt').tabs({
			tabWidth:'160',
			border : false,
			onSelect : function(title, index) {
				$("#endtimes").datetimebox('setValue','');
				$("#starttime").datetimebox('setValue','');
				if(index=="1"){
					$(".cz_div_title").hide();	
				}else{					
					$(".cz_div_title").show();	
				}
				$("#rd_list").datagrid("unselectAll");
				$("#rd_list").datagrid("load", {
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
				$("#endtimes").datetimebox('enableValidation');
			}
			
		});
		$("#endtimes").datetimebox({
			editable:false,
		    buttons:buttons
		});
		$("#rd_list").datagrid({
		
			iconCls : 'icon icon-icon10',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'receiptDispatch/pageBind.html',
			queryParams: {
				typeDate:"0"
			},
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true
		});

		var p = $("#rd_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		$("#search").bind("click", function() {
			$("#rd_list").datagrid('unselectAll');
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#rd_list").datagrid('load',{				
				typeDate:index,
				bh:$('#code').textbox('getText'),
				starttime: $("#starttime").datetimebox('getValue'),
				endtime: $("#endtimes").datetimebox('getValue')
			});
		});

		$("#btnAdd").bind(
				"click",
				function() {
					var row = $("#rd_list").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					}
					//alert(row.departmentid);
					showDialogWH("添加执行信息",
							"receiptDispatch/receiptDispatchInfo.html?id="+ row.id+"&isDisplay=0&departmentid=" + row.departmentid, 740, 600);
				});

	});

	

	function reflush() {
		$("#rd_list").datagrid('reload');
	}
</script>
</head>

<body>
<body class="easyui-layout" id="cc">
	<table id="rd_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th hidden data-options="field:'code',width:130">分解单编号</th>
				<th data-options="field:'dispatchtype',width:100">调度令类型</th>
				<th data-options="field:'name',width:100">执行部门</th>
				<th data-options="field:'launcher',width:80">调度令发起人</th>
				<th data-options="field:'launchTime',width:140" hidden="true">调度令发起时间</th>
				<th data-options="field:'endTime',width:140" hidden="true">调度令结束时间</th>
				<th data-options="field:'receipter',width:80">调度令接收人</th>
				<th data-options="field:'receipteTime',width:130">调度令接收时间</th>
				<!-- <th data-options="field:'memo',width:130">调度备注</th> -->
				<th data-options="field:'creater',width:80">分解单制单人</th>
				<th data-options="field:'createtime',width:130">分解单制单时间</th>
				<th data-options="field:'sender',width:80">执行人</th>
				<th data-options="field:'sendTime',width:130">执行时间</th>
				<th data-options="field:'stateName',width:130">分解单状态</th>
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
		<div class="cz_div_title" id="sends" style="">
			<a id="btnAdd" class="easyui-linkbutton" 
				data-options="plain:false,iconCls:'icon-add'">执行</a>
	         </div>
	     <div class="cz_div">
	     <div style="display:none;">
			分解单编号:&nbsp;<input id="code" class="easyui-textbox"
				style="width: 120px"> &nbsp;&nbsp;
				</div>
							<div>
			调度令接收时间: <input id="starttime" type="text" class="easyui-datetimebox" data-options="width:160">
			 ~<input id="endtimes" type="text" class="easyui-datetimebox"
				data-options="width:160,validType:'compareDate[starttime]'">
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" id="search">查询</a>
				</div>
		</div>
	</div>
</body>
</html>

