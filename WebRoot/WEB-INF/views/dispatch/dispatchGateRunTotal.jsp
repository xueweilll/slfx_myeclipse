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
    <title>My JSP 'dispatchGateRunTotal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(function() {
		var index="0";
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2,'', {
			text: '清空',
			handler: function(target){
				$(target).datetimebox('setValue','');
				$(target).datebox('hidePanel');  
			}
		});
		$('#begintimes').datetimebox({
			editable:false,
			width:160,
			buttons:buttons
		});
		$('#endtimes').datetimebox({
			editable:false,
			width:160,
			buttons:buttons
		});
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, i) {
				$("#begintimes").datetimebox("setValue","");
			     $("#endtimes").datetimebox("setValue","");
			     $("#sid").combobox("setValues",[]);
			     index=i;
				var data = {
						"sids" : $("#sid").combobox("getValues"),
						"begintimes" : $("#begintimes").datetimebox("getValue"),
						"endtimes" : $("#endtimes").datetimebox("getValue")
					};
					$("#employee_list").datagrid("load", {
						"sids" : $("#sid").combobox("getValues"),
						"begintimes" : $("#begintimes").datetimebox("getValue"),
						"endtimes" : $("#endtimes").datetimebox("getValue"),
					    "index":i
					});
			}
		});
		
		$("#sid").combobox({
			url : 'statisticsList/station.html',
			valueField : 'id',
			textField : 'name',
			multiple:true
		});
		$("#employee_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			singleSelect : true,
			pagination : true,
			striped : true,
			border : true,
			queryParams: {
				index:"0"
			},
			toolbar : '#toolbar',
			collapsible : false,
			fit : true,
			url : 'dispatchgaterun/pageBind.html',
			columns : [ [ {
				field : 'stationname',
				title : '枢纽名称',
				width : '120px'
			}, {
				field : 'gatename',
				title : '闸门名称',
				width : '120px'
			}, {
				field : 'operatetime',
				title : '操作时间',
				width : '150px'
			}, {
				field : 'operate',
				title : '操作',
				width : '140px'
			}
			] ],
			showFooter: true,
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true
		});
	
		
		$("#print").bind("click", function() {
			var ssid = $("#sid").combobox("getValues");
			var begintimes = $("#begintimes").datetimebox("getValue");
			var endtimes = $("#endtimes").datetimebox("getValue");
			var i = index;
			window.open("dispatchgaterun/print.html?ssid="+ ssid + "&begintimes="+ begintimes + "&endtimes="+endtimes + "&index="+i,"blank");
		});
		$("#export").bind("click",function(){
			var ssid = $("#sid").combobox("getValues");
			var begintimes = $("#begintimes").datetimebox("getValue");
			var endtimes = $("#endtimes").datetimebox("getValue");
			var i = index;
			var link="dispatchgaterun/export.html?ssid="+ ssid + "&begintimes="+ begintimes + "&endtimes="+endtimes + "&index="+i;
			window.location.href=link;
		});
		$("#search").bind("click", function() {
			var data = {
				"sids" : $("#sid").combobox("getValues"),
				"begintimes" : $("#begintimes").datetimebox("getValue"),
				"endtimes" : $("#endtimes").datetimebox("getValue")
			};
			var s= $("#sid").combobox("getValues");
			var sids ="";
			for(var i = 0;i< s.length; i++){
				sids += s[i]+",";
			}
			if(sids != ""){
				sids = sids.substring(0, sids.length -1);
				//alert(sids);
			}
			//alert(index);
			$("#employee_list").datagrid("unselectAll");
			$("#employee_list").datagrid("load", {
				"begintimes" : $("#begintimes").datetimebox("getValue"),
				"endtimes" : $("#endtimes").datetimebox("getValue"),
				"index":index,
				"sids" : sids
			});
			$.messager.progress("close");
		});
		$("#begintimes").datetimebox({
			editable : false,
			buttons : buttons,
			onChange : function() {
				$("#endtimes").datetimebox('enableValidation');
			}
		});
		$("#endtimes").datetimebox({
			editable : false,
			buttons : buttons
		});
		var p = $("#employee_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
	buttons.splice(0, 1, {
		text : '清空',
		handler : function(target) {
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
		<div id="toolbar">
			<div id="tt" class="easyui-tabs" style="">
				<div title="大包围调度" data-options="closable:false"
					style="overflow:auto;padding:20px;display:none;width: 100px;">
				</div>
				<div title="片区调度" style="padding:20px;display:none;width: 100px;">
				</div>
				<div title="自主调度" style="padding:20px;display:none;width: 100px;">
				</div>
			</div>
			<div class="cz_div_title" id="btnList" style="">
				<a id="print" class="easyui-linkbutton" style=""data-options="plain:false,iconCls:'icon-edit'">打印</a>
				&nbsp;&nbsp;<a id="export" class="easyui-linkbutton" style=""data-options="plain:false,iconCls:'icon-edit'">导出</a>
			</div>
			<div class="cz_div" id="department"
				style="min-height:30px;height:auto !important;">
				枢纽名称:<input id="sid" class="easyui-combobox" style="width: 150px"
					editable="false" /> &nbsp;&nbsp;
				&nbsp;&nbsp; 操作时间区间:<input id="begintimes"
					class="easyui-datetimebox" style="width: 130px">
				&nbsp;&nbsp; ~ <input id="endtimes" class="easyui-datetimebox"
					data-options="width:160,editable:false,validType:['compareDate[begintimes]']"
					style="width:130px">&nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" id="search">查询</a>
			</div>
		</div>
		<div data-options="region:'center'">
			<table id="employee_list"></table>
		</div>
		<!-- <div data-options="" -->
	</div>
</body>
</html>
