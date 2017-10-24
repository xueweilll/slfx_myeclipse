<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>My JSP 'employee.jsp' starting page</title>

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
		var dispatchtype="0";
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
			onSelect : function(title, index) {
				$("#begintimes").datetimebox("setValue","");
			     $("#endtimes").datetimebox("setValue","");
			     $("#sid").combobox("setValues",[]);
				dispatchtype=index;
				var data = {
						"ssid" : $("#sid").combobox("getValues"),
						"begintimes" : $("#begintimes").datetimebox("getValue"),
						"endtimes" : $("#endtimes").datetimebox("getValue")
					/* ,
									"dcode":$("#dcode").textbox("getText") */
					};
					$("#employee_list").datagrid("load", {
						jsonStr : JSON.stringify(data),
						"ssid" : $("#sid").combobox("getValues"),
						"begintimes" : $("#begintimes").datetimebox("getValue"),
						"endtimes" : $("#endtimes").datetimebox("getValue"),
						"deadlines":$("#deadlines").datetimebox("getValue"),
					     "dispatchtype":index
						/* ,
										"dcode":$("#dcode").textbox("getText") */
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
				dispatchtype:"0"
			},
			toolbar : '#toolbar',
			collapsible : false,
			fit : true,
			url : 'statisticsList/userStatisticsList.html',
			columns : [ [ {
				field : 'dispatchtype',
				title : '调度类型',
				width : '100px'
			}, {
				field : 'sname',
				title : '枢纽名称',
				width : '120px'
			}, {
				field : 'code',
				title : '调度单编号',
				width : '150px',
				hidden : true
			}, {
				field : 'begintime',
				title : '开机时间',
				width : '140px'
			}, {
				field : 'endtime',
				title : '停机/截止时间',
				width : '140px'
			},
			{
				field:'runtime',
				title:'运行时间/(时)',
				width:'80px'
			},
			{
				field : 'startinlandlevel',
				title : '开机时内河水位',
				width : '90px'
			},{
				field : 'startouterlevel',
				title : '开机时外河水位',
				width : '90px'
			},{
				field : 'stopinlandlevel',
				title : '停机时内河水位',
				width : '90px'
			},{
				field : 'stopouterlevel',
				title : '停机时外河水位',
				width : '90px'
			},
			{
				field : 'count',
				title : '开机台数',
				width : '50px'
			},
			/*   {field : 'inwater',title : '内河水位',width : '100px'}, 
			  {field : 'outwater',title : '外河水位',width : '100px'}, */
			{
				field : 'kjtime',
				title : '开机台时/(时)',
				width : '80px'
			}, {
				field : 'dcscharge',
				title : '抽水流量(米³/秒)',
				width : '100px'
			}, {
				field : 'cscharge',
				title : '总流量(万立方米)',
				width : '100px'
				
			}/* ,
			  {field : 'rdstate',
				title : '状态',
				width : '120px'} */
			] ],
			showFooter: true,
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			/* pagePosition:'bottom', */
			//rownumbers : true,
			view:detailview,
			detailFormatter:function(index,row){
				if(dispatchtype=="1" || dispatchtype=="2"){
					return "<div style=\"padding:2px\"><table class=\"unitList\"style=\"width:420\">"+
					"</table><table class=\"gateList\" style=\"width:420\"></table></div>";
				}else {
					return "<div style=\"padding:2px\"><table class=\"unitList\"style=\"width:420\">"+
					"</table><table class=\"gateList\"style=\"width:420\"></table><table class=\"cbList\"></table>"+
					"<table  class=\"suList\"></table></div>";
				}
			},
			onExpandRow: function(index,row){
				
				var ddv = $(this).datagrid('getRowDetail',index).find('table.unitList');
			     var uurl;
				/* if(type=="0"){ */ //0原先为自主
					if(dispatchtype=="2"){// 2为自主
					uurl="sdexecute/findUnitByExecuteid.html?executeid="+row.rdeid;
				}else {
					uurl="rdexecute/findUnitByExecuteid.html?executeid="+row.rdeid;
				}
					ddv.datagrid({
						url:uurl,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'机组运行记录',
						height:'auto',
						columns:[[
							{field:'unit',title:'机组名称',width:100},
							{field:'begintime',title:'开始时间',width:160},
							{field:'endtime',title:'结束时间',width:160}
						]],
						onResize:function(){
							$('#employee_list').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#employee_list').datagrid('fixDetailRowHeight',index);
							},0);
						}
					});
					var gatelist = $(this).datagrid('getRowDetail',index).find('table.gateList');
					var gurl;
					/* if(type=="0"){ 0原先为自主*/
					if(dispatchtype=="2"){// 2为自主
					
						gurl="sdexecute/findGateByExecuteid.html?executeid="+row.rdeid;
					}else {
						gurl="rdexecute/findGateByExecuteid.html?executeid="+row.rdeid;
					}
					gatelist.datagrid({
						url:gurl,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'闸门运行记录',
						height:'auto',
						columns:[[
							{field:'sid',title:'闸门名称',width:80},
							{field:'operatetime',title:'闸门执行时间',width:160},
							{field:'operate',title:'操作',width:80}
						]],
						onResize:function(){
							$('#employee_list').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#employee_list').datagrid('fixDetailRowHeight',index);
							},0);
						}
					});
				
					$('#employee_list').datagrid('x',index);
				}/* ,
				onLoadSuccess:function(data){
				
						   console.info(data);
						   var date=eval(data);
						   $('#employee_list').datagrid('appendRow',{
							   FlowTypeName:'ss',
							   FlOwTypeCode:50
						    
						   });

				} */
		});
		$("#starttime").datetimebox({
			editable : false,
			buttons : buttons,
			onChange : function() {
				$("#endtimes").datetimebox('enableValidation');
			}
		});
		$("#endtime").datetimebox({
			editable : false,
			buttons : buttons
		});
		
		$("#print").bind("click", function() {
			//$("#department").jqprint();		
			//showDialogWH("打印","statisticsList/print.html", 600,580);
			var ssid = $("#sid").combobox("getValues");
			var begintimes = $("#begintimes").datetimebox("getValue");
			var endtimes = $("#endtimes").datetimebox("getValue");
			var dispatchtypes = dispatchtype;
			window.open("statisticsList/print.html?ssid="+ ssid + "&begintimes="+ begintimes + "&endtimes="+endtimes + "&dispatchtype="+dispatchtypes,"blank");
		  	//window.print();
		});
		$("#export").bind("click",function(){
			var ssid = $("#sid").combobox("getValues");
			var begintimes = $("#begintimes").datetimebox("getValue");
			var endtimes = $("#endtimes").datetimebox("getValue");
			var dispatchtypes = dispatchtype;
			var link="statisticsList/export.html?ssid="+ ssid + "&begintimes="+ begintimes + "&endtimes="+endtimes + "&dispatchtype="+dispatchtypes+"&page="+1+"&rows="+20;
			//console.info(link);
			window.location.href=link;
		});
		$("#search").bind("click", function() {
			var data = {
				"ssid" : $("#sid").combobox("getValues"),
				"begintimes" : $("#begintimes").datetimebox("getValue"),
				"endtimes" : $("#endtimes").datetimebox("getValue")
			};
			//console.info($("#sid").combobox("getValues"));
			$("#employee_list").datagrid("unselectAll");
			$("#employee_list").datagrid("load", {
				jsonStr : JSON.stringify(data),
				"ssid" : $("#sid").combobox("getValues"),
				"begintimes" : $("#begintimes").datetimebox("getValue"),
				"endtimes" : $("#endtimes").datetimebox("getValue"),
				"deadlines":$("#deadlines").datetimebox("getValue"),
				"dispatchtype":dispatchtype
			/* ,
								"dcode":$("#dcode").textbox("getText") */
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
		$("#deadlines").datetimebox({
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
				<!-- 调度单编号:<input id="dcode" class="easyui-textbox" style="width:150px"/> -->
				&nbsp;&nbsp; 开机时间区间:<input id="begintimes"
					class="easyui-datetimebox" style="width: 130px">
				&nbsp;&nbsp; ~ <input id="endtimes" class="easyui-datetimebox"
					data-options="width:160,editable:false,validType:['compareDate[begintimes]']"
					style="width:130px">&nbsp;&nbsp; 
					截止时间:<input id="deadlines"
					class="easyui-datetimebox" style="width: 160px">
				&nbsp;&nbsp;
					<a
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
