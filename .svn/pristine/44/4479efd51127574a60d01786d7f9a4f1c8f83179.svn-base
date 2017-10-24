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
<title>My JSP 'user.jsp' starting page</title>

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
				$("#task_list").datagrid("load", {
					type : index
				});
			}
		});
		$("#task_list").datagrid({
			title : '任务中心',
			iconCls : 'icon icon-icon7',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'task/bind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			queryParams : {
				type : "0"
			},
			columns:[[    
			          {field:'name',title:'流程名称',width:120},    
			          {field:'uname',title:'任务类别',width:120},    
			          {field:'time',title:'发起时间',width:160},    
			          {field:'formkey',title:'操作',width:200,
			        	  formatter: function(val,row){
			        		  if(row.state=="0"){
				        		  if(row.uname=="组任务"){
				        		  	return '<a name="btn" id="'+row.id+'" onclick=claim(this) href="javascript:void(0)">接收任务</a>&nbsp;&nbsp;&nbsp;<a name="btn" id="'+row.id+'" onclick=selectView('+row.id+') href="javascript:void(0)">查看流程图</a>'; 
				        		  }else{
				        		  	return '<a name="btn" id="'+row.id+'"  href="'+row.formkey+'">办理任务</a>&nbsp;&nbsp;&nbsp;<a name="btn" id="'+row.id+'" onclick=selectView('+row.id+') href="javascript:void(0)">查看流程图</a>'; 
				        		  }
			        		  }else{
			        			  return '<a name="btn" id="'+row.id+'"  href="'+row.formkey+'">查看任务</a>'; 
			        		  }
						}
					   }	  
			      ]],
			onLoadSuccess:function(){
				$('a[name=btn]').linkbutton({    
				    iconCls: 'icon-add'   
				});
			}
		}); 
		var p = $("#task_list").datagrid('getPager');
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
	function selectView(id){
		showDialogWH("查看流程图", "task/imageInfo.html?id="+id,500,500);
	}
	function claim(obj){
		var id=$(obj).attr("id");
		$.post("task/claim.html",{"id":id},function(){
			$("#task_list").datagrid("load");
		});
	}
	function selectMessages() {
		$("#task_list").datagrid('load', {
			starttime : $("#starttime").datetimebox("getValue"),
			endtime : $("#endtime").datetimebox("getValue")
		});
	}
	/* function add() {
		showDialog("添加用户信息", "workFlow/workFlowInfo.html");
	}
	function distory() {
		var row = $("#task_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "workFlow/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#task_list").datagrid("unselectAll");
						$("#task_list").datagrid("reload");
					}
				});
			}
		});
	} */
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="task_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto">
		<div id="tt" class="easyui-tabs" style="">
			<div title="进行中的任务" style="padding:20px;display:none;width: 100px;">
			</div>
			<div title="历史任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div style="margin-bottom:5px">
			开始时间: <input id="starttime" type="text" data-options="width:160">
			结束时间: <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectMessages()" iconCls="icon-search">查询</a>
		</div>
		<!-- <div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon-remove" plain="true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectView()" iconCls="icon-add" plain="true">查看流程图</a>
		</div> -->
	</div>
</body>
</html>
