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
    <title>My JSP 'selfdispatch.jsp' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		var editIndex = undefined;	
		$(function(){
			$('#tt').tabs({
				tabWidth:'160',
				border : false,
				onSelect : function(title, index) {
					if(index=="0"){
						$(".cz_div_title").show();
					}else{
						$(".cz_div_title").hide();
					}
					$("#typees").combobox("clear");
					$("#selfdispatch").datagrid("unselectAll");
					$("#selfdispatch").datagrid("load", {
						typeDate : index
					});
				}
			});
			$("#selfdispatch").datagrid({
				url:'dispatchTransmitList/dispatchTransmitList.html',
				columns:[[
				    {field:'code',title:'分解单编号',width:'140px',hidden:true},
			        {field:'receiptid',hidden:'true'},
			        {field:'username',title:'分解发起人',width:'100px'},
			        {field:'launchtime',title:'调度令发起时间',width:'140px'},
			        {field:'endtime',title:'调度令结束时间',width:'140px'},
			        {field:'receipter',title:'调度令接收人',wiedth:'120px'},
			        {field:'edname',title:'调度令转发人',width:'120px'}, 
			        {field:'transmittime',title:'转发时间',width:'140px'},
			        {field:'createtime',title:'分解单制单时间',width:'140px'},
			        {field:'state',title:'处理状态',width:'120px'}
			    ]],
			    queryParams: {
					typeDate:"0"
				},
			    type:'POST',
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
				
				}
			});
			var p = $("#selfdispatch").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
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
					$("#endtimes").datetimebox('enableValidation')
				}
				
			});
			$("#endtimes").datetimebox({
				editable:false,
			    buttons:buttons
			});
		});
		function searchdbw(){
			$("#selfdispatch").datagrid('unselectAll');
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#selfdispatch").datagrid('load',{
				starttime: $("#starttime").datetimebox("getValue"),
				endtime: $("#endtimes").datetimebox("getValue"),
				bh:$("#bh").textbox("getText"),
				typeDate:index
			});
		}
		function Transmit(){
			var row = $("#selfdispatch").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("添加转发调度单", "dispatchTransmitList/dispatchTransmit.html?receiptid="+row.receiptid1+"&id="+row.id,500,450);
		}

		 		
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
  	<input id="type" type="hidden" value="${type}" />
  	<input id="receiptid1" type="hidden" value="${receiptid1}"/>
    <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        <div id="toolbar">
        	<div id="tt" class="easyui-tabs" style="">
				<div title="我的任务" style="overflow:auto;padding:20px;display:none;width: 100px;"
				data-options="closable:false">
				</div>
				<div title="全部数据" 
					style="padding:20px;display:none;width: 100px;">
				</div>
			</div>       	       	
        	<div class="cz_div_title">
        			<a id="forward"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Transmit()">转发</a>      			    
    				</div>
    				<div class="cz_div">
    				<div style="display:none;">
    				分解单编号:<input id="bh" class="easyui-textbox" name="bh" data-options="width:'160'">
    				</div>
        		转发时间区间: <input id="starttime" type="text" class="easyui-datetimebox" data-options="width:160">
			 ~<input id="endtimes" type="text" class="easyui-datetimebox"
				data-options="width:160,validType:'compareDate[starttime]'">
				
		      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchdbw()">查询</a>
        	</div>      	
        </div>
        <div data-options="region:'center'">
        	<table id="selfdispatch"></table>
        </div>        
    </div>
    <div id="dialog"></div>
  </body>
</html>
