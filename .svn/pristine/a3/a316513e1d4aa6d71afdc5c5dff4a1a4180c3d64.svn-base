<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>执行部门下发巡检计划</title>
     <%@include file="../header.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		$(function(){
			$('#tt').tabs({
				tabWidth : '160',
				border : false,
				onSelect : function(title, index) {
					$('#endtime').datetimebox('setValue', '');
					$('#starttime').datetimebox('setValue', '');
					if (index == "0") {
						$("#btnList").show();
					} else {
						$("#btnList").hide();
					}
					$("#sendList").datagrid("unselectAll");
					$("#sendList").datagrid("load", {
						type : "0",
						typeDate : index,
						classes:"2"
					});
				}
			});
			var buttons=$.extend([],$.fn.datetimebox.defaults.buttons);
			buttons.splice(2,'',{
				text:'清除',
			    handler:function(target){
			    	$("#"+target.id).datetimebox('setValue','');
			    	$(target).datebox('hidePanel'); 
			    }
			});
			$("#starttime").datetimebox({
				editable:false,
				buttons:buttons,
				onChange:function(){
					$("#endtime").datetimebox('enableValidation');
				}
				
			});
			$("#endtime").datetimebox({
				editable:false,
			    buttons:buttons
			});
			$("#sendList").datagrid({
				iconCls : 'icon icon-icon34',
				width : 'auto',
				height : 'auto',
				pageSize : 20,
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				url : 'egpatroldepartmentsend/egpatorlDepartemntSendList.html',
				remoteSort : false,
				idField : 'id',
				singleSelect : true,
				pagination : true,
				rownumbers : true,
				queryParams : {
					type : "0",
					typeDate : "0",
					classes:"2"//定期巡检
				},
			onDblClickRow:function(index,row){
				showDialogWH("查看执行信息",
						"egpatroldepartmentsend/egpatroldepartmentsendInfo.html?isid=" + row.isid+"&look=1"+"&did="+row.did+"&regular=regular", "500", "400");
			} 
			});
			$("#send").bind("click", function() {
				var row = $("#sendList").datagrid("getSelected");
			 	if (row == null) {
					$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
					return false;
				}else{ 
				 showDialogWH("执行", "egpatroldepartmentsend/egpatroldepartmentsendInfo.html?isid=" + row.isid+"&look=0"+"&did="+row.did+"&regular=regular",500,400); 
				 } 
				});
			var p = $("#sendList").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		});
		function searchs(){
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#sendList").datagrid('unselectAll');
			$("#sendList").datagrid('load',{
				begintime: $("#starttime").datetimebox("getValue"),
				endtime: $("#endtime").datetimebox("getValue"),
				type : "0",
				typeDate : index,
				classes:"2"
			});
		}
	</script>

  </head>
  
  <body class="easyui-layout" id="cc">
	<table id="sendList" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
			   <th data-options="field:'id',hidden:true"></th>
			   <th data-options="field:'isid',hidden:true"></th>
			    <th data-options="field:'did',hidden:true"></th>
				<th data-options="field:'dname',width:150">执行部门</th>
				<th data-options="field:'creater',width:150">分解人</th>
				<th data-options="field:'createtime',width:150">分解时间</th>
				<th data-options="field:'content',width:150">分解内容</th>
				<!-- <th data-options="field:'remark',width:150">备注</th> -->
	<!-- 			<th data-options="field:'stationname',width:150">制单人</th>
				<th data-options="field:'state',width:100">状态</th> -->
			</tr>
		</thead>
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle;">
		<div id="tt" class="easyui-tabs" style="">
			<div id="myTask" title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div id="allData" title="全部数据"
				style="padding:20px;display:none;width: 100px;"></div>
			
		</div>
		<div class="cz_div_title" id="btnList" style="">
			<a  id="send"  href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon_commit" plain="false">执行</a>
		</div>
		<div class="cz_div">
			分解时间:&nbsp;&nbsp;<input id="starttime" type="text" data-options="width:160"	class="easyui-datetimebox" /> ~
			 <input id="endtime" type="text"class="easyui-datetimebox"	data-options="width:160,validType:['compareDate[starttime]']" />
			&nbsp;&nbsp; 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="searchs()" id="search" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
