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
    <title>新增待办事项</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		$(function(){
			$('#userid').combobox({
				textField:'name',
				valueField:'userid',
				url:'todolist/userList.html',
				width:112,
				editable:false
			});
			$('#todolist').datagrid({
				url:'todolist/todolistList.html',				
				columns:[[	       			        
			        {field:'title',title:'标题'},
			        {field:'contents',title:'内容'},
			        {field:'dodate',title:'待办时间'},
			        /* {field:'username',title:'代办人'}, */
			        {field:'ispc',title:'PC消息提醒'},
			        {field:'isphonemess',title:'手机短信提醒'},
			        {field:'state',title:'状态'},			        
			        /* {field:'fileaddress',title:'附件地址'} */
			    ]],
			    toolbar:'#toolbar',
			    pageSize : 20,
			    width : 'auto',
				height : 'auto',
				singleSelect : true,
				pagination : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				/* onDblClickRow:function(index,row){
					showDialogWH("修改待办事项", "todolist/todolistInfodbl.html?id="
							+row.id,430,400);
				} */
			})
			var p = $("#todolist").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})
		function Search(){
			$.messager.progress();
			var userid=$('#userid').combobox('getValue');
			var title=$('#title').textbox('getText');
			$('#todolist').datagrid('load',{				
				"userid":userid,"title":title
			});
			$.messager.progress('close');
		}
		function Add(){					
			showDialogWH("添加待办事项", "todolist/todolistInfo.html?id=0",430,400);
		}	
			
		function Edit(){
			var Item=$('#todolist').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialogWH("修改待办事项", "todolist/todolistInfoEdit.html?id="
						+Item.id,430,400);
			}
		}

		function Delete(){
			var Item=$('#todolist').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"todolist/todolistDelete.html",
							data:{
								"todolistid":Item.id
							},
							success:function(){								
								$("#todolist").datagrid("reload");
							}
						});
					}
				});
			}
		}
		function Down(){
			var Item=$('#todolist').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				var link='todolist/download.html?fileaddress='+myEncoder(Item.fileaddress);
				window.open(link);
			}
		}
		function SearchSituation(){
			var Item=$('#todolist').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialogWH("查看待办事项", "todolist/searchTodopeopleById.html?id="+Item.id,800,400);
			}
		}
	</script>
  </head>
  
  <body class="easyui-layout">
	<div data-options="region:'center'">
    	<table id="todolist"></table>
    </div>
    <div id="toolbar">       	       	
    	<div class="cz_div_title">
    			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Add()">新增</a>
    			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="Edit()">编辑</a>
    			<a class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="Delete()">删除</a>	
    			<a class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="SearchSituation()">查看执行情况</a>    
    	</div>
    	<div class="cz_div">
    			标题：<input id="title" class="easyui-textbox" data-options="width:112"/>&nbsp;&nbsp;
    			代办人：<input id="userid"/>&nbsp;&nbsp;
    			<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查询</a>
    	</div>
    </div>
  </body>
</html>
