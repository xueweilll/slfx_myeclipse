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
    <title>巡检记录审批</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script>
	var ids=[];
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
			$("#patrolapproval").datagrid({
				width : 'auto',
				height : 'auto',
				pageSize : 20,
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				url : 'patrolnormaldepartment/pageBind.html',
				remoteSort : false,
				rownumbers : true,
				pagination : true,
				singleSelect : true,					
				queryParams : {
				   all : 0
				},
				toolbar:'#toolbar',
				columns : [ [ 
					/* {field:'checked',width : 30,checkbox: true} , */
					{field:'dname',width:100,title:'部门'},
			        {field:'creatertime',width:100,title:'月份'},			        
			        {field:'degreename',width : 100,title:'次数'},
				    {field:'stationname',width : 300,title:'枢纽名称'},				   
			        {field:'ids',width : 160,title:'巡检人',hidden:true}
				] ],
				onDblClickRow:function(index,row){
					 showDialogWH("审批巡检记录", "patrolnormaldepartment/patrolnormaldepartmentInfo.html?ids="+row.ids+"&type=1",800,530);
				}
			});
			var p = $("#patrolapproval").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
			$('#tt').tabs({
				tabWidth : '160',
				border : false,
				onSelect : function(title, index) {
					$('#begin').datetimebox('setValue','');
					$('#end').datetimebox('setValue','');
					if(index==0){
						$('#tool').show();	
					}else{
						$('#tool').hide();
					}
					$("#patrolapproval").datagrid("unselectAll");
					$('#patrolapproval').datagrid('load',{
						all:index
					});
				}
			});
			
			Array.prototype.remove = function(val) {
				var index = this.indexOf(val);
				if (index > -1) {
				this.splice(index, 1);
				}
			};			
		});		
		function Edit(){
		
			var row = $("#patrolapproval").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
		    showDialogWH("审批巡检记录", "patrolnormaldepartment/patrolnormaldepartmentInfo.html?ids="+row.ids+"&type=0",800,530);
		}
		function Search(){
			if($("#end").datetimebox("getValue")>=$("#begin").datetimebox("getValue")||$("#end").datetimebox("getValue")==""){
			$("#patrolapproval").datagrid('unselectAll');
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#patrolapproval").datagrid("load", {
				all : index,
				begin : $("#begin").datetimebox("getValue"),
				end : $("#end").datetimebox("getValue")
			});
			}
		}
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
    <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        <div id="toolbar">
        	<div id="tt">
        		
				<div id="my" title="我的任务" data-options="closable:false" 
				style="overflow:auto;padding:20px;display:none;width: 100px;"></div>
				<div id="all" title="全部数据" style="padding:20px;display:none;width: 100px;"></div>
        	</div>
        	<div id="tool" style="" class="cz_div_title">
	        	<table>
	        		<tr>
	        			<td>
	        			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Edit()">上报</a>
	        			</td>
	        		</tr>    		
	        	</table>
        	</div>
        	<div class="cz_div">
				实际巡检时间:&nbsp;&nbsp;<input id="begin" /> ~ <input id="end"/>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="Search()" id="search" iconCls="icon-search">查询</a>
			</div>        	
        </div>
        <div data-options="region:'center'">
        	<table id="patrolapproval"></table>
        </div>
    </div>
  </body>
</html>
