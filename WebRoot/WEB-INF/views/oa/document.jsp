<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>My JSP 'document.jsp' starting page</title>

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
	$(function() {
		$("#document_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'document/search.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb'
		});

		var p = $("#document_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		})
	});

	function add() {
		showDialogWH("添加公文", "document/documentInfo.html?id=0", "500px",
				"500px");
	}
	function selectDocument() {
		var data = {

			code : $("#code").textbox("getValue"),
			title : $("#title").textbox("getValue")
		};
		$("#document_list").datagrid('load', {
			jsonStr : JSON.stringify(data)
		});
	}
	function watch(){
		var row = $("#document_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		if (row.state == "0") {
			$.post("document/watch.html", {
				id : row.id,
				userid:row.receiverid
			});
			$("#document_list").datagrid("reload");
		} 
		
		$("#tbr").datagrid('load','document/receiverBind.html?id='+row.id);
		$('#dr').dialog({    
		    title: '接收人信息',
		    iconCls:'',
		    width: 200,    
		    height: 150,    
		    closed: false,    
		    cache: false,    
		    modal: true   
		});
	}
	function edit(){
		var row = $("#document_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialog("编辑员工信息", "document/documentInfo.html?id=" + row.id);
		
	}
	function down() {
		var row = $("#document_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		var link='document/download.html?'+'fileaddress='+myEncoder(row.fileaddress);
		window.open(link);
		return false;	
	}

	function distory() {
		var row = $("#document_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "document/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#document_list").datagrid("reload");
					}
				});
			}
		});
	}
</script>

<body>
	<table id="document_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
			    <th data-options="field:'leve',width:80">公文类别</th>
				<th data-options="field:'code',width:80">公文编号</th>
				<th data-options="field:'title',width:120">公文标题</th>
				<th data-options="field:'createname',width:120">创建人</th>
				<th data-options="field:'createtime',width:160">创建时间</th>
				<!-- <th data-options="field:'username',width:120">接收人</th> -->
				<th data-options="field:'receiptdate',width:160">接收时间</th>
				<!-- <th data-options="field:'createname',width:120">最近修改人</th> -->
				<th data-options="field:'edittime',width:160">最近修改时间</th>
				<th data-options="field:'pc',width:80">PC消息提醒</th>
				<th data-options="field:'phonemessage',width:80">手机短信提醒</th>
			 <th data-options="field:'fileaddress',hidden:true"></th> 
				<!-- <th data-options="field:'linkaddress',width:80">下载地址</th> -->
				<th data-options="field:'state',hidden:true"></th>
				<th data-options="field:'receiverid',hidden:true"></th>
				<th data-options="field:'createid',hidden:true"></th>
				<th data-options="field:'id',hidden:true"></th>
			</tr>
		</thead>
	</table>
	<div id="tb" >
		<div class="cz_div_title">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="add()" iconCls="icon-add" plain="false">添加</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"

				onclick="distory()" iconCls="icon-remove" plain="false">删除</a>

			 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="watch()"


				iconCls="icon-search" plain="false">查看接收人</a>  

				<a
				href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()"

				iconCls="icon-edit" plain="false">编辑</a>
				<!-- <a

				iconCls="icon-edit" plain="false">编辑</a><a
>>>>>>> .r1219
				href="javascript:void(0)" class="easyui-linkbutton" onclick="down()"
<<<<<<< .mine
				iconCls="icon-download" plain="true">下载</a> -->
<!--                 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="down()"
				iconCls="icon-download" plain="false">下载</a> -->

		</div>
		<div class="cz_div">
			公文编号: <input id="code" class="easyui-textbox" data-options="width:80">
			公文标题: <input id="title" class="easyui-textbox"
				data-options="width:80"> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="selectDocument()"
				iconCls="icon-search">查询</a>
		</div>
	</div>
		<div id="dd"> </div>
	<div id="dr">
	<table id="tbr" class="easyui-datagrid"  style="width:700px;height:250px"
			data-options="singleSelect:true,collapsible:true">
		<thead>
			<tr>
				<!-- <th data-options="field:'code',width:80">公文编号</th>
				<th data-options="field:'title',width:120">公文标题</th> -->
				<th data-options="field:'username',width:120">接收人</th>
			</tr>
		</thead>
	</table>
	</div>
</body>
</html>
