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
    <title>查看待办事项</title>   
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
			var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
			buttons.splice(2,'', {
				text: '清空',
				handler: function(target){
					$(target).datetimebox('setValue','');
					$(target).datetimebox('hidePanel');
				}
			})
			$('#begin').datetimebox({
				editable:false,
				width:160,
				buttons:buttons,
				onChange:function(){
					$("#endtime").datetimebox('enableValidation');
				}
			})
			$('#end').datetimebox({
				editable:false,
				width:160,
				buttons:buttons,
				validType:['compareDate[begin]']
			})
			$('#todolistpeople').datagrid({
				url:'todolist/todolistSearch.html',
				columns:[[
			        {field:'title',title:'标题'},
			        {field:'contents',title:'内容'},
			        {field:'username',title:'待办人'},
			        {field:'dodate',title:'待办时间'},			       
			        {field:'state',title:'状态'}			        
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
				fit : true
			})
			var p = $("#todolistpeople").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})							
		function Edit(){
			var Item=$('#todolistpeople').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				if(Item.state=="待处理"){
					$.post('todolist/doneTodo.html?id='+Item.id+'&todolistid='+Item.todolistid,function(msg){
						$('#todolistpeople').datagrid('reload');
					})
				}else if(Item.state=="待查看"){
					alert("请先查看");
				}else{
					alert("已处理,无需再操作");
				}
			}
		}
		function Kan(){
			var Item=$('#todolistpeople').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				var state=Item.state;
				if(state=="待查看"){
					showDialogWH("查看待办事项", "todolist/todolistInfo.html?id="+Item.id+"&state=0"+"&todolistid="+Item.todolistid,430,400);
				}else{
					showDialogWH("查看待办事项", "todolist/todolistInfo.html?id="+Item.id,430,400);
				}
				$('#todolistpeople').datagrid('reload');
			}
		}
		function Search(){
			$.messager.progress();
			var title=$('#title').textbox('getText');
			var begin=$('#begin').datetimebox('getText')==""?"":$('#begin').datetimebox('getText');
			var end=$('#end').datetimebox('getText')==""?"":$('#end').datetimebox('getText');
			$('#todolistpeople').datagrid('load',{
				"title" : title,
				"begin" : begin,
				"end":end
			});
			$.messager.progress('close');
		}
		function Delete(){
			var Item=$('#todolistpeople').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"todolist/todolistDelete.html",
							data:{
								"todolistid":Item.todolistid,
								"todolistpeopleid":Item.id
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
			var Item=$('#todolistpeople').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				var link='todolist/download.html?fileaddress='+myEncoder(Item.fileaddress);
				window.open(link);
			}
		}
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
    <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        <div id="toolbar">       	       	
        	<div class="cz_div_title">
        			<a class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="Kan()">查看</a>
        			<a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="Edit()">反馈</a>
        			<!-- <td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="Down()" iconCls="icon-download" plain="true">下载</a></td> -->        			     			    
        	</div>	
        		<div class="cz_div">
        			标题：<input id="title" class="easyui-textbox" data-options="width:112"/>&nbsp;&nbsp;
        			待办时间：<input id="begin" />~<input id="end" />&nbsp;&nbsp;
        			<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查询</a>
        	</div>
        	
        </div>
        <div data-options="region:'center'">
        	<table id="todolistpeople"></table>
        </div>
    </div>
  <!--   <div id="dialog"></div> -->
  </body>
</html>
