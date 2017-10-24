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
					$("#adds").hide();
					$("#edits").hide();
					$("#distory").hide();
					$("#commit").show();
				}else{
					$("#commit").hide();
					$("#adds").show();
					$("#edits").show();
					$("#distory").show();
				}
				$("#outStorage_list").datagrid("unselectAll");
				$("#outStorage_list").datagrid("load", {
					typeDate : index
				});
			}
		});
		$("#outStorage_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'outStorage/bind.html',
			idField : 'id',
			singleSelect : true,
			queryParams: {
				typeDate : 0
			},
			onDblClickRow:function(rowIndex, rowData){
				searchInfo(rowData);
			},
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			columns:[[    
			          {field:'code',title:'编号',width:120},    
			          {field:'creater',title:'制单人',width:140},    
			          {field:'creatertime',title:'制单时间',width:140},    
			          {field:'handler',title:'审批人',width:140},    
			          {field:'handlertime',title:'审批时间',width:140},    
			          {field:'casuse',title:'事由',width:200},    
			          {field:'memo',title:'备注',width:200},
			          {field:'state',title:'状态',width:140,formatter:function(value,row,index){
			        	  if(value==0){
			        		  return "保存";
			        	  }else if(value==1){
			        		  return "已提交";
			        	  }
			          }},
			          {field:'stateValue',hidden:true}
			          
			      ]]
		}); 
		var p = $("#outStorage_list").datagrid('getPager');
		//console.info(p);
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#starttime").datetimebox({
			onChange : function() {
				$('#endtime').datetimebox('enableValidation');
			}
		});
	});
	function selectbig(){
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		var tab = $('#tt').tabs('getSelected');
		var data=$("#code").textbox("getValue");
		var index = $('#tt').tabs('getTabIndex',tab);
		$("#outStorage_list").datagrid('load',{
			code:(data=="" ? null:data),
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue"),
			typeDate:index
		});
		$("#outStorage_list").datagrid("unselectAll");
	}

	function add() {
		showDialogWH("添加物资出库", "outStorage/outStorageInfo.html?id=0",650,510);
	}

	function edit() {
		var row = $("#outStorage_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "warning");
			return false;
		}
		if (row.stateValue!="0") {
			$.messager.alert("操作提示", "表单已提交，不能修改！", "warning");
			return false;
		}
		showDialogWH("编辑物资出库", "outStorage/outStorageInfo.html?id=" + row.id,650,510);
	}

	function distory() {
		var row = $("#outStorage_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "warning");
			return false;
		}
		if (row.stateValue!="0") {
			$.messager.alert("操作提示", "表单已提交，不能删除！", "warning");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "outStorage/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#outStorage_list").datagrid("unselectAll");
						$("#outStorage_list").datagrid("reload");
					}
				});
			}
		});
	}
	
	function commit(){
		var row = $("#outStorage_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "warning");
			return false;
		}
		showDialogWH("提交出库单", "outStorage/outStorageInfo.html?id=" + row.id+"&type=1",650,550);
	}
	function searchInfo(row){
		showDialogWH("查看出库明细", "outStorage/outStorageInfo.html?id=" + row.id+"&type=0",650,550);
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
	<table id="outStorage_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto">
		<div id="tt" class="easyui-tabs" style="">
			<div title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div title="全部数据" style="padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div class="cz_div">
			<a  id="commit"  href="javascript:void(0)" class="easyui-linkbutton" onclick="commit()" iconCls="icon_commit" plain="false">提交</a>
			<a id="adds" style="display: none;" href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon_add" plain="false">添加</a>
			<a id="edits" style="display: none;" href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()" iconCls="icon_edit" plain="false">编辑</a>
			<a id="distory" style="display: none;" href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon_delete" plain="false">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
			
			出库编号:<input id="code" class="easyui-textbox" name="code"   
    				data-options="width:'160'"  />
			制单时间： <input id="starttime" type="text" data-options="width:160,editable:false,buttons: buttons">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
