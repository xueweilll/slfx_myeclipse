<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>查看代办事项-查看</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
</head>

<body>	
	<form id="ffemp1" method="post"  enctype="multipart/form-data"
		style="margin-top: 10px; text-align: center;">
		<div>					
			<input id="id" type="hidden" value="${todopeople.getId()}" />
			<input id="fileaddress" type="hidden" value="${fileaddress}"/>
			<table>
				<tr>
					<td><label for="title">标题:</label></td>
					<td><input id="title" class="easyui-textbox" 
					value="${todopeople.getTodolist().getTitle()}" 
					data-options="disabled:true,width:300" /></td>					
				</tr>
				<tr>
					<td><label for="dodate">待办时间:</label></td>
					<td><input id="dodate" class="easyui-datebox" 
					value="${todopeople.getTodolist().getDodate()}"
					data-options="disabled:true,width:300" /></td>					
				</tr>
				<tr>
					<td><label for="contents">内容:</label></td>
					<td><input id="contents" style="width:300px;height:100px;"
					class="easyui-textbox" data-options="multiline:true,disabled:true"
			 		value="${todopeople.getTodolist().getContents()}"/></td>					
				</tr>				
			</table>			 																		
		</div>			
		<div id="more"></div>
	</form>
	<script type="text/javascript">
		$(function() {
			var fileaddress=$("#fileaddress").val();
			var address=undefined;
			if(fileaddress!=""){
				address=fileaddress.split(",");
				for(var i=0;i<address.length;i++){					
					createInput(address[i]);
				}
			}
			reflush();
		});
		function reflush() {
			document.getElementById('todosearch.htmlifm').contentWindow.$('#todolistpeople').datagrid('reload');
		}
		function Down(address){
			var link='todolist/download.html?fileaddress='+myEncoder(address);
			window.open(link);
		}			
		function createInput(address){
			var add=address.substring(37);				
			var str='<div style="text-align:left"><label style="width:58px">文件:</label>'+
			'<input class="easyui-textbox" data-options="width:243,editable:false" disabled="true" value="'+add+'" />'+			
			'  '+'<a class="easyui-linkbutton" data-options="iconCls:\'icon-redo\'" onclick=Down("'+address+'") >下载</a>'+'</div>';
			document.getElementById("more").insertAdjacentHTML("beforeEnd",str);
		}					
	</script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
</body>
</html>
