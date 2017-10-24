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
    <title>My JSP 'document.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <script type="text/javascript">
		$(function(){
		$("#document_list").datagrid({
			title:'公文查看',
			iconCls:'icon icon-icon24',
			width:'auto',
			height:'auto',
			pageSize:20,
			nowrap:false,
			striped:true,
			border:true,
			collapsible:false,
			fit:true,
			url:'search/search.html',
			remoteSort:false,
			idField:'documentId',
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			toolbar:'#tb'
		});
		
		var p = $("#document_list").datagrid('getPager');
		$(p).pagination({
			pageList:[5,10,15,20],
			beforePageText:'第',
			afterPageText:'页        共{pages}页',
			displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
		});
		
		$("#down").bind("click", function() {
			var row = $("#document_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			var link='search/download2.html?'+'fileaddress='+myEncoder(row.fileaddress);
			window.open(link);
			return false;
		});
	});
	
	function watch(){
		var row = $("#document_list").datagrid("getSelected");
		showDialogWH("查看公文", "search/documentWacth.html?id="+row.id+"&userid="+row.receiverid,"500px","400px");
	}
	
	
	function selectDocument(){
		var data={
				
				code: $("#code").textbox("getValue"),
				title: $("#title").textbox("getValue")	
		};
		
		$("#document_list").datagrid('load',{
			jsonStr: JSON.stringify(data)
		});
		
	}
	
	/* function down() {
		var row = $("#document_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.post("search/download.html", {
			id : row.id,
			fileaddress : row.fileaddress
		});
	} */
	function distory(){
		var row = $("#document_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "document/distory.html",
					data : {
						entityId : row.documentId
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
				<th data-options="field:'code',width:80">公文编号</th>
				<th data-options="field:'title',width:120">公文标题</th>
				<th data-options="field:'createname',width:120">发件人</th>
				<th data-options="field:'createtime',width:120">发件时间</th>
				<th data-options="field:'username',width:120">收件人</th>
				<th data-options="field:'receiverid',hidden:true"></th>
				<!-- <th data-options="field:'fileaddress',hidden:true">文件下载</th> -->
			</tr>
		</thead>
	</table>
	<div id="tb" >
		<div class="cz_div">
			公文编号: 
			<input id="code" class="easyui-textbox"  data-options="width:80" >
			公文标题:
			<input id="title" class="easyui-textbox"  data-options="width:80" >
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectDocument()" iconCls="icon-search">查询</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" target="blank" 
				iconCls="icon-search" plain="true" onclick="watch()">查看</a> 
				<!-- <a href="uplaod/download.html" target="blank"  id="down" ><button>下载</button></a> -->
		</div>
	</div>
  </body>
</html>
