<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>巡检记录处理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
		$(function(){
			var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
			buttons.splice(1, 0, {
				text: '清空',
				handler: function(target){
					$(target).datetimebox('setValue','');
					$(target).datetimebox('hidePanel');
				}
			});
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
			$('#approval').datagrid({
				url:'resolve/patrolDetailProblemList2.html',
				columns:[[
			        {field:'id',title:'id',hidden:"hidden"},
			        {field:'stationname',title:'枢纽名称',width:150},
			        {field:'degree',title:'次数',width:100},
			        {field:'parttime',title:'实际巡检时间',width:200},
			        {field:'handlername',title:'巡检人',width:150},
			        {field:'creatername',title:'制单人',width:100},
			        {field:'creatertime',title:'制单时间',width:200}
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
				onDblClickRow:function(index,row){
					var arr=[];
					arr.push(row.id);
					var data ={
							"type":0,
							"ids":arr
					}
				    showDialogWH("解决巡检记录", "resolve/resolveinfo.html?data="+JSON.stringify(data),800,530);
				}
			});
			var p = $("#approval").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		});		
		/* function reflush() {
			$('#approval').datagrid('reload');
		} */
		function resolve(){
			var Item=$('#approval').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				var arr=[];
				arr.push(Item.id);
				var data ={
						"type":0,
						"ids":arr
				}
			    showDialogWH("解决巡检记录", "resolve/resolveinfo.html?data="+JSON.stringify(data),800,530);
			}
		}
		function Search(){
			$('#approval').datagrid('unselectAll');
			$("#approval").datagrid("load", {
				begin : $("#begin").datetimebox("getValue"),
				end : $("#end").datetimebox("getValue")
			});
		}
	</script>
</head>
<body class="easyui-layout" id="cc">
	<form id="ffemp" method="post" enctype="multipart/form-data"
		style="margin:10px;text-align: center;">

		<%-- <input id="patrolplandetailsid" value="${patrol.getPatrolplandetailsid()}" type="hidden" /> --%>
		
		<div id="toolbar">
			<div class="cz_div_title" style="display:none">   	       	
	        	<table>
	        		<tr>
	        			<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="resolve()">处理</a></td>        					
	        		</tr>
	        	</table>
        	</div>    
        	<div class="cz_div">
				实际巡检时间:&nbsp;&nbsp;<input id="begin" /> ~ <input id="end"/>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="Search()" id="search" iconCls="icon-search">查询</a>
			</div>			
		</div>
		<div data-options="region:'center'"><table id="approval"></table></div>		
	</form>
	

</body>
</html>
