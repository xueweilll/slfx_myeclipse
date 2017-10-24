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
    <title>报警记录表</title>   
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
			buttons.splice(1, 0, {
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
					$("#end").datetimebox('enableValidation');
				}
			});
			$('#end').datetimebox({
				editable:false,
				width:160,
				buttons:buttons,
				validType:['compareDate[begin]']
			});
			$('#station').combobox({
				width:150,
				editable:false,				
				valueField:'code',
				textField:'name',
				url:'warning/station.html',
				onLoadSuccess:function(data){
					$(this).combobox('select',0);
				}
			});
			$('#warning').datagrid({
				url:'warning/warningList.html',
				columns:[[
					{field:'stationname',title:'枢纽名称'},
			        {field:'fStationcode',title:'测站编码'},
			        {field:'fWarncode',title:'报警编码'},
			        {field:'fWarntime',title:'报警时间'},
			        {field:'fFreetime',title:'解除时间'},
			        {field:'fWarnstate',title:'报警状态'}
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
			var p = $("#warning").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})
		function Search(){
			$.messager.progress();
			var stationname=$('#station').combobox('getValue');
			var begin=$('#begin').datetimebox('getValue');
			var end=$('#end').datetimebox('getValue');
			$('#warning').datagrid('load',{				
				stationname:stationname,
				begin:begin,
				end:end
			});			
			$.messager.progress('close');
		}				
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
    <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        <div id="toolbar" >       	       	
        	<div class="cz_div_title">
        	</div>
        	<table class="cz_div" cellpadding="0"cellspacing="0" style="padding-top:0px;">
        		<tr>        			
        			<td>枢纽名称：</td>
        			<td><input id="station" class="easyui-textbox" data-options="width:150"/></td>
        			<td>报警时间</td>
        			<td><input id="begin" />~<input id="end" /></td>
        			<td><a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查询</a></td>
        		</tr>
        	</table>
        </div>
        <div data-options="region:'center',iconCls:'icon icon-icon11'">
        	<table id="warning"></table>
        </div>
    </div>
    <div id="dialog"></div>
  </body>
</html>
