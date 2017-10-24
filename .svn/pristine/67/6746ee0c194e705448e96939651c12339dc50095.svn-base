<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<%@include file="../header.jsp"%>
    <title>My JSP 'message.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script type="text/javascript">
		$(function(){
		$("#message_list").datagrid({
			title:'信息发送',
			iconCls:'icon icon-icon26',
			width:'auto',
			height:'auto',
			pageSize:20,
			nowrap:false,
			striped:true,
			border:true,
			collapsible:false,
			fit:true,
			url:'message/bind.html',
			remoteSort:false,
			idField:'id',
			onDblClickRow:function(rowIndex, rowData){
				selectContents(rowData);
			},
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			toolbar:'#tb'

		});
		
		var p = $("#message_list").datagrid('getPager');
		$(p).pagination({
			pageList:[5,10,15,20],
			beforePageText:'第',
			afterPageText:'页        共{pages}页',
			displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
		});
		
		$("#starttime").datetimebox({
			onChange:function(){
				$('#endtime').datetimebox('enableValidation');
			}
		});
	});
	function selectMessages(){
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		$("#message_list").datagrid('load',{
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue")
		});
	}
	function add(){
		showDialogWH("添加消息", "message/messageInfo.html?id=0","450px","300px");
	}	
	function edit() {
		var row = $("#message_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialog("编辑消息", "message/messageInfo.html?id=" + row.id);
	}
	function distory(){
		var row = $("#message_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "message/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#message_list").datagrid("unselectAll");
						$("#message_list").datagrid("reload");
					}
				});
			}
		});
	}
	function selectContents(row){
		$('#dd').dialog({    
		    title: '消息内容',
		    iconCls:'',
		    width: 400,    
		    height: 200,    
		    closed: false,    
		    cache: false,    
		    content: row.contentsAll,    
		    modal: true   
		});

	}
	function selectReceiver(){
		var row = $("#message_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			return false;
		}
		$("#tbr").datagrid('load','message/receiverBind.html?id='+row.id);
		$('#dr').dialog({    
		    title: '接收人列表',
		    iconCls:'',
		    width: 400,    
		    height: 200,    
		    closed: false,    
		    cache: false,    
		    modal: true   
		});
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
  
  <body class="easyui-layout">
    <table id="message_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th data-options="field:'contents',width:320" >短信内容</th>
				<th data-options="field:'createtime',width:160">创建时间</th>
 				<th data-options="field:'edittime',width:160">修改时间</th> 
				<th data-options="field:'sendtime',width:160">发送时间</th>
 				<th data-options="field:'isphonemess',width:60">手机消息</th> 
 				<th data-options="field:'ispc',width:60">PC消息</th> 
 				<th data-options="field:'contentsAll',hidden:true"></th> 
			</tr>
		</thead>
	</table>
	<div id="tb" >
		<div class="cz_div_title">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon_add" plain="false">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon_delete" plain="false">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectReceiver();" iconCls="icon-tip" plain="false">查看收件人</a>
		</div>
		<div class="cz_div">
			创建时间: 
			<input id="starttime" type="text"  data-options="width:160,buttons: buttons,editable:false" >
			~
			<input id="endtime" type="text" class="easyui-datetimebox" data-options="width:160,buttons: buttons,editable:false,validType:['compareDate[starttime]']" >
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectMessages()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div id="dd"> </div>
	<div id="dr">
	<table id="tbr" class="easyui-datagrid" data-options="singleSelect:true,collapsible:true">
		<thead>
			<tr>
				<th data-options="field:'name',width:160">姓名</th>
				<th data-options="field:'phone',width:160">手机号码</th>
			</tr>
		</thead>
	</table>
	</div>
  </body>
</html>
