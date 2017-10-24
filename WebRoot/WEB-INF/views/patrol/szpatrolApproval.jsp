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
    <title>水政巡检审批</title>   
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
			});
			$('#begintime').datetimebox({
				editable:false,
				width:160,
				buttons:buttons,
				onChange:function(){
					$("#endtime").datetimebox('enableValidation');
				}
			});
			$('#endtime').datetimebox({
				editable:false,
				width:160,
				buttons:buttons,
				validType:['compareDate[begintime]']
			});
			$('#szpatrol').datagrid({
				url:'szpatrolApproval/szpatrolList.html',
				/* method:'post', */
				queryParams : {
					all:0
				},
				columns:[[
			        {field:'sid',title:'枢纽名称'},
			        {field:'patroltime',title:'巡检时间'},
			        {field:'patroladdress',title:'巡检线路或地点'},
			        {field:'patrolcase',title:'巡查情况'},
			        {field:'patrolmemo',title:'处理措施'},
			        {field:'creater',title:'巡查负责人'},
			        {field:'createtime',title:'创建时间'},
			        {field:'edittime',title:'修改时间'},
			        {field:'creatertime',title:'提交时间'},
			        /* {field:'handler',title:'审核人'},
			        {field:'handlertime',title:'审核时间'}, */
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
				fit : true,
				onDblClickRow:function(index,row){
					/* showDialogWH("编辑自主调度单", "/.html?id="+row.id,520,400); */
				}
			});
			var p = $("#szpatrol").datagrid('getPager');
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
					$('#begintime').datetimebox('setValue','');
					$('#endtime').datetimebox('setValue','');
					if(index==0){
						$('#tool1').show();						
					}else{
						$('#tool1').hide();
					}
					$("#szpatrol").datagrid("unselectAll");
					$('#szpatrol').datagrid('load',{
						all:index
						/* ,
						begin:$('#begin').datetimebox('getValue'),
						end:$('#end').datetimebox('getValue') */
					});
				}
			}); 
		});
		function Delete(){
			var Item=$('#szpatrol').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else if(Item.state=="已提交"){
				$.messager.alert("操作提示","该调度单已提交，不能删除！","info");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"szpatrol/szDelete.html",
							data:{
								"id":Item.id
							},
							success:function(msg){				
								$("#szpatrol").datagrid("reload");
							}
						});
					}
				});
			}
		}
		function Approval(){
			var Item=$('#szpatrol').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialogWH("水政巡检审批", "szpatrolApproval/szpatrolApprovalInfo.html?id="+Item.id,520,400);
			}
		}
      	function Search(){
      		var tab = $('#tt').tabs('getSelected');
      		var index = $('#tt').tabs('getTabIndex',tab);
      		$('#szpatrol').datagrid('load',{
				all:index,
      			begintime:$('#begintime').datetimebox('getValue'),
      			endtime:$('#endtime').datetimebox('getValue')
      		});
      	}
      	function Kan(){
      		var Item=$('#szpatrol').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				
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
        	<div id="tool1" class="cz_div_title">
        		<a class="easyui-linkbutton" data-options="iconCls:'icon_commit',plain:false" onclick="Approval()">审批</a>
        	</div>      	       	
        	<div class="cz_div">        
        		巡检时间：<input id="begintime"/>~<input id="endtime"/>&nbsp;&nbsp;
        		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查询</a>
        	</div>
        </div>
        <!-- <table id="instructions"></table> -->
        <div data-options="region:'center'">
        	<table id="szpatrol"></table>
        </div>        
    </div>
  </body>
</html>
