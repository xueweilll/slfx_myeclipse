<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../../header.jsp"%>
<title>管理处审阅弹窗(应急)</title>

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
				if(index=="0"){
					$("#commit").show();
				}else{
					$("#commit").hide();
				}
				$("#psprojectreport_list").datagrid("unselectAll");
				$("#psprojectreport_list").datagrid("load", {
					typeDate : index
				});
			}
		});
		$("#psprojectreport_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'patrolSpecialmanagement/bind.html',
			idField : 'id',
			singleSelect : true,
			queryParams: {
				typeDate : 0
			},
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			onDblClickRow:function(rowIndex, rowData){
				searchInfo(rowData);
			},
			columns:[[    
			          {field:'code',title:'编号',width:120},    
			          {field:'creater',title:'签发人',width:140},    
			          {field:'createtime',title:'签发时间',width:160},    
			          {field:'content',title:'签发内容',width:300},
			          {field:'remark',title:'备注',width:300,hidden:true}
			      ]]
			  
		}); 
		var p = $("#psprojectreport_list").datagrid('getPager');
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
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		var tab = $('#tt').tabs('getSelected');
		var index = $('#tt').tabs('getTabIndex',tab);
		$("#psprojectreport_list").datagrid('load',{
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue"),
			typeDate:index
		});
		$("#psprojectreport_list").datagrid("unselectAll");
	}
	
	function commit(){
		var row = $("#psprojectreport_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "warning");
			return false;
		}
		showDialogWH("提交签发单", "patrolSpecialmanagement/patrolSpecialmanagementInfo.html?isId=" + row.id+"&type=1",670,590);
	}
	function searchInfo(row){
		showDialogWH("查看签发单明细", "patrolSpecialmanagement/patrolSpecialmanagementInfo.html?isId=" + row.id+"&type=0",670,590);
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

</head>

<body class="easyui-layout" id="cc">
	<table id="psprojectreport_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto">
		<div id="tt" class="easyui-tabs" style="">
			<div title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div title="全部数据" style="padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div class="cz_div">
			<a  id="commit"  href="javascript:void(0)" class="easyui-linkbutton" onclick="commit()" iconCls="icon_commit" plain="false">审阅</a>&nbsp;&nbsp;&nbsp;&nbsp;
			签发时间： <input id="starttime" type="text" data-options="width:160,editable:false,buttons: buttons">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
