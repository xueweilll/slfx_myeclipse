<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>管理处查看</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

	$(function() {
		$("#patrolmanagement_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'patrolmanagement/bind.html',
			idField : 'id',
			singleSelect : false,
			queryParams: {
				typeDate : 0
			},
			/* frozenColumns:[[{field:'ck',checkbox:true}]], */
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			onDblClickRow:function(rowIndex,rowData){
				searchInfo(rowData);
			},
			columns:[[    
			          /* {field:'departmentid',title:'所属部门',width:200},   */  
			          {field:'reporter',title:'上报人',width:200},    
			          {field:'reporttime',title:'上报时间',width:200}
			      ]]
			  
		}); 
		var p = $("#patrolmanagement_list").datagrid('getPager');
		//console.info(p);
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#starttime").datetimebox({
			onChange : function() {
				$('#endtime').datetimebox('enableValidation');
			}
		});
	});
	function selectbig(){
		$("#patrolmanagement_list").datagrid('unselectAll');
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		$("#patrolmanagement_list").datagrid('load',{
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue")
		});
	}
	function searchInfo(row){
		var array=[];
		array.push(row.id);
		var json = {
				"ids":array,
				"type":0
		}
		var str  = JSON.stringify(json);
		showDialogWH("查看明细", "patrolmanagement/patrolManagementInfo.html?jsonStr=" + str,650,500);
	}
	var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
	buttons.splice(0,1,{
		text: '清空',
		handler: function(target){
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	}); 
</script>
<style>
.datagrid-row-selected {
    background:  none repeat scroll 0 0;
    color: #000000;
}
</style>
</head>

<body class="easyui-layout" id="cc">
	<table id="patrolmanagement_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto">
		<!-- <div id="tt" class="easyui-tabs" style="">
			<div title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div title="全部数据" style="padding:20px;display:none;width: 100px;">
			</div>
		</div> -->
		<div class="cz_div">
			<!-- <a  id="collectCommit"  href="javascript:void(0)" class="easyui-linkbutton" onclick="collectCommit()" iconCls="icon_commit" plain="false">汇总上报记录</a>
			<a  id="supportCommit"  href="javascript:void(0)" class="easyui-linkbutton" onclick="supportCommit()" iconCls="icon_commit" plain="false">自行安排解决</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
			上报时间： <input id="starttime" type="text" data-options="width:160,editable:false,buttons: buttons">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
