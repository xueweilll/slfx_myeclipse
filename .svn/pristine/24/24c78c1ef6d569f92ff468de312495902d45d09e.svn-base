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
				if(index=="2"){//自主调度原先为0
					$("#smemo").html("发起");
					$("#dispatchExecute_list").datagrid("hideColumn",'code');
					$("#dispatchExecute_list").datagrid("hideColumn",'code1');
					$("#dispatchExecute_list").datagrid("hideColumn",'etime');
					$("#dispatchExecute_list").datagrid("hideColumn",'rname');
					$("#dispatchExecute_list").datagrid("hideColumn",'rtime');
					$("#dispatchExecute_list").datagrid("showColumn",'rcname');
					$("#dispatchExecute_list").datagrid("showColumn",'rctime');
				}else{
					$("#smemo").html("接收");
					$("#dispatchExecute_list").datagrid("hideColumn",'code1');
					$("#dispatchExecute_list").datagrid("hideColumn",'code');
					$("#dispatchExecute_list").datagrid("hideColumn",'rcname');
					$("#dispatchExecute_list").datagrid("hideColumn",'rctime');
					$("#dispatchExecute_list").datagrid("hideColumn",'fqname');
					$("#dispatchExecute_list").datagrid("showColumn",'lname');
					$("#dispatchExecute_list").datagrid("showColumn",'etime');
					$("#dispatchExecute_list").datagrid("showColumn",'rname');
					$("#dispatchExecute_list").datagrid("showColumn",'rtime');
				}
				$("#dispatchExecute_list").datagrid("unselectAll");
				$("#dispatchExecute_list").datagrid("load", {
					typeDate : index
				});
			}
		});
		$("#dispatchExecute_list").datagrid({
			
			iconCls : 'icon icon-icon7',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'dispatchExecute/bind.html',
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
			          {field:'code',title:'接收单编号',width:130,hidden:true},    
			          {field:'code1',title:'接收单编号',width:130,hidden:true},    
			          {field:'fqname',title:'汇报发起人',width:80,hidden:true},
			          {field:'lname',title:'调度令发起人',width:80},    
			          {field:'ltime',title:'发起时间',width:140},    
			          {field:'etime',title:'结束时间',width:140},    
			          {field:'rname',title:'调度令接收人',width:80},    
			          {field:'rtime',title:'接收时间',width:140},    
			          {field:'rcname',title:'汇报人',width:80,hidden:true},    
			          {field:'rctime',title:'汇报时间',width:140,hidden:true},    	  
			      ]]
		}); 
		var p = $("#dispatchExecute_list").datagrid('getPager');
		//console.info(p);
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
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
		$("#dispatchExecute_list").datagrid('load',{
			/* type:(data=="3" ? null:data), */
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue"),
			/* code:$("#bh").textbox("getText"), */
			typeDate:index
		});
	}
	function searchInfo(row){
		$("#dispatchExecute_list").datagrid('unselectAll');
		if(row.state==="新建"){
			$.messager.alert("操作提示","表单未提交","error");
			return false;
		}
		$('#diglog').dialog({    
		    title: '调度进度',    
		    width: 600,    
		    height: 400,
		    fit:true,
		    closed: false,    
		    cache: false,    
		    href:"dispatchExecute/dispatchExecuteInfo.html?id="+row.id,    
		    modal: true   
		});
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
	<table id="dispatchExecute_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto">
		<div id="tt" class="easyui-tabs" style="">
			<div title="大包围调度" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div title="片区调度" style="padding:20px;display:none;width: 100px;">
			</div>
			<div title="自主调度" style="padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div >
			<!-- <a id="adds"  href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon_add" plain="true">添加</a>
			<a id="edits"  href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()" iconCls="icon_edit" plain="true">编辑</a>
			<a id="distory"  href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon_delete" plain="true">删除</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
			
			<!-- 调度编号:<input id="type" class="easyui-combobox" name="type"   
    				data-options="valueField:'id',width:'160',textField:'text',url:'paramers/bigWayData.html'" /> -->
    		</div>
    		<div class="cz_div">
    		<!-- 接收单编号:<input id="bh" class="easyui-textbox" name="bh"   
    				data-options="valueField:'id',width:'160'" /> -->	
			<span id="smemo">发起</span>时间: <input id="starttime" type="text" data-options="width:160,editable:false
			">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div id="diglog" style="z-index: -1;overflow:auto"></div>
</body>
</html>
