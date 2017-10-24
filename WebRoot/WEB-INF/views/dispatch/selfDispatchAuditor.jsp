<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>执行部门请示</title>
    <%@include file="../header.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<script>
		var editIndex = undefined;	
		$(function(){
			$('#tt').tabs({
				tabWidth:'160',
				border : false,
				onSelect : function(title, index) {
					$('#begintime').datetimebox('setValue','');
					$('#endtime').datetimebox('setValue','');
					if(index=="0"){
						$(".cz_div_title").show();
					}else{
						$(".cz_div_title").hide();
					}
					$("#selfdispatch").datagrid("unselectAll");
					$("#selfdispatch").datagrid("load", {
						typeDate : index
					});
				}
			});
			
			$("#selfdispatch").datagrid({
				url:'sdAuditor/sdAuditorList.html',		
				queryParams: {
					typeDate:"0"
				},
				columns:[[	       			        
			        {field:'code',title:'请示单编号',width:'120px',hidden:true},
			        {field:'promotetime',title:'请示时间',width:'140px'},
			        {field:'username',title:'请示发起人',width:'130px'},
			        {field:'creater',title:'制单人',width:'130px'},
			        {field:'createtime',title:'制单时间',width:'140px'},
			        {field:'auditor',title:'审核人',width:'130px'},
			        {field:'audittime',title:'审核时间',width:'140px'}
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
				fit : true,onDblClickRow:function(index,row){				
					showDialogWH("查看请示单信息", "sdAuditor/sdAuditorInfo.html?id="+row.id+"&state=1",500,475);
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
			    	$(target).datetimebox('hidePanel');
			    }
			});
			$("#begintime").datetimebox({
				buttons:buttons,
				editable:false,
				onChange : function() {
					$('#endtime').datetimebox('enableValidation');
				}
			});
			$("#endtime").datetimebox({
				buttons:buttons,
				editable:false
			});
		
		});
		function Add(){
			var row = $("#selfdispatch").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("审核请示单信息", "sdAuditor/sdAuditorInfo.html?id="+row.id+"&state=0",500,475);
		}
		function selectbig(){
			$("#selfdispatch").datagrid('unselectAll');
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#selfdispatch").datagrid('load',{
				code: $("#code").textbox("getValue"),
				begintime: $("#begintime").datetimebox("getValue"),
				endtime: $("#endtime").datetimebox("getValue"),
				typeDate:index
			});
		}
	</script>
  </head>
  
   <body class="easyui-layout" id="cc">
  
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
			 <div class="cz_div_title" >    	
        	<a id="adds"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Add()">审核</a>
        	</div>
        	<div  class="cz_div">
        	<div style="display:none;">  
        	请示单编号:&nbsp;<input id="code" class="easyui-textbox" style="width: 120px"> &nbsp;&nbsp;
        	</div> 
        	请示时间:&nbsp;<input id="begintime" style="width: 160px"/> ~ 
			<input id="endtime" class="easyui-datetimebox"	data-options="width:160,validType:['compareDate[begintime]']"/>&nbsp;&nbsp;
        	<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="selectbig()" iconCls="icon-search">查询</a> 
        	</div>  
        </div>
        <div data-options="region:'center'">
        	<table id="selfdispatch"></table>
        </div>        
    </div>

  </body>
</html>
