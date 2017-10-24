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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
		$(function(){
		$("#document_list").datagrid({
			title:'公文接受',
			iconCls:'icon icon-icon30',
			width:'auto',
			height:'auto',
			pageSize:20,
			nowrap:false,
			striped:true,
			border:true,
			collapsible:false,
			fit:true,
			url:'accept/search.html',
			remoteSort:false,
			idField:'id',
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			toolbar:'#tb',
	/* 		rowStyler:
				function(index,row){
				if(row.state == "未接收"){
					 return 'background-color:#6293BB;color:#fff;font-weight:bold;';
				}
			} */
		});
		
		var p = $("#document_list").datagrid('getPager');
		$(p).pagination({
			pageList:[5,10,15,20],
			beforePageText:'第',
			afterPageText:'页        共{pages}页',
			displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
		})
	});
	
	function add(){
		showDialogWH("添加公文", "document/documentInfo.html?id=0","450px","300px");
	}	
	function selectDocument(){
		var data={
				
				code: $("#code").textbox("getValue"),
				//title: $("#title").textbox("getValue")	
		};
		
		$("#document_list").datagrid('load',{
			jsonStr: JSON.stringify(data)
		});
	}
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
				<th data-options="field:'code',width:120">公文编号</th>
 				<th data-options="field:'username',width:120">接收人</th> 
				<th data-options="field:'receiptdate',width:120">接收时间</th>
                <th data-options="field:'state',width:120">接收情况</th>

			</tr>
		</thead>
	</table>
	<div id="tb" >
			<div class="cz_div">
			公文编号: 
			<input id="code" class="easyui-textbox"  data-options="width:80" >
			<!-- 公文标题:
			<input id="title" class="easyui-textbox"  data-options="width:80" > -->
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectDocument()" iconCls="icon-search">查询</a>
		</div>
	</div>
  </body>
</html>
