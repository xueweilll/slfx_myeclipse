<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>My JSP 'message.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript">
		$(function(){
		$('#tt').tabs({
		title:'短信中心',
			iconCls:'icon icon-icon27',    
			   border:false,    
			   onSelect:function(title,index){    
				   $("#message_list").datagrid("load",{type:index});
			   }    
		}); 	
		$("#message_list").datagrid({
			width:'auto',
			height:'auto',
			pageSize:20,
			nowrap:false,
			striped:true,
			border:true,
			collapsible:false,
			fit:true,
			url:'messageReceiver/bind.html',
			remoteSort:false,
			idField:'id',
			onDblClickRow:function(rowIndex, rowData){
				selectContents(rowData);
			},
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			toolbar:'#tb',
			queryParams: {
				type:"0"
			}

		});
		var p = $("#message_list").datagrid('getPager');
		$(p).pagination({
			pageList:[5,10,15,20],
			beforePageText:'第',
			afterPageText:'页        共{pages}页',
			displayMsg:'当前显示{from}-{to}条记录      共{total}条记录'
		});
		
		$("#starttime").datetimebox({
			onChange:function(){
				$('#endtime').datetimebox('enableValidation');
			}
		});
	});
	function selectMessages(){
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		var tab = $('#tt').tabs('getSelected');
		var index = $('#tt').tabs('getTabIndex',tab);
		$("#message_list").datagrid('load',{
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue"),
			type: index
		});
	}
	function distory(){
		var row = $("#message_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "messageReceiver/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#message_list").datagrid("reload");
					}
				});
			}
		});
	}
	function selectContents(){
		var row = $("#message_list").datagrid("getSelected");
		if(row==null){
			$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			return false;
		}
		if(row.state!="2"){
			$.post("messageReceiver/saveState.html",{id:row.id});
		}
		$('#dd').dialog({    
		    title: '消息内容',
		    iconCls:'',
		    width: 400,    
		    height: 200,    
		    closed: false,    
		    cache: false,    
		    href: 'messageReceiver/selectContents.html?id='+row.id,    
		    modal: true,
		    method:'post',
		    onBeforeClose:function(){
		    	var str=$('#dd').dialog("body").html();
		    	if(str.length>20){
		    		str=str.substring(0, 20)+"...";
		    	}
		    	var date =new Date();
		    	$('#message_list').datagrid('updateRow',{
		    		index: $("#message_list").datagrid("getRowIndex",row),
		    		row: {
		    			contents: str,
		    			rtime:date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()
		    		}

		    	});
		    }
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

<body class="easyui-layout">
	<table id="message_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th data-options="field:'messager',width:320">发送人</th>
				<th data-options="field:'contents',width:320">短信内容</th>
				<th data-options="field:'stime',width:160">发送时间</th>
				<th data-options="field:'rtime',width:160">接收时间</th>
				<th data-options="field:'state',hidden:true"></th>
			</tr>
		</thead>
	</table>
	<div id="tb" >
		<div id="tt" class="easyui-tabs" >
			<div title="PC消息" style="padding:20px;display:none;width: 100px;">
			</div>
			<div title="手机消息" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div  class="cz_div">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="distory()" iconCls="icon_delete" plain="false">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			发送时间: <input id="starttime" type="text" data-options="width:160,buttons: buttons,editable:false">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,buttons: buttons,editable:false,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectMessages()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div id="dd"></div>
	<div id="dr">
		<table id="tbr" class="easyui-datagrid"
			style="width:700px;height:250px"
			data-options="singleSelect:true,collapsible:true">
			<thead>
				<tr>
					<th data-options="field:'name',width:180">姓名</th>
					<th data-options="field:'phone',width:220">手机号码</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
