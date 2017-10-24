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
		$("#inStorage_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'monthlyReport/monthlyReportList.html',
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			columns:[[    
			          {field:'mname',title:'物品名称',width:120},    
			          {field:'sname',title:'规格',width:120},    
			          {field:'pname',title:'计量单位',width:120},    
			          {field:'ultstorage',title:'上月结存',width:120},    
			          {field:'instorage',title:'本月入库',width:110},    
			          {field:'outstorage',title:'本月出库',width:110},    
			          {field:'checkoutstorage',title:'本月借出',width:110}, 
			          {field:'checkinstorage',title:'本月归还',widht:110},
			          {field:'scrapstorage',title:'本月报废',width:110},
			          {field:'storage',title:'现库存',width:100},
			          {field:'creatertime',title:'时间',width:200}, 
			          {field:'stateValue',hidden:true}
			          
			      ]]
		}); 
		$("#month").combobox({
			url:'paramers/month.html',
			valueField : 'id',
			textField : 'text',
			editable : false
			
		});
		$("#year").combobox({
			url:'monthlyReport/year.html',
			textField:'createtime',
			valueField:'createtime',
			editable : false
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
		$("#inStorage_list").datagrid('load',{
			month:$("#month").combobox("getValue"),
		    year:$("#year").combobox("getText")
		});
	}
	function edit() {
		var row = $("#inStorage_list").datagrid("getSelected");
		if (row == null&&row.stateValue=="0") {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		if (row.stateValue!="0") {
			$.messager.alert("操作提示", "表单已提交，不能修改！", "error");
			return false;
		}
		showDialogWH("编辑物资入库", "inStorage/inStorageInfo.html?id=" + row.id,700,550);
	}

	function distory() {
		var row = $("#inStorage_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		if (row.stateValue!="0") {
			$.messager.alert("操作提示", "表单已提交，不能删除！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "inStorage/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#inStorage_list").datagrid("unselectAll");
						$("#inStorage_list").datagrid("reload");
					}
				});
			}
		});
	}
	function print() {
		var row = $("#month").combobox("getValue");
		var year=$("#year").combobox("getText");
			if (row == '') {
			$.messager.alert("操作提示", "请选择一个月份再进行操作！", "error");
			return false;
		}
		if(year== ''){
			$.messager.alert("操作提示","请选择一个年份在进行操作！","error");
			return false;
		} 
		var link="monthlyReport/print.html?month="+row+"&year="+myEncoder(year);
		window.location.href=link;
		return false;	
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
		<div id="tb" style="padding:5px;height:auto">

		<div style="margin-bottom:5px" class="cz_div">
		    年份:<input id="year" class="easyui-combobox" name="year" data-options="width:'100'"/>		
			 月份:<input id="month" class="easyui-combobox" name="month"   
    				data-options="width:'100'"  />
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
				<a href="javascript:void(0)"class="easyui-linkbutton"
				 onclick="print()" iconCls="icon-search">导出</a>
		</div>
	</div>
</body>
</html>
