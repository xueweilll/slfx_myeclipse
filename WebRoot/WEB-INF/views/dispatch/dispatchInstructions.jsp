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
    <title>执行人员汇报</title>
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
			var actor=${actor};
			if(actor==0){
				$("#compulsoryDistory").show();
			}
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
			$('#selfdispatch').datagrid({
				url:'dispatchinstructions/dispatchList.html',			
				columns:[[
			        {field:'code',title:'汇报单编号',hidden:true},
			        {field:'promotetime',title:'汇报时间'},
			        {field:'username',title:'汇报发起人'},
			        //{field:'memo',title:'汇报备注'},
			        {field:'creatername',title:'制单人'},
			        {field:'createtime',title:'制单时间'},
			        {field:'state',title:'状态'},
			        {field:'completetime',title:'完成时间'}
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
				,
				onDblClickRow:function(index,row){
					showDialogWH("查看汇报单信息", "dispatchinstructions/dispatchInfo2.html?id="+row.id,520,400);
				}
			});
			var p = $("#selfdispatch").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});			
		});
		function Add(){
			showDialogWH("新增汇报单信息", "dispatchinstructions/dispatchInfo.html?id=0",520,400);
		}
		function Edit(){
			var Item=$('#selfdispatch').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else if(Item.state != "新建"){
				$.messager.alert("操作提示","该调度方案已提交，不能进行修改！","info");
			}else{
				showDialogWH("编辑汇报单信息", "dispatchinstructions/dispatchInfo.html?id="+Item.id,520,400);
			}
		}
		function Delete(){
			var Item=$('#selfdispatch').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else if(Item.state !="新建"){
				$.messager.alert("操作提示","该汇报单已提交，不能删除！","info");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"dispatchinstructions/dispatchDelete.html",
							data:{
								"id":Item.id
							},
							success:function(){								
								$("#selfdispatch").datagrid("reload");
							}
						});
					}
				});
			}
		}
		function dateDiff(interval, date1, date2)
	    {
	        var objInterval = {'D' : 1000 * 60 * 60 * 24, 'H' : 1000 * 60 * 60, 'M' : 1000 * 60, 'S' : 1000, 'T' : 1};
	        interval = interval.toUpperCase();
	        var dt1 = Date.parse(date1.replace(/-/g, '/'));
	        var dt2 = Date.parse(date2.replace(/-/g, '/'));
	        try
	        {
	        	alert(dt2 - dt1);
	        	alert('(objInterval.' + interval + ')');
	            return Math.round((dt2 - dt1) / ('(objInterval.' + interval + ')'));
	        }
	        catch (e)
	        {
	            return e.message;
	        }
	    }
		function compulsoryDistory(){
			var Item=$('#selfdispatch').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"dispatchinstructions/dispatchDelete.html",
							data:{
								"id":Item.id
							},
							success:function(){								
								$("#selfdispatch").datagrid("reload");
							}
						});
					}
				});
			}
		}
      	function Search(){
      		$('#selfdispatch').datagrid('unselectAll');
      		$('#selfdispatch').datagrid('load',{
      			code:$('#code').textbox('getText'),
      			begintime:$('#begintime').datetimebox('getValue'),
      			endtime:$('#endtime').datetimebox('getValue')
      		});
      	}
      	function Kan(){
      		var Item=$('#selfdispatch').datagrid('getSelected');
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
        	<div class="cz_div_title">
       			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:false" onclick="Add()">新增</a>
       			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:false" onclick="Edit()">编辑</a>
       			<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:false" onclick="Delete()">删除</a>
       			<a id="compulsoryDistory"  href="javascript:void(0)" class="easyui-linkbutton" onclick="compulsoryDistory()" data-options="plain:false,iconCls:'icon-clear'" style="display: none;">强制删除</a>
       			<!-- <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false" onclick="Kan()">查看流程图</a> -->
        	</div>
        	<div class="cz_div">
        		<div style="display:none">
        		汇报单编号：<input id="code" class="easyui-textbox" data-options="width:160"/>&nbsp;&nbsp;
        		</div>
        		汇报时间：<input id="begintime"/>~<input id="endtime"/>&nbsp;&nbsp;
        		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查询</a>
        	</div>     	
        </div>
        <!-- <table id="instructions"></table> -->
        <div data-options="region:'center'">
        	<table id="selfdispatch"></table>
        </div>        
    </div>
  </body>
</html>
