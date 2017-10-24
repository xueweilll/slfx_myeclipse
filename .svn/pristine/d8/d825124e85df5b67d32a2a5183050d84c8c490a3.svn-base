<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理处审核</title>
    <%@include file="../header.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">
	$(function() {
		$("#managementList").datagrid({
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			queryParams : {
		    	all : 0
			},
			url : 'psmanageapproval/pageBind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){
				showDialogWH("查看", "patrolmanagementexamine/patrolManagementInfo.html", 500, 550);
			}
		});
		
		var p = $("#managementList").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		

		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				$('#begin').datetimebox('setValue','');
				$('#end').datetimebox('setValue','');
				if(index==0){
					$('#tool').show();	
					$('#managementList').datagrid('showColumn', 'checked');
				}else{
					$('#tool').hide();
					$('#managementList').datagrid('hideColumn', 'checked');
				}
				$("#managementList").datagrid("unselectAll");
				$('#managementList').datagrid('load',{
					all:index,
					begin:$('#begin').datetimebox('getValue'),
					end:$('#end').datetimebox('getValue')
				});
			}
		});
	});
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
	<table id="managementList" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th hidden data-options="field:'id',width:150"></th>
				<th hidden data-options="field:'isid',width:150"></th>
				<th data-options="field:'reporttime',width:150">工程科上报时间</th>
				<th data-options="field:'reporter',width:150">审核人</th>
				<th data-options="field:'state',width:100">状态</th>
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
		<div id="tool" style="" class="cz_div_title">
			<table>
				<tr>
					<td><a class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" onclick="Edit()">审核</a></td>
				</tr>
			</table>
		</div>
		<div class="cz_div">
			审核时间:&nbsp;&nbsp;<input	id="begin" type="text" data-options="width:160"	class="easyui-datetimebox" /> ~
			 <input id="end" type="text"	class="easyui-datetimebox"	data-options="width:160,validType:['compareDate[starttime1]']" />
			&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="search()" id="search" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
