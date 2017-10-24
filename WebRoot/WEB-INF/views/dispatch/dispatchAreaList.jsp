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
		$(function(){
			$('#tt').tabs({
				tabWidth:'160',
				border : false,
				onSelect : function(title, index) {
					$("#starttime").datetimebox("setValue",'');
					$("#endtimes").datetimebox("setValue",'');
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
			$('#selfdispatch').datagrid({
				url:'dispatchAreaList/dispatchAreaList.html',
				 columns:[[	       			        
			        {field:'code',title:'分解单编号',width:130,hidden:true},
			        {field:'username',title:'调度令发起人',width:80},
			        {field:'launchtime',title:'调度令发起时间',width:160},
			        {field:'creater',title:'分解单制单人',width:80},
			        {field:'handletime',title:'分解单制单时间',width:160},
			        //{field:'memo',title:'调度备注',width:120},
			        {field:'completetime',title:'分解单完成时间',width:160},
			        {field:'state',title:'分解单状态',width:120},        
			        {field:'receiptid1',hidden:'true'},
			        {field:'states',hidden:'true'},
			        {field:'reid',hidden:'true'}
			    ]],
			    onDblClickRow:function(rowIndex,rowData){
			    	showDialogWH("查看片区调度分解单", "dispatchAreaList/dispatchAreaWatch.html?state=0"+"&states="+rowData.states+"&id="+rowData.receiptid1+"&reid="+rowData.reid,680,600);
			    },
			    queryParams: {
					typeDate:"0"
				},
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
		function add(){
			showDialogWH("添加片区调度分解单", "dispatchAreaList/dispatchAreaInfo.html?state=2"+"&id=0",680,600);
		}
		function edit(){
			var row = $("#selfdispatch").datagrid("getSelected");
			if (row == null/* &&row.stateValue=="0" */) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("编辑片区调度分解单", "dispatchAreaList/dispatchAreaInfo.html?state=0"+"&states="+row.states+"&id="+row.receiptid1+"&reid="+row.reid,680,600);
		}
		function watch(){
			OpenWindow=window.open("", "流程图", "height=250, width=250,titlebar=no,menubar=no,toolbar=no,scrollbars="+scroll+",menubar=no,screenX=250,screenY=300"); 
			OpenWindow.document.write("<BODY BGCOLOR=#ffffff>");
			OpenWindow.document.write("<h1>Hello!</h1>");  
			OpenWindow.document.write("</BODY>"); 
			OpenWindow.document.write("</HTML>"); 
			OpenWindow.document.close();
		}
		function searchs(){
			$('#selfdispatch').datagrid('unselectAll');
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#selfdispatch").datagrid('load',{
				starttime: $("#starttime").datetimebox("getValue"),
				endtimes: $("#endtimes").datetimebox("getValue"),
				typeDate:index
			});
		}
		function deletes(){
			var Item=$('#selfdispatch').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else if(Item.state !='保存'){
				$.messager.alert("操作提示","不是保存状态无法删除！","error");
			}
			else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"dispatchAreaList/delete.html?reid="+Item.reid,
							success:function(){	
								$("#selfdispatch").datagrid("unselectAll");
								$("#selfdispatch").datagrid("reload");
							}
						});
					}
				});
			}
		}       		
	</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="selfdispatch"></table>
	<div id="toolbar"  class="cz_div">
		<div id="tt" class="easyui-tabs" style="">
			<div title="我的任务" style="overflow:auto;padding:20px;display:none;width: 100px;"
			data-options="closable:false">
			</div>
			<div title="全部数据" 
				style="padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div  class="cz_div_title" >
			<a id="adds" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" onclick="add()">新增</a> <a
				id="edits" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'" onclick="edit()">编辑</a> <a
				id="distory"  class="easyui-linkbutton"
				data-options="iconCls:'icon-clear'" onclick="deletes()">删除</a>
			<!-- 	<a id="watch" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="watch()">查看流程图</a> -->
			</div>
			<div class="cz_div">
			分解时间: <input id="starttime" type="text" class="easyui-datetimebox" data-options="width:160">
			~<input id="endtimes" type="text" class="easyui-datetimebox"
				data-options="width:160,validType:'compareDate[starttime]'">
		      <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchs()">查询</a>
		    </div>
	</div>
	<div id="dialog"></div>
</body>
</html>
