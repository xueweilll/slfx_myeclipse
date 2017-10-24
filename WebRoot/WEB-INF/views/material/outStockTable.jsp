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
		$("#types").combobox({
			url:'paramers/outStockType.html',
			valueField : 'id',
			textField : 'text',
			editable : false
		});
		
		$("#inStorage_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'outStockTable/stockTable.html',
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			columns:[[    
			          {field:'mname',title:'物品名称',width:120},    
			          {field:'sname',title:'规格',width:140},    
			          {field:'pname',title:'计量单位',width:140},    
			          {field:'count',title:'数量',width:100},    
			          {field:'types',title:'类型',width:140},     
			          {field:'oldstorage',title:'原库存',width:100},
			          {field:'newstorage',title:'现库存',width:100},
			          {field:'creatertime',title:'出库时间',width:160},
			        /*   {field:'memo',title:'备注',width:200}, */
			          {field:'stateValue',hidden:true}       
			      ]]
		}); 
	
		
		var p = $("#inStorage_list").datagrid('getPager');
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
		var index = $('#types').combobox("getValue");
		if(index==''){
			index=null;
		}
		$("#inStorage_list").datagrid('load',{
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue"),
			typeDate:index
		});
	}
	function searchInfo(){
		var row = $("#inStorage_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWH("查看入库明细", "inStorage/inStorageInfo.html?id=" + row.id+"&type=0",700,550);
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
	<table id="inStorage_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto" >
		<div style="margin-bottom:5px" class="cz_div">
				
			出库类型:<input id="types" class="easyui-combobox" name="types"   
    				data-options="width:'160'"/>
			出库时间区间: <input id="starttime" type="text" data-options="width:160,editable:false,buttons: buttons">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
