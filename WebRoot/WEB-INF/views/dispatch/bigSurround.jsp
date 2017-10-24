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
<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

	$(function() {
		$('#tt').tabs({
			tabWidth:'160',
			border : false,
			onSelect : function(title, index) {
				
				
				if(index=="0"){
					$(".cz_div_title").show();
					
				}else{
					$(".cz_div_title").hide();
				}
				$("#bigSurround_list").datagrid("unselectAll");
				$("#bigSurround_list").datagrid("load", {
					typeDate : index
				});
			}
		});
		$("#bigSurround_list").datagrid({
			
			iconCls : 'icon icon-icon7',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'bigSurround/bind.html',
			idField : 'id',
			singleSelect : true,
			queryParams: {
				typeDate:"0"
			},
			onDblClickRow:function(rowIndex, rowData){
				searchInfo(rowData);
			},
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			columns:[[    
			          {field:'code',title:'分解单编号',width:150},    
			          {field:'lname',title:'调度令发起人',width:80},    
			          {field:'ltime',title:'调度令发起时间',width:140},    
			          {field:'etime',title:'调度令结束时间',width:140},    
			          {field:'rname',title:'调度令接收人',width:80},    
			          {field:'rtime',title:'调度令接收时间',width:140},    
			          /* {field:'rcname',title:'制单人',width:80},    
			          {field:'rctime',title:'制单时间',width:140}, */    
			          {field:'dcname',title:'分解人',width:80},    
			          {field:'dctime',title:'分解时间',width:140},	  
			          {field:'state',title:'片区处理状态',width:100},
			          {field:'trstate',title:'转发处理状态',width:100}
			      ]]
		}); 
		var p = $("#bigSurround_list").datagrid('getPager');
		//console.info(p);
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
		    	$("#"+target.id).datetimebox('setValue','')
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

	});
	function selectbig(){
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		var tab = $('#tt').tabs('getSelected');
		/* var data=$("#type").combobox("getValue"); */
		var index = $('#tt').tabs('getTabIndex',tab);
		$("#bigSurround_list").datagrid('load',{
			/* type:(data=="3" ? null:data), */
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue"),
			bh:$("#bh").textbox("getText"),
			typeDate:index
		});
		$("#bigSurround_list").datagrid('unselectAll');
	}

	function add() {
		showDialogWH("添加大包围分解调度", "bigSurround/bigSurroundInfo.html?id=0",670,550);
	}

	function edit() {
		var row = $("#bigSurround_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		if (row.stateValue!="0") {
			$.messager.alert("操作提示", "指令已提交，不能修改！", "error");
			return false;
		}
		showDialogWH("编辑大包围分解调度", "bigSurround/bigSurroundInfo.html?id=" + row.id,670,550);
	}

	function distory() {
		var row = $("#bigSurround_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		if (row.stateValue!="0") {
			$.messager.alert("操作提示", "指令已提交，不能删除！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "bigSurround/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#bigSurround_list").datagrid("unselectAll");
						$("#bigSurround_list").datagrid("reload");
					}
				});
			}
		});
	}
	function searchInfo(row){
		showDialogWH("查看大包围调度", "bigSurround/bigSurroundInfo.html?id=" + row.id+"&type=0&state=1",670,550);
	}
	var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
	buttons.splice(0,1,{
		text: '清空',
		handler: function(target){
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	}); 

</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="bigSurround_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto">
		<div id="tt" class="easyui-tabs" style="">
			<div title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div title="全部数据" style="padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div class="cz_div_title" >
			<a id="adds"  href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" data-options="plain:false,iconCls:'icon-add'">添加</a>
			<a id="edits"  href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()" data-options="plain:false,iconCls:'icon-edit'">编辑</a>
			<a id="distory"  href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" data-options="plain:false,iconCls:'icon-clear'">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
			
			<!-- 调度编号:<input id="type" class="easyui-combobox" name="type"   
    				data-options="valueField:'id',width:'160',textField:'text',url:'paramers/bigWayData.html'" /> -->
    		</div>
    		<div class="cz_div">
    		<div style="display:none;">
    		分解单编号:<input id="bh" class="easyui-textbox" name="bh"   
    				data-options="valueField:'id',width:'160'" />
    				</div>	
			分解时间: <input id="starttime" type="text" data-options="width:160,editable:false,buttons: buttons
			">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
